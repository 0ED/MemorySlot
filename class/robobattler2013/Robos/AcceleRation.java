/*
 オーダー数が多く動作遅し。L145の進行状況確認を使用する事
 
 1/27　　パワーサーチ実装(setLog) 運次第で思考時間短縮
 　　　  searchTargetした座標に打ち返すだけのRoboより弱い
 1/26　　メモリ超過防止ほか　１思考２時間くらい？
 1/22　　1マップではなく大量のマップを参照する方式に決定
 1/21　　期待値誤差の蓄積を解消するためマップを大きく改変(AcceleRation) //Geneの方が優位
 1/10    盤端に対するラインミサイルの向きを断定する処理追加
 searchTargetでのPoint取得時、周囲マスが盤外時の処理を追加
 敵の射撃ログを記憶(GeneRation)
 h25/1/9 (Cannoneer _ RRと来ました)
 敵射撃位置の取得マス期待値算出、get位置と同じなら決め打ち(Sniper)
 ExPointSetクラス実装,ラインミサイル方向制御
 12/29   ラインミサイルの場合の期待値算出完成、敵取得の重みは縦横1/2ずつで総て加算
 12/27   メモリマップ、期待値算出システム完成
 
 */
import robobattler2.*;
import java.awt.Point;
import java.util.*;

public class AcceleRation extends Robo{
    static final int  MISSILE_L_VERTICAL = 6;
    static final int  SIZE = 9;
    static final int  NUM_MISSILES = 16;
    static final int  MYEXP = 0;
    static final int  ENEMYEXP = 1;
    static final int  FIREEXP = 2;
    static final int  GETPOINT = 1;             //自分の持っているマス
    SetMissType typeMap = new SetMissType(); //missileTypeのintとPoint[]を関連付けるマップ
    ArrayList<int[][]> mapLog=new ArrayList<int[][]>();
    int[][] memory =new int[SIZE][SIZE];  //記憶マップ、自身所持マスは==GETPOINT
    int count =0; //射撃した回数を記憶
    int turn=1;   //先後攻、0なら先攻
    int power=0;
    Point[] area;
    boolean line_VH; //linemissileがVかHか(Vでtrue)
    
    protected void action (robobattler2.Missile missile){
        Point target =new Point();
        if(count>=NUM_MISSILES)memoryFlesh(); //バトル毎の初期化
        
        Point enemyTarget = searchTarget(); //相手の射撃地点を取得
        if(enemyTarget==null){turn =0; mapLog.add(new int[SIZE][SIZE]);}//自分が先手
        else{ //相手の射撃情報があればメモリに期待値を落とし込む
            int enemyType = searchType(); //相手のミサイル種を取得
            
            ExPointSet enemyEpSet=new ExPointSet();  //enemyTarget周辺の敵獲得マスの期待値を算出
            getMaxExp(getArea(enemyType),enemyEpSet,enemyTarget);
            if(enemyType==1){ //相手がラインミサイルを使った場合
                if(enemyTarget.x<0+1 || enemyTarget.x>SIZE-1-1){ //getTargetが端ならラインの縦横を断定
                    getMaxExp(getArea(MISSILE_L_VERTICAL),enemyEpSet,enemyTarget);
                }
                
                else if(enemyTarget.y<0+1 || enemyTarget.y>SIZE-1-1)		    {
                    //上のデータをそのまま使用
                }
                
                else{//縦横双方について走査
                    ExPointSet enemyEpSetV=new ExPointSet();
                    getMaxExp(getArea(MISSILE_L_VERTICAL),enemyEpSet,enemyTarget);
                    if(enemyEpSetV.getExp()>enemyEpSet.getExp()){ //よりExpの大きい物を採用
                        enemyEpSet=enemyEpSetV;
                        enemyType=MISSILE_L_VERTICAL;
                    }}
            }
            addMemory(enemyTarget,enemyType,-GETPOINT,memory);
            setLog(enemyTarget,enemyType);
        }
        
        
        if((count+turn)==0)target=new Point((int)SIZE/2,(int)SIZE/2); //初手は中央
        else if(mapLog.size()>50)	target=aimAdvanced(missile);
        else	target=aimTarget(missile);
        
        int result = fire(target.x,target.y); //相手マスの取得数記憶
        checkLog(result,target,missile.getType()); //ラインミサイルの判別
        memory=deepCopy(mapLog.get(0)); //判っている最適解の一つで上書き(aimTarget向処理)
        
        count++;
    }
    
    private boolean saveLog(int[][] buff){ //size肥大化によるメモリ超過を防ぐ　!! O(n!)? 非常に低速 !!
        for(int j=0;j<mapLog.size();j++){
            if(Arrays.deepEquals(buff,mapLog.get(j))){
                return false;
            }
        }
        return true;
    }
    
    private void checkLog(int res,Point targ,int mType){ //fireの値から確実なlogのみ残す
        if(mType==1 && line_VH) mType=MISSILE_L_VERTICAL;
        for(int tester=0;tester<mapLog.size();tester++){
            ExPointSet checkSet = new ExPointSet();
            getExp(getArea(mType),checkSet,targ.x,targ.y,FIREEXP,mapLog.get(tester));
            if(res!=checkSet.getExp()){
                mapLog.remove(tester);
                tester-=1;
            }
            else{  addMemory(targ,mType,GETPOINT*2,mapLog.get(tester));
            }
        }
    }
    
    private void addLog(int x,int y,int mType,int[][] buff){ //mapLogにmapを追加
        if(mType==1){
            int[][] buffV=deepCopy(buff);
            addMemory(new Point(x,y),MISSILE_L_VERTICAL,-GETPOINT*2,buffV);
            if(saveLog(buffV)) mapLog.add(deepCopy(buffV));
        }
        addMemory(new Point(x,y),mType,-GETPOINT*2,buff);
        if(saveLog(buff)) mapLog.add(deepCopy(buff));
    }
    
    private void setLog(Point enemyP,int mType){ //敵ミサイルを受け取って、可能性のあるマップを生成
        int[][] virtualMap=new int[SIZE][SIZE];
        int range = mapLog.size();
        int bar=0;
        if(range==0) { //初生成
            for(int x=(enemyP.x)-1;x<=(enemyP.x)+1;x++){
                for(int y=(enemyP.y)-1;y<=(enemyP.y)+1;y++){
                    if (0 <= x&& x < SIZE && 0 <= y && y < SIZE)
                        addLog(x,y,mType,deepCopy(virtualMap));
                }}
        }
        
        else if((range>5000 || count >(10 +power)) && power < 5){ //範囲が大きすぎるorPサーチが余っている
            Point pP=powerSearchTarget();
            for(int i=0;i<range;i++){
                addLog(pP.x,pP.y,mType,deepCopy(mapLog.get(0))); //ラインミサイル対策のためaddLogを経由
                mapLog.remove(0);
            }
            power++;
        }
        
        else{
            for(int i=0;i<range;i++){
                for(int x=(enemyP.x)-1;x<=(enemyP.x)+1;x++){
                    for(int y=(enemyP.y)-1;y<=(enemyP.y)+1;y++){
                        if (0 <= x&& x < SIZE && 0 <= y && y < SIZE)
                            addLog(x,y,mType,deepCopy(mapLog.get(0)));
                    }}
                /*
                 if((i*50/range) >bar){ //確認用、一本で2%進行
                 System.out.print("|");
                 bar++;
                 }
                 */
                mapLog.remove(0);
            }
        }
    }
    
    private int[][] deepCopy(int[][] source){ //シャローコピー回避
        int[][] buff = new int[SIZE][SIZE];
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                buff[i][j]=source[i][j];
            }
        }
        return buff;
    }
    
    private void addMemory(Point point,int missile,int addType,int[][] selectMap){ //メモリの操作
        int x=point.x; int y=point.y;
        area = getArea(missile); //missile#getTypeはintで返ってくる
        if(0>x) x=0;	else if(x>SIZE) x=SIZE-1;  //盤上に落とし込む(line30付近 敵memory向処理)
        if(0>y) y=0;	else if(y>SIZE) y=SIZE-1;
        
        for(Point p:area){
            if (0  <= x+p.x && x+p.x < SIZE  && 0  <= y+p.y && y+p.y < SIZE ){ //Field#isInFieldと等価
                if(addType<0 && selectMap[x+p.x][y+p.y]>0)  selectMap[x+p.x][y+p.y] = -GETPOINT ; //上書きされたかもしれない処理
                selectMap[x+p.x][y+p.y] += addType;
                if(selectMap[x+p.x][y+p.y]>GETPOINT)selectMap[x+p.x][y+p.y]=GETPOINT; //想定する範囲に収める
                else if(selectMap[x+p.x][y+p.y]<-GETPOINT)selectMap[x+p.x][y+p.y]=-GETPOINT;
            }}
    }
    
    private Point aimTarget(Missile missile){ //射撃地点の思考
        ExPointSet targetSet =new ExPointSet();
        int missType = missile.getType();
        
        getMaxExp(getArea(missType),targetSet,memory);
        if(missType==1){ //ラインミサイルを取得した時、水平垂直の両者について走査
            ExPointSet epSetV =new ExPointSet();
            getMaxExp(getArea(MISSILE_L_VERTICAL),epSetV,memory);
            if(epSetV.getExp()>targetSet.getExp()){
                missile.setVertical();
                targetSet=epSetV;
                line_VH=true;
            }
            else{ missile.setHorizontal();
                line_VH=false;}
        }
        return targetSet.getAns();
    }
    
    private Point aimAdvanced(Missile missile){
        ExPointSet targetSet =new ExPointSet();
        int missType = missile.getType();
        int mSize=mapLog.size();
        int[][] vMap=new int[SIZE][SIZE];
        for(int cnt=0;cnt<mSize;cnt++){
            for(int i=0;i<SIZE;i++){ //保持する全てのマップデータを統合
                for(int j=0;j<SIZE;j++){
                    vMap[i][j]+=(mapLog.get(cnt))[i][j];
                }
            }
        }
        for(int i=0;i<SIZE;i++){ //値をならす
            for(int j=0;j<SIZE;j++){
                if(vMap[i][j]>mSize/5) vMap[i][j]=1;
                else if(vMap[i][j]<-mSize/5) vMap[i][j]=-1;
                else vMap[i][j]=0;
            }
        }
        
        getMaxExp(getArea(missType),targetSet,vMap);
        if(missType==1){ //ラインミサイルを取得した時、水平垂直の両者について走査
            ExPointSet epSetV =new ExPointSet();
            getMaxExp(getArea(MISSILE_L_VERTICAL),epSetV,vMap);
            if(epSetV.getExp()>targetSet.getExp()){
                missile.setVertical();
                targetSet=epSetV;
                line_VH=true;
            }
            else{ missile.setHorizontal();
                line_VH=false;}
        }
        return targetSet.getAns();
    }
    
    private void getMaxExp(Point[] area,ExPointSet epSet,int[][] slctMap){
        for(int x=0;x<SIZE;x++){ //全てのマスに対して期待値を走査
            for(int y=0;y<SIZE;y++){
                getExp(area,epSet,x,y,MYEXP,slctMap);
            }
        }
    }
    
    private void getMaxExp(Point[] area,ExPointSet epSet,Point enemyP){ //相手のミサイルの期待値を走査
        for(int x=(enemyP.x)-1;x<=(enemyP.x)+1;x++){
            for(int y=(enemyP.y)-1;y<=(enemyP.y)+1;y++){
                if(x>=0 && y>=0 && x<SIZE && y <SIZE)
                {
                    getExp(area,epSet,x,y,ENEMYEXP,memory);
                }
            }
        }
    }
    
    private void getExp(Point[] area,ExPointSet tepSet,int x,int y,int type,int[][] targetMap){
        int buff =0; //type がfalseで相手の走査
        for(Point p:area){
            if (0 <= x+p.x && x+p.x < SIZE && 0 <= y+p.y && y+p.y < SIZE)
                if(type==0) buff+= - targetMap[x+p.x][y+p.y];
                else if(type==1)     buff+= targetMap[x+p.x][y+p.y];
                else if(type==2)if(targetMap[x+p.x][y+p.y]==-1) buff+= - targetMap[x+p.x][y+p.y];
        }
        if(buff > tepSet.getExp()){
            tepSet.setAns(new Point(x,y));
            tepSet.setExp(buff);
        }
    }
    
    
    private Point[] getArea(int type){ //missileTypeをキーにtypeMap内からミサイル範囲を得る
        return typeMap.type.get(type);
    }
    
    void showMemory(int[][] select){
        for(int i = 0;i<SIZE;i++){
            for(int j = 0;j<SIZE;j++){
                if     (select[j][i] ==-1) System.out.print("[●]");
                else if(select[j][i] ==1)  System.out.print("[○]");
                else if(select[j][i] ==0)  System.out.print("[ ]");
                else System.out.print("["+ select[i][j] +"]");
		    }
            System.out.println("");
        }
        System.out.println("");
    }
    
    void memoryFlesh(){ //初期化
	    memory =new int[SIZE][SIZE];
	    count=0;
	    turn = 1;
	    mapLog.clear();
	    power=0;
    }
    
}



class ExPointSet{
    private Point ans;
    private int exp;
    
    ExPointSet(){exp=0; ans = new Point();}
    
    public Point getAns(){
        return ans;
    }
    
    public void setAns(Point buff){
        ans=buff;
    }
    
    public int getExp(){
        return exp;
    }
    
    public void setExp(int buff){
        exp=buff;
    }
    
}


//missileType参照用マップの生成、内容は各missile派生クラスから
class SetMissType extends HashMap{ 
    HashMap<Integer,Point[]> type = new HashMap<Integer,Point[]>();
	
    SetMissType(){
        Point[] AREA1 = { //水平方向ラインミサイル
            new Point(-3, 0), new Point(-2, 0), new Point(-1, 0), new Point(0, 0),
            new Point(1, 0), new Point(2, 0), new Point(3, 0)
        };
        type.put(Missile.MISSILE_TYPE_LINE,AREA1);
        
        Point[] AREA2 = {
            new Point(-1, 0), new Point(0, 0), new Point(1, 0),
            new Point(0, -1), new Point(0, 1)
        };
        type.put(Missile.MISSILE_TYPE_PLUS,AREA2);
        
        Point[] AREA3 = {
            new Point(-1, -1), new Point(0, 0), new Point(1, 1),
            new Point(1, -1), new Point(-1, 1)
        };	
        type.put(Missile.MISSILE_TYPE_X,AREA3);
        
        Point[] AREA4 = {
            new Point(-2, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),
            new Point(0, -2), new Point(0, -1), new Point(0, 1), new Point(0, 2)
        };
        type.put(Missile.MISSILE_TYPE_BIGPLUS,AREA4);
        
        Point[] AREA5 = {
            new Point(-2, -2), new Point(-1, -1), new Point(1, 1), new Point(2, 2),
            new Point(2, -2), new Point(1, -1), new Point(-1, 1), new Point(-2, 2)
        };
        type.put(Missile.MISSILE_TYPE_BIGX,AREA5);
        
        Point[] AREA6 = { //垂直方向ラインミサイル
            new Point(0, -3), new Point(0, -2), new Point(0, -1), new Point(0, 0),
            new Point(0, 1), new Point(0, 2), new Point(0, 3)
        };
        type.put(6,AREA6);
        
    }
    
}
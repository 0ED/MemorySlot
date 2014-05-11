import robobattler2.*;
/**
 Roboクラスを継承し、actionメソッドを実装して作成したクラス。
 */

/*
 自分の攻撃と相手の攻撃から仮想のフィールドを作成し、更地の重要度を2、敵の陣地の重要度を３にして
 与えられたミサイルで各座標に爆撃した場合に最も効率の良い座標を探索して、そこに爆撃を行うようにした。
 ミサイルのうちでBIG〜系は範囲が広く強力なのでパワーサーチを使い確実に奪い返す。また最後の追い込みとして
 最終ターン付近に残ったパワーサーチを集中的に使用して守りを固める。
 敵のLINEミサイルについては方向の察知が出来ないためマップ上下端の場合横方向、左右端の場合縦方向、その他は十字に爆撃を予想している。
 
 */
public class Robo_144029 extends Robo {
    static final int MINE=0,ENEMY=3;//射撃位置の判定の際の重要度
    static private int[][] field = new int[Field.SIZE][Field.SIZE];//敵味方のミサイル情報から仮想フィールドを作成
    static private int turn = 1;//現在何ターン目かを記憶
    static private int leftPowerSearch;
    /**
     Roboクラスのactionメソッドを実装したもの　ロボットの動作を決める。
     @param missile 受け取ったMissileクラスのオブジェクト
     */
    protected void action(Missile missile){
        if(turn == 1){//最初のターンに仮想フィールド初期化
            for(int i=0;i<Field.SIZE;i++)
                for(int j=0;j<Field.SIZE;j++)
                    field[i][j]=2;//重要度は2
            leftPowerSearch = 5;
        }
        enemyAttack();//相手のミサイルの結果をフィールドに反映
        attack(missile);//取得したミサイルで攻撃
        /*for(int i=0;i<9;i++){
         for(int j=0;j<9;j++)
         System.out.print(field[j][i]);
         System.out.println("");
         }*///デバッグ
        turn++;//ターン数を＋１
        if(turn == 17)//一回の戦闘が終われば１ターン目に戻す
            turn = 1;
    }
    /**
     敵ロボットの爆撃した範囲を仮想フィールドに反映する
     */
    protected void enemyAttack(){
        java.awt.Point enemyMis=null;//相手のミサイルの座標情報
        int ePosX=0,ePosY=0;//相手ミサイルのx,y座標
        int enemyMisType=0;//相手が撃ったミサイルのタイプ
        enemyMis = searchTarget();
        if(enemyMis!=null){//相手が打たなかった場合は何もしない
            enemyMisType = searchType();//タイプを取得
            if(enemyMisType==4 || enemyMisType==5 || (17-turn) == leftPowerSearch){//
                enemyMis = powerSearchTarget();//パワーサーチを使用
                leftPowerSearch--;
                //System.out.println("use PowerSearch.");
            }
            else
                enemyMis = searchTarget();//通常サーチ
            ePosX = (int)enemyMis.getX();//x座標と
            ePosY = (int)enemyMis.getY();//y座標をそれぞれint型で取得
            
            //System.out.println(enemyMisType);
            //System.out.println(ePosX + " "+ePosY);
            switch(searchType()){//ミサイルのタイプ毎に場合分け
                case 1://LINEMISSILE
                    if(ePosX>6 ||ePosX<2){
                        for(int j=-3;j<4;j++){
                            if(ePosX>8 ||ePosX<0 ||ePosY+j>8 || ePosY+j<0) continue;
                            field[ePosX][ePosY+j]=ENEMY;
                        }
                    }
                    else if(ePosY>6 || ePosY<2){
                        for(int i=-3;i<4;i++){
                            if(ePosX+i>8 ||ePosX+i<0 ||ePosY>8 || ePosY<0) continue;
                            field[ePosX+i][ePosY]=ENEMY;
                        }
                    }
                    else{
                        for(int i=-1;i<2;i++){
                            if(ePosX+i>8 ||ePosX+i<0 ||ePosY>8 || ePosY<0) continue;
                            field[ePosX+i][ePosY]=ENEMY;
                        }
                        for(int j=-1;j<2;j++){
                            if(ePosX>8 || ePosX<0 || ePosY+j>8 || ePosY+j<0) continue;
                            field[ePosX][ePosY+j]=ENEMY;
                        }
                    }
                    break;
                case 2://PLUS
                    for(int i=-1;i<2;i++){
                        if(ePosX+i>8 ||ePosX+i<0 ||ePosY>8 || ePosY<0) continue;
                        field[ePosX+i][ePosY]=ENEMY;
                    }
                    for(int j=-1;j<2;j++){
                        if(ePosX>8 || ePosX<0 || ePosY+j>8 || ePosY+j<0) continue;
                        field[ePosX][ePosY+j]=ENEMY;
                    }
                    break;
                case 3://X
                    for(int i=-1;i<2;i++){
                        if(ePosX+i>8 || ePosX+i<0 || ePosY+i>8 || ePosY+i<0) continue;
                        field[ePosX+i][ePosY+i]=ENEMY;
                    }
                    for(int j=1;j>-2;j--){
                        if(ePosX+j>8 || ePosX+j<0 || ePosY-j>8 || ePosY-j<0) continue;
                        field[ePosX+j][ePosY-j]=ENEMY;
                    }
                    break;
                case 4://BIGPLUS
                    for(int i=-2;i<3;i++){
                        if(i==0)
                            continue;
                        if( ePosX+i>8 || ePosX+i<0 || ePosY>8 ||ePosY<0) continue;
                        field[ePosX+i][ePosY]=ENEMY;
                    }
                    for(int j=-2;j<3;j++){
                        if(j==0)
                            continue;
                        if(ePosX>8 ||ePosX<0 || ePosY+j>8 || ePosY+j<0) continue;
                        field[ePosX][ePosY+j]=ENEMY;
                    }
                    break;
                case 5://BIGX
                    for(int i=-2;i<3;i++){
                        if(i==0)
                            continue;
                        if(ePosX+i>8 || ePosX+i<0 || ePosY+i>8 || ePosY+i<0) continue;
                        field[ePosX+i][ePosY+i]=ENEMY;
                    }
                    for(int j=2;j<-3;j--){
                        if(j==0)
                            continue;
                        if(ePosX+j>8 || ePosX+j<0 || ePosY+j>8 || ePosY-j<0) continue;
                        field[ePosX+j][ePosY-j]=ENEMY;
                    }
                    break;
                default://相手が打たなかった場合は更新なし
                    break;
            }
        }
        //else
        //  System.out.println("null");
    }
    /**
     受け取ったミサイルで爆撃を行う。
     */
    protected void attack(Missile missile){
        int misType=missile.getType();//受け取ったミサイルの種類を取得
        int x,y;//射撃座標のx,y
        boolean isVertical = false;
        java.awt.Point aim;
        if(misType == 1){//lineMissileの場合は縦横の判断
            aim = mapScan(misType);
            isVertical = horOrVer();
            if(isVertical == true)
                missile.setVertical();
            else
                missile.setHorizontal();
        }
        else
            aim = mapScan(misType);//mapScanで最適な座標を得る
        x = (int)aim.getX();//x座標と
        y = (int)aim.getY();//y座標を取得
        
        switch(misType){//ミサイルのタイプで場合分け
            case 1://LINEMISSILE
                fire(x,y);//取得したx,y座標に攻撃
                if(isVertical == true){
                    for(int j=-3;j<4;j++){
                        if(y+j>8 ||y+j<0) continue;
                        field[x][y+j]=MINE;//爆撃範囲を自分の陣地に
                    }
                }
                else{
                    for(int i=-3;i<4;i++){
                        if(x+i>8 ||x+i<0) continue;
                        field[x+i][y]=MINE;//爆撃範囲を自分の陣地に
                    }
                }
                break;
            case 2://PLUS
                fire(x,y);
                for(int i=-1;i<2;i++){
                    if(x+i>8 ||x+i<0 ||y>8 || y<0) continue;
                    field[x+i][y]=MINE;
                }
                for(int j=-1;j<2;j++){
                    if(x>8 || x<0 || y+j>8 || y+j<0) continue;
                    field[x][y+j]=MINE;
                }
                break;
            case 3://X
                fire(x,y);
                for(int i=-1;i<2;i++){
                    if(x+i>8 || x+i<0 || y+i>8 || y+i<0) continue;
                    field[x+i][y+i]=MINE;
                }
                for(int j=1;j>-2;j--){
                    if(x+j>8 || x+j<0 || y-j>8 || y-j<0) continue;
                    field[x+j][y-j]=MINE;
                }
                break;
            case 4://BIGPLUS
                fire(x,y);
                for(int i=-2;i<3;i++){
                    if(i==0)
                        continue;
                    if( x+i>8 || x+i<0 || y>8 ||y<0) continue;
                    field[x+i][y]=MINE;
                }
                for(int j=-2;j<3;j++){
                    if(j==0)
                        continue;
                    if(x>8 ||x<0 || y+j>8 || y+j<0) continue;
                    field[x][y+j]=MINE;
                }
                break;
            case 5://BIGX
                fire(x,y);
                for(int i=-2;i<3;i++){
                    if(i==0)
                        continue;
                    if(x+i>8 || x+i<0 || y+i>8 || y+i<0) continue;
                    field[x+i][y+i]=MINE;
                }
                for(int j=2;j<-3;j--){
                    if(j==0)
                        continue;
                    if(x+j>8 || x+j<0 || y+j>8 || y-j<0) continue;
                    field[x+j][y-j]=MINE;
                }
                break;
        }
    }
    /**
     最も有効な爆撃座標を探索する。
     @param misType 受け取ったミサイルの種類
     @return 期待値が最大の座標データ
     */
    protected java.awt.Point mapScan(int misType){//最も期待値の高い爆撃座標を求める。
        int mean=0,maxMean=0;//最大期待値を求めるために宣言
        java.awt.Point result = new java.awt.Point(0,0);//探索結果
        switch(misType){
            case 1://LINEMISSILE
                for(int x=0;x<9;x++){
                    for(int y=0;y<9;y++){
                        for(int i=-3;i<4;i++){//横方向
                            if(x+i>8 ||x+i<0) continue;
                            mean+=field[x+i][y];//爆撃座標の値を加算していく
                        }
                        if(mean>maxMean){//期待値が最大より高ければ
                            maxMean=mean;//この座標を最大の期待値として
                            result.setLocation(x,y);//この座標を探索結果にする
                        }
                        mean = 0;//期待値を初期化
                        for(int j=-3;j<4;j++){//縦方向
                            if(y+j>8 ||y+j<0) continue;
                            mean+=field[x][y+j];//爆撃座標の値を加算していく
                        }
                        if(mean>maxMean){//期待値が最大より高ければ
                            maxMean=mean;//この座標を最大の期待値として
                            result.setLocation(x,y);//この座標を探索結果にする
                        }	
                        mean = 0;//期待値を初期化
                    }
                }
                break;
            case 2://PLUS
                for(int x=0;x<9;x++){
                    for(int y=0;y<9;y++){	    
                        for(int i=-1;i<2;i++){
                            if(x+i>8 ||x+i<0 || y>8 || y<0) continue;
                            mean+=field[x+i][y];
                        }
                        for(int j=-1;j<2;j++){
                            if(x>8 || x<0 || y+j>8 || y+j<0) continue;
                            mean+=field[x][y+j];		
                        }
                        if(mean>maxMean){
                            maxMean=mean;
                            result.setLocation(x,y);
                        }		
                        mean = 0;
                    }
                }
                break;
            case 3://X
                for(int x=0;x<9;x++){
                    for(int y=0;y<9;y++){
                        for(int i=-1;i<2;i++){
                            if(x+i>8 || x+i<0 || y+i>8 || y+i<0) continue;
                            mean+=field[x+i][y+i];
                        }
                        for(int j=1;j>-2;j--){
                            if(x+j>8 || x+j<0 || y-j>8 || y-j<0) continue;
                            mean+=field[x+j][y-j];
                        }
                        // System.out.println(mean + " " +maxMean);
                        if(mean>maxMean){
                            maxMean=mean;
                            result.setLocation(x,y);	
                        }
                        mean = 0;
                        // System.out.print(result.getX()+" "+result.getY()+"\n");
                    }
                }
                break;
            case 4://BIGPLUS
                for(int x=0;x<9;x++){
                    for(int y=0;y<9;y++){
                        for(int i=-2;i<3;i++){
                            if(i==0)
                                continue;
                            if( x+i>8 || x+i<0 || y>8 ||y<0) continue;
                            mean+=field[x+i][y];
                        }
                        for(int j=-2;j<3;j++){
                            if(j==0)
                                continue;
                            if(x>8 ||x<0 || y+j>8 || y+j<0) continue;
                            mean+=field[y][y+j];
                        }
                        if(mean>maxMean){
                            maxMean=mean;
                            result.setLocation(x,y);	
                        }	    
                        mean = 0;
                    }
                }
                break;
            case 5://BIGX
                for(int x=0;x<9;x++){
                    for(int y=0;y<9;y++){
                        for(int i=-2;i<3;i++){
                            if(i==0)
                                continue;
                            if(x+i>8 || x+i<0 || y+i>8 || y+i<0) continue;
                            mean+=field[x+i][y+i];
                        }
                        for(int j=2;j<-3;j--){
                            if(j==0)
                                continue;
                            if(x+j>8 || x+j<0 || y-j>8 || y-j<0) continue;
                            mean+=field[x+j][y-j];
                        }
                        if(mean>maxMean){
                            maxMean=mean;		
                            result.setLocation(x,y);
                        }
                        mean = 0;
                    }
                }
                break;	 
        }
        //	System.out.print(result.getX()+" "+result.getY()+"\n");
        return result;//探索結果を返す
    }   
    /**
     lineミサイルの場合に縦か横どちらで攻撃するかを判断する。
     @return 爆撃方向が縦か横か（boolean型）
     */
    protected boolean horOrVer(){//lineMissileの場合に縦か横か判断
        int mean=0,maxMean=0;//最大期待値を求めるために宣言
        boolean isVertical=false;
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                for(int i=-3;i<4;i++){
                    if(x+i>8 ||x+i<0) continue;
                    mean+=field[x+i][y];//爆撃座標の値を加算していく
                }
                if(mean>maxMean){//期待値が最大より高ければ
                    maxMean=mean;//この座標を最大の期待値として
                    isVertical=false;//横方向でこの座標を探索結果にする
                }	
                mean = 0;//期待値を初期化
            }
        }
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                for(int j=-3;j<4;j++){
                    if(y+j>8 ||y+j<0) continue;
                    mean+=field[x][y+j];//爆撃座標の値を加算していく
                }
                if(mean>maxMean){//期待値が最大より高ければ
                    maxMean=mean;//この座標を最大の期待値として
                    isVertical=true;//縦方向でこの座標を探索結果にする
                }	
                mean = 0;//期待値を初期化
            }
        }
        return isVertical;
    }
}
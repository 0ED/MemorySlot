import robobattler2.*; //ロボバトラー2のパッケージをimport
import java.awt.Point; //Point型のライブラリをimport

public class DarkAngel extends Robo {
    
    // フィールド
    public static final int EMPTY = 0;
    public static final int ME = 1;
    public static final int YOU = 2;
    private static final int FINAL_TURN = 16;
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private static ForestedField forestedField = new ForestedField();
    private static MemoryData memory = new MemoryData();
    private Rom rom = new Rom();
    
    /**
     メインメソッド
     @param missile ミサイルクラスのポインタ
     */
    protected void action(Missile missile) {
        init();
        Point target = myBrain(missile); //思考ルーチン
        memory.setSenryo( fire(target.x, target.y) );
        show();
        memory.increaseTurn();
    }
    
    
    /**
     思考ルーチン
     @param missile 自分のミサイル名
     @return 自分の爆撃ポイント
     */
    private Point myBrain(Missile missile) {
        Point myTarget;
        
        memory.setMyType(missile.getType()); //自分の武器をメモリに書き込む
        if(searchTarget() == null) { //相手が打ってこないとき
            myTarget = maxSearchTarget(missile);
        }
        else { //相手が打ったとき
            forestedField.youMapping(searchType(), powerSearchTarget()); //メモリに書き込み、マップを描く
            myTarget = maxSearchTarget(missile); //自分の爆撃範囲を決める。そして、縦横も決める
        }
        forestedField.myMapping(myTarget); //自分の爆撃範囲をメモリに書き込み、マップを描く
        //forestedField.maxMapping();
        return myTarget;
    }
    
    
    /**
     マックスサーチターゲット
     @param missile 自分のミサイル名
     @return 自分の爆撃ポイント
     */
    private Point maxSearchTarget(Missile missile) {
        
        MissileArea missileArea = new MissileArea();
        if(missile.getType() == Missile.MISSILE_TYPE_LINE) { //LINEなら
            Point[] area1 = missileArea.getArea(Missile.MISSILE_TYPE_LINE, VERTICAL); //縦打ち
            Point[] area2 = missileArea.getArea(Missile.MISSILE_TYPE_LINE, HORIZONTAL); //横打ち
            if(search(area1).getMax() > search(area2).getMax()) { //縦打ちが多くとれるなら
                missile.setVertical(); //縦打ちに設定
                memory.setMyLineData(VERTICAL);
                return search(area1).getPoint();
            }
            else { //横打ちが多くとれるなら
                missile.setHorizontal(); //横打ちに設定
                memory.setMyLineData(HORIZONTAL);
                return search(area2).getPoint();
            }
        }
        else { //LINE以外
            Point[] area = missileArea.getArea(missile.getType(), HORIZONTAL);
            return search(area).getPoint();
        }
    }
    
    
    /**
     サーチ
     @param area 自分のミサイルエリア
     @return 自分の爆撃ポイント、最大爆撃パネル数
     */
    private Rom search(Point[] area) {
        int[][] field = forestedField.getForestedField();
        int[] panelCount = new int[3];
        int max=0;
        
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                for(int i=0; i<panelCount.length; i++) panelCount[i]=0; //パネルカウント初期化
                for (Point p : area) {
                    if ( (0 <= (x+p.x) && (x+p.x) < 9) && (0 <= (y+p.y) && (y+p.y) < 9) ) {
                        if(field[x+p.x][y+p.y] == YOU) panelCount[YOU]++;
                        else if(field[x+p.x][y+p.y] == ME) panelCount[ME]++;
                        else if(field[x+p.x][y+p.y] == EMPTY) panelCount[EMPTY]++;
                    }
                }
                if(  ( (2*panelCount[YOU]) + panelCount[EMPTY] ) > max  ) {
                    max = (2*panelCount[YOU]) + panelCount[EMPTY];
                    rom.set(max,new Point(x,y));
                }
            }
        }
        return rom;
    }
    
    
    
    /**
     初期化メソッド
     */
    private void init() {
        if(memory.getNowTurn() >= FINAL_TURN) {
            forestedField = new ForestedField();
            memory = new MemoryData();
        }
    }
    
    /**
     デバッグ用出力メソッド
     */
    private void show() {
        System.out.println("---------DarkAngel-------");
        forestedField.show();
        //memory.show();
    }
}


package DarkAngel;
import robobattler2.*; //ロボバトラー2のパッケージをimport
import java.awt.Point; //Point型のライブラリをimport

public class DarkAngel extends Robo {
    
    // フィールド
    private static final int FINAL_TURN = 16;
    private static ForestedField forestedField = new ForestedField();
    private static MemoryData memory = new MemoryData();
    
    /**
     メインメソッド
     @param missile ミサイルクラスのポインタ
     */
    protected void action(Missile missile) {
        init();
        Point target = myBrain(missile); //思考ルーチン
        System.out.println("myTarget(x,y)="+target.x+", "+target.y);
        fire(target.x, target.x);
        show();
        memory.increaseTurn();
    }
    
    
    /**
     思考ルーチン
     @param missile 自分の爆撃ポイント
     @return 自分の爆撃ポイント
     */
    private Point myBrain(Missile missile) {
        Point myTarget,youTarget;
        
        if(searchTarget() == null) { //相手が打ってこないとき
            youTarget = searchTarget();
            myTarget = new Point(3,2);
        }
        else { //相手が打ったとき
            youTarget = powerSearchTarget();
            System.out.println("youTarget(x,y)="+youTarget.x+", "+youTarget.y);
            myTarget = youTarget;
        }
        memory.setNowData(missile.getType(), searchType(), false, false, myTarget, youTarget); //記憶
        forestedField.allMapping(); //記憶をもとにマッピング
        return myTarget;
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
        memory.show();
    }
}


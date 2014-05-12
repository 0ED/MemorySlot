import robobattler2.*; //ロボバトラー2のパッケージをimport
import java.awt.Point; //Point型のライブラリをimport
public class MemoryData {

    //フィールド
    public static final int ME = 1;
    private static int turn = 0;
    private static int[] myMissiles;
    private static int[] youMissiles;
    private static boolean[] myLineVerticals;
    private static boolean[] youLineVerticals;
    private static Point[] myTargets;
    private static Point[] youTargets;
    private static int[][][] forestedSenryoCount;
    private static int[] senryoCount;

    MemoryData() {
        turn = 0;
        myMissiles = new int[16];
        youMissiles = new int[16];
        myLineVerticals = new boolean[16];
        youLineVerticals = new boolean[16];
        myTargets = new Point[16];
        youTargets = new Point[16];
        forestedSenryoCount = new int[3][3][16];
        senryoCount = new int[16];
    }
    
    //自分のデータベース
    void setMyType(int myType) {
        myMissiles[turn] = myType;
    }
    void setMyTarget(Point myTarget) {
        myTargets[turn] = myTarget;
    }
    void setMyLineData(boolean myLineVertical) {
        myLineVerticals[turn] = myLineVertical;
    }
    void setForestedSenryo(int count, int x_around, int y_around, int t) {
        forestedSenryoCount[x_around+1][y_around+1][t] = count;
    }
    void setSenryo(int count) {
        senryoCount[turn] = count;
    }
    
    //相手のデータベース
    void setYouType(int youType) {
        youMissiles[turn] = youType;
    }
    void setYouTarget(Point youTarget) {
        youTargets[turn] = youTarget;
    }
    void setYouLineData(boolean youLineVertical) {
        youLineVerticals[turn] = youLineVertical;
    }
    
    //ターン数
    void increaseTurn() {turn++;}
    
    
    int getNowTurn() {return turn;}
    int getForestedSenryo(int x, int y, int t) {return forestedSenryoCount[x][y][t];}
    int getSenryo(int t) {return senryoCount[t];}
    int getMissile(int who, int t) {
        if(who == ME) return myMissiles[t];
        else return youMissiles[t];
    }
    
    boolean getLineVertical(int who, int t) {
        if(who == ME) return myLineVerticals[t];
        else return youLineVerticals[t];
    }
    
    Point getTarget(int who, int t) {
        if(who == ME) return myTargets[t];
        else return youTargets[t];
    }

    
    void show() {
        for(int t=0; t<=turn; t++) {
            System.out.println("myWeapon="+myMissiles[t]+" youWeapon="+youMissiles[t]+" myTarget="+myTargets[t]+" youTarget="+youTargets[t]);
        }
    }
}
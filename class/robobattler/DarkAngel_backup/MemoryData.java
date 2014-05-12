package DarkAngel;
import robobattler2.*; //ロボバトラー2のパッケージをimport
import java.awt.Point; //Point型のライブラリをimport
public class MemoryData {

    //フィールド
    private static final int ME = 1;
    private static int turn = 0;
    private static int[] myMissiles;
    private static int[] youMissiles;
    private static boolean[] myLineVerticals;
    private static boolean[] youLineVerticals;
    private static Point[] myTargets;
    private static Point[] youTargets;
    
    MemoryData() {
        turn = 0;
        myMissiles = new int[16];
        youMissiles = new int[16];
        myLineVerticals = new boolean[16];
        youLineVerticals = new boolean[16];
        myTargets = new Point[16];
        youTargets = new Point[16];
    }
    void setNowData(int myMissile, int youMissile, boolean myLineVertical, boolean youLineVertical, Point myTarget, Point youTarget) {
        myMissiles[turn] = myMissile;
        youMissiles[turn] = youMissile;
        myLineVerticals[turn] = myLineVertical; //LINE
        youLineVerticals[turn] = youLineVertical;
        myTargets[turn] = myTarget;
        youTargets[turn] = youTarget;
    }
    void increaseTurn() {turn++;} //ターン数を数える
    
    
    int getNowTurn() {return turn;}
    
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
package DarkAngel;
import robobattler2.*;
import java.awt.Point;

public class ForestedField extends MemoryData {
    
    //フィールド
    private static final int ME = 1;
    private static final int YOU = 2;
    private static final int FIELD_WIDTH = Field.SIZE; // 9
    private static final int FIELD_HEIGHT = Field.SIZE; // 9
    private int[][] field = new int[FIELD_WIDTH][FIELD_HEIGHT]; //思考中予測フィールド
    private MissileArea missileArea = new MissileArea(); //武器エリア記憶
    
    
    ForestedField() {
        for(int y = 0; y < FIELD_HEIGHT; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                field[x][y] = 0;
            }
        }
    }
    
    void allMapping() {
        for(int t = 0; t <= getNowTurn(); t++) {
            if(getTarget(YOU,t) != null) { //相手が爆撃してたら
                mapping(YOU,t);
            }
            mapping(ME,t); //自分は絶対爆撃
        }
    }
    
    private void mapping(int who, int t) {
        Point[] area = missileArea.getArea(getMissile(who,t), getLineVertical(who,t));
        for (Point p : area) {
            int x = getTarget(who,t).x + p.x;
            int y = getTarget(who,t).y + p.y;
            if ( (0 <= x && x < 9) && (0 <= y && y < 9) ) {
                field[x][y] = who;
            }
        }
    }
    
    void show() {
        for(int y = 0; y < FIELD_HEIGHT; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                System.out.printf("%d ", field[x][y]);
            }
            System.out.println();
        }
    }
    
    
    
}
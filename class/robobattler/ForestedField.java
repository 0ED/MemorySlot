import robobattler2.*;
import java.awt.Point;

public class ForestedField {
    
    //フィールド
    public static final int ME = 1;
    public static final int YOU = 2;
    private static final int FIELD_WIDTH = Field.SIZE; // 9
    private static final int FIELD_HEIGHT = Field.SIZE; // 9
    private int[][] forestedField = new int[FIELD_WIDTH][FIELD_HEIGHT]; //思考中予測フィールド
    private int[][] repairField = new int[FIELD_WIDTH][FIELD_HEIGHT]; //思考中予測フィールド
    private MemoryData memory = new MemoryData();
    
    ForestedField() {
        for(int y = 0; y < FIELD_HEIGHT; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                forestedField[x][y] = 0;
            }
        }
    }
    
    int[][] getForestedField() {return forestedField;}
    int[][] getRepairField() {return repairField;}
    
    void myMapping(Point target) {
        memory.setMyTarget(target);
        mapping(ME,memory.getNowTurn());
    }
    
    void youMapping(int type, Point target) {
        memory.setYouType(type);
        memory.setYouTarget(target);
        mapping(YOU,memory.getNowTurn());
        senryoCount(memory.getNowTurn());
    }
    
    private void senryoCount(int t) {
        for(int y_around = -1; y_around <= 1; y_around++) {
            for(int x_around = -1; x_around <= 1; x_around++) {
                int count=0;
                Point block = blockTarget(YOU, x_around, y_around, t); //相手の座標の周辺座標を得る
                Point[] area = areaTarget(ME, block.x, block.y, t); //各周辺座標に打ったときの武器エリアを返す
                for (Point p : area) {
                    if ( (0 <= p.x && p.x < FIELD_WIDTH) && (0 <= p.y && p.y < FIELD_HEIGHT) ) {
                        if(forestedField[p.x][p.y] == YOU) count++; //占領数を数える
                    }
                }
                memory.setForestedSenryo(count, x_around, y_around, t);//記憶
            }
        }
    }
    
    private void mapping(int who, int t) {
        Point block = blockTarget(who, 0, 0, t); //自分または相手の座標を得る
        Point[] area = areaTarget(who, block.x, block.y, t); //その座標に打ったときの武器エリアを返す
        for (Point p : area) {
            if ( (0 <= p.x && p.x < FIELD_WIDTH) && (0 <= p.y && p.y < FIELD_HEIGHT) ) {
                forestedField[p.x][p.y] = who;
            }
        }
    }
    
     //自分または相手の座標の周辺座標を得る
    private Point blockTarget(int who, int x_around, int y_around, int t) {
        int x = memory.getTarget(who,t).x + x_around;
        int y = memory.getTarget(who,t).y + y_around;
        return new Point(x,y);
    }
    
    //各周辺座標に打ったときの武器エリアを返す
    private Point[] areaTarget(int who, int x, int y, int t) {
        MissileArea missileArea = new MissileArea();
        Point[] area = missileArea.getArea(memory.getMissile(who,t), memory.getLineVertical(who,t));
        Point[] areaCopy = new Point[area.length];
        for(int i=0; i<area.length; i++) {areaCopy[i] = new Point(x+area[i].x, y+area[i].y);}
        return areaCopy;
    }
    
    
    
    
    
    
    
    
    private Node root = null;
    
    void maxMapping() {
        for(int t=0; t <= memory.getNowTurn(); t++) {
            System.out.println("ターン"+t);
            tree(root);
        }
        
    }
    
    private void tree(Node node) { //node=現在のノード
        if(node == null) {
            node = new Node();
            root = node;
            return;
        }
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++){
                if(memory.getSenryo(root.getTurn()) == memory.getForestedSenryo(x,y,root.getTurn()) ) {
                    node.setNext(root,x,y);
                    tree(node);
                    return;
                }
                else {
                    node.setNext(null,x,y);
                    return;
                }
            }
        }
    }
    

    
    
    
    
    
    
    
    void show() {
        for(int y = 0; y < FIELD_HEIGHT; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                System.out.printf("%d ", forestedField[x][y]);
            }
            System.out.println();
        }
        System.out.println("自分の占領率"+panelPercent()+"%");
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                System.out.printf("%d ", memory.getForestedSenryo(x,y,memory.getNowTurn()) );
            }
            System.out.println();
        }
    }
    
    
    private int panelPercent() {
        int me=0,you=0,other=0;
        for(int y = 0; y < FIELD_HEIGHT; y++){
            for(int x = 0; x < FIELD_WIDTH; x++){
                if(forestedField[x][y] == YOU) you++;
                else if(forestedField[x][y] == ME) me++;
                else other++;
            }
        }
        double mySenryo = (double)me / (double)(FIELD_HEIGHT*FIELD_WIDTH-other);
        return (int)(mySenryo*100);
    }
}
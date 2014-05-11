
import robobattler2.*;
import java.awt.Point;

public class Palira extends Robo {
    
    // フィールド
    private static int[][] ryodo = new int[9][9]; //9×9パネルの領土
    private static int turn = 1;
    private static boolean senko;
    private static int myPowerSearchCount = 0;
    private int myBuki; //直前に自分が打った武器
    private int enemyBuki; //直前に相手が打った武器
    private int x_my; //自分が直前に爆撃した場所
    private int y_my;
    private int x_enemy; //相手が直前に爆撃した場所（およそ）
    private int y_enemy;
    private boolean LineWhich=false; //ラインの縦横（縦がtrue, 横がfalse）
    private static int count=0;
    
    
    //定数（Line=1, Plus=2, X=3, BigPlus=4, BigX=5）
    private final Point[] LineVerticalArea = {
    	new Point(0, -3), new Point(0, -2), new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)
    };
    private final Point[] LineHorizontalArea = {
        new Point(-3, 0), new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)
    };
    private final Point[] PlusArea = {
    new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)
    };
    private final Point[] XArea = {
    new Point(-1, -1), new Point(0, 0), new Point(1, 1), new Point(1, -1), new Point(-1, 1)
    };
    private final Point[] BigPlusArea = {
        new Point(-2, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),
        new Point(0, -2), new Point(0, -1), new Point(0, 1), new Point(0, 2)
    };
    private final Point[] BigXArea = {
        new Point(-2, -2), new Point(-1, -1), new Point(1, 1), new Point(2, 2),
        new Point(2, -2), new Point(1, -1), new Point(-1, 1), new Point(-2, 2)
    };
  
    /** 
        デバッグ用出力メソッド
    */
    private void show() {
        System.out.println("--------ヒトラー--------");
        if(turn == 1) {
            if(senko==true) System.out.println("先攻");
            else System.out.println("後攻");
        }
        System.out.println("ターン数 = "+turn);
//        System.out.println("自分武器:"+myBuki);
//        System.out.println("相手が直前に使った武器:"+enemyBuki);
//        System.out.println("自分の爆撃範囲 = (" + x_my + ", " + y_my + ")");
//        System.out.println("相手の爆撃範囲 = (" + x_enemy + ", " + y_enemy + ")");
//        for(int y = 0; y < 9; y++) {
//            for(int x = 0; x < 9; x++) {
//                System.out.print(ryodo[x][y]+" ");
//            }
//            System.out.println();
//        }
    }
    
    /**
     初期化メソッド
     */
    private void initialize() {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                ryodo[x][y] = 0;
            }
        }
        turn=1;
        senko=false;
    }
    
    /** 
        自分が先攻か後攻か特定するメソッド
    */
    private void SenkoHokan() {
        if(turn == 1) { //1ターン目だけマークをつける事で
            if(searchTarget() == null) senko = true; //trueなら先攻
            else senko = false; //falseなら後攻
        }
    }
    
    /**
     サーチし、相手の(X,Y)座標を特定するメソッド
     */
    private void SearchXY() {
        Point search = searchTarget();
        x_enemy = (int)search.getX();
        y_enemy = (int)search.getY();
    }
    private void PowerSearchXY() {
        Point search = powerSearchTarget();
        x_enemy = (int)search.getX();
        y_enemy = (int)search.getY();
    }
    
    /** 
        引数で与えられた武器の爆撃範囲を返すメソッド
    */
    private Point[] BukiArea(int buki) {
        Point[] area = null;
        if(buki == 1) {
            if(LineWhich == true) area = LineVerticalArea;
            else area = LineHorizontalArea;
        }
        else if(buki == 2) area = PlusArea;
        else if(buki == 3) area = XArea;
        else if(buki == 4) area = BigPlusArea;
        else if(buki == 5) area = BigXArea;
        
        return area;
    }
    
    /**
     自分と相手の武器が一致又は似たような武器かを判断するメソッド
     */
    private boolean bukiSimilar(){
        boolean flag = false;
        if ( (myBuki == enemyBuki)||
            (myBuki == 2 && enemyBuki == 4)||
            (myBuki == 4 && enemyBuki == 2)||
            (myBuki == 3 && enemyBuki == 5)||
            (myBuki == 5 && enemyBuki == 3)||
            (myBuki == 1 && enemyBuki == 4)||
            (myBuki == 4 && enemyBuki == 1) ) {
            if ( !(myBuki == 1 && enemyBuki == 1) ) {
                flag = true;
            }
        }
        return flag;
    }
    
    /** 
        領土分布の測定（およそ）
    */
    private void RyodoMark(boolean me) { //自分と相手の領土にマークをつけるメソッド
        
        //ローカル変数
        int mark;
        int buki,x_point,y_point;
        int x=0,y=0;
        Point[] area = null;
    
        if(me == true) { //自分の領土分布にマークをつけたいなら、true
            buki = myBuki;
            x_point = x_my;
            y_point = y_my;
            mark=1; //自分の領土には1をマーク
        }
        else { //相手の領土分布にマークをつけたいなら、false
            buki = enemyBuki;
            x_point = x_enemy;
            y_point = y_enemy;
            mark=2; //相手の領土には2をマーク
        }
        area = BukiArea(buki);
        
        //領土にマークをつける
        for (int i = 0; i < area.length; i++) {
            x = x_point + area[i].x;
            y = y_point + area[i].y;
            if ( (0 <= x && x < 9) && (0 <= y && y < 9) ) {
                ryodo[x][y] = mark;
            }
        }
    }

    
    
    /**
        領土分布と自分の武器を基に、自分が爆撃する(X,Y)座標を決めるメソッド
    */
    void myBrain() {
        //ローカル変数
        int x_part,y_part;
        Point[] area = null;
        int enemyPanelCount=0,myPanelCount=0,airPanelCount=0;
        int[][] kakusa = new int[9][9];
        int max=0;
        
        area = BukiArea(myBuki);
        
        /*
         「1パネルずつ、自分の武器で爆撃した」と仮定したとき、
         一番領土を獲得できる場所を求める
        */
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                enemyPanelCount = 0;
                myPanelCount = 0;
                airPanelCount = 0;
                for (int i = 0; i < area.length; i++) { //引数「area xy座標」
                    x_part = x + area[i].x;
                    y_part = y + area[i].y;
                    if ( (0 <= x_part && x_part < 9) && (0 <= y_part && y_part < 9) ) {
                        if(ryodo[x_part][y_part] == 2) enemyPanelCount++;
                        else if(ryodo[x_part][y_part] == 1) myPanelCount++;
                        else airPanelCount++;
                    }
                }
                kakusa[x][y] = (2 * enemyPanelCount) + airPanelCount;
                if(kakusa[x][y] > max) max = kakusa[x][y];
                //2*相手パネル + 空パネル。この値が高いほど、一番領土を獲得でき、互いの領土に差が生まれる
                /* 理由：
                 自分と相手の領土を比較したとき、空パネルを取れば自分の領土が1マス増え（格差1）、
                 相手パネルを取れば自分の領土が1マス増え、相手の領土が1マス減る（格差2）、
                 自分パネルを取れば自分の領土は増えない（格差0）
                */
            }
        }
        for(int y = 0; y < 9; y++) { //一旦、一番領土を獲得できる場所を見つけてから、探す
            for(int x = 0; x < 9; x++) {
                if(max == kakusa[x][y]) {
                    x_my = x;
                    y_my = y;
                }
            }
        }
        
    }

    /**
     ターンによって処理を切り替えるメソッド
     */
    private void TurnManager() {
        if(turn <= 5) {
            SearchXY();
        }
        else {
            /*
             相手と似たような武器又は
             11ターン以降は強制的にパワーサーチを使う
            */
            if(bukiSimilar()==true || turn - myPowerSearchCount == 11) { 
                PowerSearchXY();
                myPowerSearchCount++; //自分がパワーサーチを使った数を保管
            }
            else {
                SearchXY();
            }
        }
        RyodoMark(false);
        if(bukiSimilar() == true) { //似たような武器なら
            x_my = x_enemy;
            y_my = y_enemy;
        }
        if(bukiSimilar()==false || turn - myPowerSearchCount == 11) { //似たような武器がないなら
            myBrain();
        }
        RyodoMark(true);
    }
    
    
    /**
     先攻後攻によって処理を切り替えるメソッド
     */
    private void senkoManager() {
        if(senko == true) {
            if(turn == 1) {
                myBrain();
                RyodoMark(true);
            }
            else {
                TurnManager();
            }
        }
        else {
            TurnManager();
        }
    }
    
    
    /** 
        メインメソッド
    */
    protected void action(robobattler2.Missile missile) {
        if(turn >= 17) initialize(); //初期化
        
        //武器特定
        myBuki = missile.getType(); //直前に自分が打った武器
        enemyBuki = searchType(); //直前に相手が打った武器
        
        SenkoHokan(); //自分が先攻か後攻か特定するメソッド
        senkoManager();
        int senryo = fire(x_my,y_my); //占領したパネル数を返す
        show(); //デバッグ用出力
        System.out.println("占領"+senryo);
        
        turn++;
    }
    
}
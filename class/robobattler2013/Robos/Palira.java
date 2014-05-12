/*
 学籍番号： 144704
 氏名： 高橋　右
 プログラム名：「パリラ」
 ファイル名： Palira.java
 提出日： 1月25日
 
 このプログラム名の由来：
    どうでもいいと思うかもしれないが、"せっかく"つけた名前であるから説明したい。
    まず「パリラ」とは、「天然パーマのゴリラ」の略称である。
    そして、「天然パーマのゴリラ」は芸人のクリームシチュー上田晋也のニックネームであり、
    彼は、事実として「パリラ王」と言われているくらい多くの業界を支配している。
    例えば、多くの司会を務めたり、政治家インタビューしたり、海外ドラマTHE4400に出たり、、
    このプログラムも、パリラのように多くの業界（領土）を支配できるプログラムとなっているため
    プログラム名「パリラ」を襲名した。
 
 
 理想として掲げていたプログラムの形：
    相手武器、自分武器、相手の座標（およそ）そして、自分の座標を基に仮のマップを作る。
    そして、SearchTargetの周辺9マスに本当に相手が爆撃した座標があるので
    その周辺9マスを探索（＊）していき、マップを修正する事で正しいマップを得る。
    その後、このプログラムにもあるメソッドmyBrain()を使い、マップを基に一番多く取れる座標を
    自分の座標とし爆撃する。また、Line型ミサイルの縦横による区別も付けて爆撃する。
 
    ＜＊探索方法＞：メソッドの一部を一番下の「付録」に掲示
    SearchTargetの周辺9マスの各マスそれぞれに自分が爆撃したとき、相手パネルを自分パネルに
    書き換えた枚数を数え、3*3の配列に格納（仮の占領枚数）。そして、fireメソッドから得られた相手パネルを
    自分パネルに書き換えた枚数を格納（実際の占領枚数）。
    次に、9分木の木構造を作成し、仮の占領枚数と実際の占領枚数が一致した場合、
    正しい爆撃範囲であるとわかる事から、一致したときnextし、一致しなければnullとなる用にして枝切りする。
    そして、一番深いleafが正しいマップを表しているから、その
 
 
 実際に実現できたプログラムの形：
    相手武器、自分武器、相手の座標（およそ）そして、自分の座標を基に仮のマップを作る。
    その後、このプログラムにもあるメソッドmyBrain()を使い、マップを基に一番多く取れる座標を
    自分の座標とし爆撃する。また、直前の相手武器と自分武器が似たような武器の場合はパワーサーチを使う。
    
 
 工夫した点、アピールポイント（重要！）：
    ＜コードの書き方＞
        ・見やすくするため、細めに関数分けを行った。
        ・関数の名前からどのような動作となるのか想像できるようにした。
        ・分かりにくい動作を長文のコメントとして書いた。
    ＜アルゴリズム＞
        ・領土を取り合うということで、マップを使ったこと。: RyodoMark()
        ・最善の場所に爆撃するようなアルゴリズムを作ったこと。: myBrain()
        ・パワーサーチの使い方を工夫したこと。: TurnManager()
        
    
 反省するべき点（重要！）：
    ・色々と考えたが、木構造が実現できず（木構造メソッドの一部を一番下の「付録」に掲示）、
    他のロボットとの差別化が出来なかったこと。
    ・マップを参考に、Lineミサイルの縦横の方向分けを出来なかったこと。
    ・RyodoMark()でのメソッド内の書き方が雑になってしまった。（実現する為にはこうするしか無かった）
    ・RyodoMark()とmyBrain()の中で似たような処理があったが、省略できなかったこと。
    
 
 感想、トーナメント予測：
    今回のロボバトラーは差別化が難しく、同じようなロボになるのではないかと思う。
    第１段階は、直前に相手の爆撃した場所に攻撃
    第２段階は、マップを作って、攻撃（パリラの立ち位置）
    第３段階は、マップを作ってそのマップを補正しつつ、攻撃
    つまり、トーナメントではこの「パリラ」は中の上くらいの順位となると考えられる。
 
*/
import robobattler2.*; //ロボバトラー2のパッケージをimport
import java.awt.Point; //Point型のライブラリをimport

public class Palira extends Robo {
    
    // フィールド
    private static int[][] ryodo = new int[9][9]; //9×9パネルの領土
    private static int turn = 1;
    private static int myPowerSearchCount = 0;
    private int myBuki; //直前に自分が打った武器
    private int enemyBuki; //直前に相手が打った武器
    private int x_my; //自分が直前に爆撃した場所
    private int y_my;
    private int x_enemy; //相手が直前に爆撃した場所（およそ）
    private int y_enemy;
    private boolean LineWhich=false; //ラインの縦横（縦がtrue, 横がfalse）
    
    
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
        System.out.println("---------パリラ-------");
        System.out.println("ターン数 = "+turn);
        System.out.println("自分武器:"+myBuki);
        System.out.println("相手が直前に使った武器:"+enemyBuki);
        System.out.println("自分の爆撃範囲 = (" + x_my + ", " + y_my + ")");
        System.out.println("相手の爆撃範囲 = (" + x_enemy + ", " + y_enemy + ")");
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                System.out.print(ryodo[x][y]+" ");
            }
            System.out.println();
        }
    }
    
    /**
        初期化メソッド
     */
    private void initialize() {
        if(turn >= 17) {
            for(int y = 0; y < 9; y++) {
                for(int x = 0; x < 9; x++) {
                    ryodo[x][y] = 0; //領土マップの初期化
                }
            }
            turn=1; //ターン数の初期化
        }
    }
    
    
    /**
        サーチし、相手の(X,Y)座標を特定するメソッド
     */
    private void SearchXY() {
        Point search = searchTarget(); //通常のサーチターゲット
        x_enemy = (int)search.getX(); 
        y_enemy = (int)search.getY();
    }
    private void PowerSearchXY() {
        /*
         一旦Point型に格納する事で2回呼び出さなくてよい
         すなわち、パワーサーチを2回使う事が無いようにする。
        */
        Point search = powerSearchTarget(); //パワーサーチターゲット
        x_enemy = (int)search.getX();
        y_enemy = (int)search.getY();
    }
    
    
    /** 
        引数で与えられた武器の爆撃範囲を返すメソッド
        @return 武器の爆撃範囲
        @param buki 武器の番号
    */
    private Point[] BukiArea(int buki) {
        Point[] area = null;
        if(buki == 1) { //ここのLineの縦横の分類分けはしていない（時間的に）
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
        @return 武器が似ているかどうか
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
        @param me 自分(=true)か相手(=false)のどちらにマークするか
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
        area = BukiArea(buki); //引数に与えられた武器の爆撃範囲を返す
        
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
    
        int x_part,y_part;
        Point[] area = null;
        int enemyPanelCount=0,myPanelCount=0,airPanelCount=0;
        int[][] kakusa = new int[9][9];
        int max=0;
        
        area = BukiArea(myBuki); //引数に与えられた武器の爆撃範囲を返す
        
        /*
         「1パネルずつ、自分の武器で爆撃した」と仮定したとき、
         一番領土を獲得できる場所を求める
        */
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                enemyPanelCount = 0;
                myPanelCount = 0;
                airPanelCount = 0;
                for (int i = 0; i < area.length; i++) {
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
                //  2 * 相手パネル + 空パネル。この値が高いほど、一番領土を獲得でき、互いの領土に差が生まれる
                /* 
                 理由：
                 自分と相手の領土を比較したとき、空パネルを取れば自分の領土が1マス増え（格差1）、
                 相手パネルを取れば自分の領土が1マス増え、相手の領土が1マス減る（格差2）、
                 自分パネルを取れば自分の領土は増えない（格差0）
                 これらの事から、重みの数式(2 * enemyPanelCount) + airPanelCountができた。
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
        
        //相手の座標を特定する
        if(turn <= 5) { //序盤はサーチターゲットを使わず、普通のサーチをする
            SearchXY();
        }
        else { //中盤から
            if(bukiSimilar()==true || turn - myPowerSearchCount == 11) { //中盤〜終盤
                /*
                 相手と似たような武器又は
                 11ターン以降は強制的にパワーサーチを使う
                 */
                PowerSearchXY();
                myPowerSearchCount++; //自分がパワーサーチを使った数を保管
            }
            else {
                SearchXY();
            }
        }
        
        
        //相手が打った場所にマークする
        RyodoMark(false);
        
        
        //自分が打つ場所を決める
        if(bukiSimilar() == true) { //自分に取ってわかりやすくするため、trueは省略していない
            //似たような武器ならパワーサーチをつかって特定した相手座標を自分の座標にする
            x_my = x_enemy;
            y_my = y_enemy;
        }
        if(bukiSimilar()==false || turn - myPowerSearchCount == 11) {
            /*
             11ターンを超えて自分がパワーサーチを使っていなかったら、強制的にパワーサーチを使うので、
             似たような武器が無い場合もパワーサーチを使う事になる。だから、前のターン打った相手の座標を
             自分の座標にするのではなく、myBrain()をつかって計算する
             また、似たような武器がないときmyBrain()をつかう
            */
            myBrain(); //自分で作った領土マップを基に一番領土を取れる座標を特定する
        }
        
        
        //自分が打った場所にマークする
        RyodoMark(true);
    }
    
    
    /**
        先攻後攻によって、また相手が爆撃していないときによって、処理を切り替えるメソッド
     */
    private void senkoManager() {
        if(searchTarget() == null) {
            //先攻又は、相手が爆撃してないとき
            myBrain();
            RyodoMark(true); //自分が打った場所にマークする
        }
        else {
            TurnManager(); //ターンによって処理を切り替える
        }
    }
    
    
    /** 
        メインメソッド
        @param missile ミサイルクラスのポインタ
    */
    protected void action(robobattler2.Missile missile) {
        initialize(); //初期化
        
        //武器特定
        myBuki = missile.getType(); //直前に自分が打った武器
        enemyBuki = searchType(); //直前に相手が打った武器
        
        senkoManager(); //ここがメインの処理
        int senryo = fire(x_my,y_my); //占領したパネル数を返す
        //show(); //デバッグ用出力
        //System.out.println("占領"+senryo);
        
        turn++;
    }
    
}








/********************************************************************************
 
    ＜付録＞
    マップ修正のための9分木の木構造探索です。完成は出来ませんでした。
 
********************************************************************************/
 

/*
 public class Palira extends Robo { 
 
    //メソッドの一部を紹介
    private Node tree(Node root,int t) { //tはターン数。
 
        if(turn == t) return root;
 
        for(int y = 0; y < 3; y++) { //0~2
            for(int x = 0; x < 3; x++) { //0~2
                Node next = new Node(x,y);
                if(senryo[t] == kariSenryo[t][x][y]) {
                    //本物の占領パネル数と仮の占領パネル数が一致したら
                    //次のノードへ!!!!!
                }
                else { 
                    //一致しなければ
                    //木を進めない（枝切り）!!!!!
                    next.setNext(null);
                }
            }
        }
        return root;
    }
}

public class Node {
    int x,y;
    Node[][] next;
    int depth;
    
    //セッター（コンストラクタ含む）
    public Node(int x,int y) {
        next = new Node[3][3];
        this.x = x;
        this.y = y;
    }
    void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }
    void setNext(Node next) {
        this.next[x][y] = next;
    }
    void setDepth(int depth) {
        this.depth = depth;
    }
    
    //ゲッター
    Node getNext() {
        return next[x][y];
    }
    int getDepth() {
        return depth; //深さ
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
}


*/
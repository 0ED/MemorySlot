import robobattler2.*;
import java.awt.Point;

public class THE4400 extends Robo {
    
    /*
        フィールド
    */
    private static int[][]kariRyodo = new int[9][9]; //9×9パネルの仮の領土
    private static int[][]recoveRyodo = new int[9][9]; //9×9パネルの補正した領土
    private static int myPowerSearchCount=0; //自分がパワーサーチを使った回数
    private static int enemyPowerSearchCount=0; //相手がパワーサーチを使った回数（およそ）
    private static boolean senko; //先攻か後攻かを保存
    private static int turn = 1; //ターン数を数える
    
    //各ターンで使った自分と相手の武器と座標を保存するために、staticとしている
    //ただし、1~16の配列を使いたいので0を含めて17配列
    private static int[] myBuki = new int[17]; //直前に自分が打った武器
    private static int[] enemyBuki = new int[17]; //直前に相手が打った武器
    private static int[] x_my = new int[17]; //自分が直前に爆撃した場所
    private static int[] y_my = new int[17];
    private static int[] x_enemy = new int[17]; //相手が直前に爆撃した場所（およそ）
    private static int[] y_enemy = new int[17];
    private static int[] senryo = new int[17]; //占領枚数
    private static int[][][] kariSenryo = new int[17][9][9];  //仮の占領枚数
    private static int[] count = new int[17];
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
        System.out.println("--------THE4400--------");
        if(turn == 1) {
            if(senko==true) System.out.println("先攻");
            else System.out.println("後攻");
        }
        System.out.println("ターン数：" + turn);
        System.out.println("自分武器["+turn+"]：" + myBuki[turn]);
        System.out.println("相手武器["+turn+"]：" + enemyBuki[turn]);
        
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                System.out.print(kariRyodo[x][y]+" ");
            }
            System.out.println();
        }
        System.out.println();
        
//        for(int y = 0; y < 9; y++) {
//            for(int x = 0; x < 9; x++) {
//                System.out.print(recoveRyodo[x][y]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
    
    /**
     初期化メソッド
     */
    private void initialize() {
        if(turn >= 17) {
            for(int y = 0; y < 9; y++) {
                for(int x = 0; x < 9; x++) {
                    kariRyodo[x][y] = 0;
                }
            }
            myPowerSearchCount=0;
            enemyPowerSearchCount=0;
            turn=1;
            senko=false;
            for(int t = 1; t <= 16; t++) {
                myBuki[t] = 0;
                enemyBuki[t] = 0;
                x_my[t] = 0;
                y_my[t] = 0;
                x_enemy[t] = 0;
                y_enemy[t] = 0;
                for(int y = 0; y < 9; y++) {
                    for(int x = 0; x < 9; x++) {
                        kariSenryo[t][x][y] = -1;
                    }
                }
            }
        }
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
        x_enemy[turn] = (int)search.getX();
        y_enemy[turn] = (int)search.getY();
    }
    private void PowerSearchXY() {
        Point search = powerSearchTarget();
        x_enemy[turn] = (int)search.getX();
        y_enemy[turn] = (int)search.getY();
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
    private boolean bukiSimilar(int buki1, int buki2){ //myBuki[turn], enemyBuki[turn]
        boolean flag = false;
        if ( (buki1 == buki2)||
            (buki1 == 2 && buki2 == 4)||
            (buki1 == 4 && buki2 == 2)||
            (buki1 == 3 && buki2 == 5)||
            (buki1 == 5 && buki2 == 3)||
            (buki1 == 1 && buki2 == 4)||
            (buki1 == 4 && buki2 == 1) ) {
            if ( !(buki1 == 1 && buki2 == 1) ) {
                flag = true;
            }
        }
        return flag;
    }
    
    /**
        1ターン前に占領したパネル数と1ターン前の自分の武器と相手の武器が以下のメソッドの状態になれば、
        相手の前の座標が確実にわかる。そのとき、trueを返す
     */
    private boolean miracleMatch() {
        boolean flag = false;
        //相手と自分の前のターンに関する事なので、エラーをさける為に先攻、後攻によって区別
        if( (senko==true && turn>=3) || (senko==false && turn>=2) ) {
            /*
                自分の座標とサーチターゲットによる今までの相手の座標、自分と相手の武器、および占領したパネル数によって、
                相手の前の座標がわかる
                奇跡が起こる確率は (  6 / 5(武器数)*5(武器数)  ) * (  10(似た武器が存在する) / 5(武器数)*5(武器数)  ) 
                    = 60 / 625 = 9.5%（さらに、起こったとしても互いの領土があまりない最初の方しか起こらない）
            */
            if( (myBuki[turn-1] == 4 && enemyBuki[turn-1] == 5)||
               (myBuki[turn-1] == 5 && enemyBuki[turn-1] == 4)||
               (myBuki[turn-1] == 4 && enemyBuki[turn-1] == 3)||
               (myBuki[turn-1] == 3 && enemyBuki[turn-1] == 4)||
               (myBuki[turn-1] == 2 && enemyBuki[turn-1] == 5)||
               (myBuki[turn-1] == 5 && enemyBuki[turn-1] == 2) ) {
                if(senryo[turn-1] == 0) {
                    if(bukiSimilar(myBuki[turn-1],myBuki[turn]) == true) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }
    
    /**
        相手がパワーサーチを使ったかおよそ判断するメソッド
     */
    private boolean enemyPowerSearch() {
        boolean flag=false;
        int x_around,y_around;
        
        if(turn >= 2) {
            for(int y = -1; y <= 1; y++) {
                for(int x = -1; x <= 1; x++) {
                    x_around = x_enemy[turn] + x;
                    y_around = y_enemy[turn] + y;
                    if ( (0 <= x_around && x_around < 9) && (0 <= y_around &&y_around < 9) ) {
                        if(x_around == x_my[turn-1] && y_around == y_my[turn-1]) {
                            /* サーチターゲットで特定した相手の位置の周辺座標のどれかと
                             直前に爆撃した自分の座標が一致した場合 */
                            if(bukiSimilar(myBuki[turn-1],enemyBuki[turn]) == true) {
                                flag = true;
                                System.out.println("THE4400のパワーサーチ");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }
    
    
    
    
    
    
    
    
     /* =======================以下より、重要なメソッド========================= */
    /**
        指定した領土分布と自分の武器を基に、自分が爆撃する(X,Y)座標を決めるメソッド
    */
    void myBrain(int[][] ryodo) {
        //ローカル変数
        int x_part,y_part;
        Point[] area = null;
        int enemyPanelCount=0,myPanelCount=0,airPanelCount=0;
        int[][] kakusa = new int[9][9];
        int max=0;
        
        area = BukiArea(myBuki[turn]);
        
        /*「1パネルずつ、自分の武器で爆撃した」と仮定したとき、
         一番領土を獲得できる場所を求める */
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
                 自分パネルを取れば自分の領土は増えない（格差0）*/
            }
        }
        for(int y = 0; y < 9; y++) { //一旦、一番領土を獲得できる場所を見つけてから、探す
            for(int x = 0; x < 9; x++) {
                if(max == kakusa[x][y]) {
                    x_my[turn] = x;
                    y_my[turn] = y;
                }
            }
        }
    }
    
    /**
        領土分布の測定（およそ）
     */
    private void RyodoMark(int[][] ryodo, int buki, int x_point, int y_point, int t, int mark) { //自分と相手の領土にマークをつけるメソッド
        
        int x=0,y=0;
        Point[] area = null;
        int senryo=0;
        
        area = BukiArea(buki);
        for (int i = 0; i < area.length; i++) {
            x = x_point + area[i].x;
            y = y_point + area[i].y;
            if ( (0 <= x && x < 9) && (0 <= y && y < 9) ) {
                ryodo[x][y] = mark; //仮の領土にマークをつける
            }
        }
    }
    
    
    
    /**
        指定した座標に自分が爆撃したとき、相手パネルを占領した数を返すメソッド
     */
    private int getSenryo(int buki, int x_point, int y_point) {
        
        int senryoCount=0;
        int x=0,y=0;
        Point[] area = null;
        int senryo=0;
        
        area = BukiArea(buki);
        for (int i = 0; i < area.length; i++) {
            x = x_point + area[i].x;
            y = y_point + area[i].y;
            if ( (0 <= x && x < 9) && (0 <= y && y < 9) ) {
                if(kariRyodo[x][y]==2) { //相手領土に自分の武器で爆撃したおよそのパネル数を数える
                    senryoCount++;
                }
            }
        }
        return senryoCount;
    }

    /**
        相手パネルを占領した数をカウントするメソッド
     */
    private void SenryoCount() {
        int x_around, y_around;
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 3; x++) {
                x_around = x_enemy[turn]-1 + x; //サーチターゲットによって得られた座標の周囲を探索
                y_around = y_enemy[turn]-1 + y;
                if ( (0 <= x_around && x_around < 9) && (0 <= y_around && y_around < 9) ) {
                    //占領したパネル数を返す。
                    kariSenryo[turn][x][y] = getSenryo(myBuki[turn], x_around, y_around);
                }
                else { //パネル外なら
                    kariSenryo[turn][x][y] = -1; 
                }
            }
        }
    }
    
    private void BureRecovery() {
        Node root = new Node(0,0);
        if(senko==true) tree(root,2);
        else tree(root,1);
        
        System.out.println("ルート"+root);
        Node node = root;
        while(node != null){
            System.out.println("x="+node);
            node = node.getNext();
        }
    }
    
    
    /**
        サーチターゲットによって出来た"ぶれ"を状況によって補正する再帰的メソッド
     */
    private Node tree(Node root,int t) { //tはターン数。"1ターン<=t<現在のターン"の間、再起的に呼び出す
        
        if(turn == t) return root;
        
        /* 現在のターン（turn）は本物の占領パネル数がわからないので、
        現在のターンではないターン（"t<現在のターン"）の間、処理を行う*/
        for(int y = 0; y < 3; y++) { //0~2
            for(int x = 0; x < 3; x++) { //0~2
                Node next = new Node(x,y);
                //本物の占領パネル数と仮の占領パネル数が一致したら
                if(senryo[t] == kariSenryo[t][x][y]) {
                    next.setNext(root); //次のノードへ
                    System.out.println("next"+next);
                    t++;
                    next.setDepth(t);
                    tree(next,t);
                    t--;
                }
                else { //一致しなければそれ以上、木を進めない
                    next.setNext(null);
                }
            }
        }
        
        return root;
    }

    
    /**
        ターンによって処理を切り替えるメソッド
     */
    private void TurnManager() {
        
        //相手が爆撃した領土を見つける
        if (turn <= 5) SearchXY(); //通常サーチ
        else { //6~16ターン目
            if(  bukiSimilar(myBuki[turn],enemyBuki[turn]) == true || (turn - myPowerSearchCount) == 11  ) {
                /* 直前に相手が打った武器と自分に渡された武器が一致する、又は似ているとき
                 又11ターン以降、パワーサーチを使っていないなら強制的にパワーサーチを使う */
                System.out.println("THE4400のパワーサーチ回数"+myPowerSearchCount);
                PowerSearchXY();
                myPowerSearchCount++;
            }
            else {
                SearchXY();
                if(  bukiSimilar(myBuki[turn],enemyBuki[turn]) == true && (turn - enemyPowerSearchCount) == 11 ) {
                    /* 直前に相手が打った武器と自分に渡された武器が一致する、又は似ているとき
                     又11ターン以降、相手がパワーサーチを使っていないなら、相手はパワーサーチを使うはずなので、
                     自分が前に打った場所にもう一発打ち込めば、相手の */
                    System.out.println("相手の手を読む");
                    x_enemy[turn] = x_my[turn-1];
                    y_enemy[turn] = y_my[turn-1];
                }
            }
        }
        
        //相手が爆撃した領土にマーク
        RyodoMark(kariRyodo, enemyBuki[turn], x_enemy[turn], y_enemy[turn], turn, 2);
        
        
        
        //相手が爆撃した領土を活かした処理
        SenryoCount(); //占領したおよその枚数をカウント
        BureRecovery();
        RyodoMark(recoveRyodo, enemyBuki[turn], x_enemy[turn], y_enemy[turn], turn, 2);
        if(enemyPowerSearch() == true) enemyPowerSearchCount++; //相手がパワーサーチを使ったかをある程度判断
        
        
        //自分が爆撃する位置を決める
        if(miracleMatch() == true) {
            //奇跡の組み合わせが成り立てば、1ターン前に自分が打った場所にうつ
            x_my[turn] = x_my[turn-1];
            y_my[turn] = y_my[turn-1];
        }
        else {
            if(  bukiSimilar(myBuki[turn],enemyBuki[turn]) == true  ) {
                x_my = x_enemy;
                y_my = y_enemy;
            }
            else if(  bukiSimilar(myBuki[turn],enemyBuki[turn]) == false || (turn - myPowerSearchCount) == 11 ) {
                myBrain(kariRyodo); //およその領土分布と自分の武器を基に、自分が爆撃する位置を決める
            }
        }
        
        
        //自分が爆撃した領土にマーク
        RyodoMark(kariRyodo, myBuki[turn], x_my[turn], y_my[turn], turn, 1);
    }
    
    
    
    
    
    /**
        先攻後攻によって処理を切り替えるメソッド
     */
    private void senkoManager() {
        if(senko == true) { //自分が先攻なら
            if(turn == 1) { //1ターン目
                myBrain(kariRyodo);
                RyodoMark(kariRyodo, myBuki[turn], x_my[turn], y_my[turn], turn, 1); //自分が爆撃する領土にマークする
                RyodoMark(recoveRyodo, myBuki[turn], x_my[turn], y_my[turn], turn, 1);
            }
            else { //2~16ターン目
                TurnManager(); //ターンによって処理を切り替える
            }
        }
        else { //自分が後攻なら
            TurnManager(); //ターンによって処理を切り替える
        }
    }
    
    
    
    /**
        メインメソッド
    */
    protected void action(robobattler2.Missile missile) {
        initialize(); //16ターン終わったら初期化
        
        
        //武器特定
        myBuki[turn] = missile.getType(); //自分に渡された武器
        enemyBuki[turn] = searchType(); //直前に相手が打った武器

        SenkoHokan(); //自分が先攻か後攻か特定するメソッド
        
        //先攻後攻によって処理を切り替え、このプログラムの中心のメソッド
        senkoManager();
        senryo[turn] = fire(x_my[turn],y_my[turn]); //占領したパネル数を返す
        
        
        show(); //デバッグ用出力
        System.out.println("------------------------------------------------\n");
        
        turn++;
    }
    
}
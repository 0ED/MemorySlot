
/*
最終課題
ファイル名：CheckLine.java
学生証番号:144155
氏名:今江健悟
説明:
このクラスはLine型のミサイルの縦横を判断するクラス

--開発前のメモ書き--
入力 -> Point:爆撃された座標(ノーマルサーチ), MyBoard:その時の仮想盤
入力2-> MyBoardだけ(自分の判断を行うので相手の位置はいらない)
出力 -> boolean: 縦:true, 横:false
という感じで行う。
MyBoardのset()メソッドを行う時のLineの縦横を判断するクラス
MyBoardのset()が行われる前に呼び出す。setの引き数にbooleanを追加する。
(Roboクラスから直接縦で撃つ場合と指定する為、MyBoardに組み込むと勝手に判断してしまう。
融通がきかないこちらだけ縦横を区別して、相手は全て横にしたいなどを行いたい)
CheckLine checkLine = new CheckLine();
爆撃される前の盤の状態と取得した座標を返す(ノーマルサーチ)
tate = checkLine.check(Point, MyBoard);//ここで判定を返す。
この後,MyBoard.set()メソッドへ登録を行う
(この時点で縦横判定は終わっているため、後はMyBoardに最適な爆撃位置決めてもらう)
Line型を縦で撃った方が横で撃ったときより有利になる場合にtrueにする。

判断をどうするか?
全ての行と列の合計を算出する。(MyBoardには各マスの値を取得するメソッドがあるgetMass(x,y))
それを利用し、各行と列の合計値を算出、最も値の低いエリアに撃つ事が効率的!!
->最低値があるエリアが行なら横, 列なら縦撃ちってことやね。

相手の判断:受け取った誤差のあるかもしれない座標(x±1, y±1)の範囲内で上記のエリア
に入っている場合は入ったエリア(行・列)に合わせて縦横判定を行う。
例: Point = (4,3) 最高値 列合計[2]が最高なら
 (4±1, 3±1) -> つまり(*,2)が含まれている 縦撃ちが正解!!

    Point = (3,6) なら,横撃ちで撃ってみる
  (3±1, 6±1) ->
　　 つまり(*,2)が含まれていないため縦撃ちによるベストな位置は撃てない 横撃ち!!

意味がわからないかもしれないが、とりあえず、縦撃ちによる最悪なエリアを撃った可能性が
あれば縦撃ちで登録、受け取った座標からみてその位置には撃てていないことがわかったなら
横撃ちとする。

この方法をとると基本的に横撃ちで撃つ事が多くなるはずであるが、戦ったロボの傾向的に
縦横の区別をしないロボが多かったため、厳密に判断しすぎると、マップを正確に取れなくなる
また、こちらは縦横を区別して撃ち、相手は横だけという状態が多くなると
考えたため、マップの精度をあげるため横撃ちだけにした.
Line型の縦横判断については正しく取得できないはずであるので相手のマップの情報をすこしでも
狂わせるためには縦横を変えて撃つべきである。相手が横撃ちしかしないロボなら縦撃ちで自分の
マスにしたエリアの塗り替えがされにくいことが模擬試合より確認できている。
  
感想/工夫した点：


実行結果:


 */

import robobattler2.*;
import java.awt.Point;

/**
   盤の状態を保存するクラス
 */
public class CheckLine{
    //定数
    public static final int SIZE = 9;//マス目の数
    //フィールド
    private boolean tate = false;//Line型の判定フィールド
    private int[] tateTotal = new int[SIZE];//各列の占拠率
    private int[] yokoTotal = new int[SIZE];//各行の占拠率
    private MyBoard board = new MyBoard();//現在の盤の状態
    
    /**
       コンストラクタ
     */
    CheckLine(){
	for(int y = 0; y < SIZE; y++){
	    for(int x = 0; x < SIZE; x++){
		tateTotal[x] = 0;
		yokoTotal[y] = 0;
	    }
	}
    }

    /**
       現在の盤の状態を設定するメソッド
       @param board 設定する盤の状態
     */
    private void setBoard(MyBoard board){
	this.board = board;
    }

    /**
       状態を初期化するメソッド
     */
    private void init(){
	//前のデータを初期化する
	for(int y = 0; y < SIZE; y++){
	    for(int x = 0; x < SIZE; x++){
		tateTotal[x] = 0;
		yokoTotal[y] = 0;
	    }
	}
    }

    /**
       縦横判定するメソッド
       @param target 確認する座標
       @param board 判定する時の盤の状態
     */
    public boolean check(Point target, MyBoard board){
	Point bestArea = new Point();
	if(target == null){return false;}//相手が撃っていないなら調査しない
	init();//初期化
	setBoard(board);//盤の状態を設定する
	calcTotal();//現在の状態の仮想盤の各行・列の占拠率を計算
	bestArea = getBadArea();//現在の状態の最も占拠率が低い場所を取得
	checkRange(target, bestArea);//低いエリアを爆撃されたか確認する
	System.out.println("相手の番: "+tate);
	return tate;
    }

       /**
       縦横判定するメソッド(自分用)
       @param board 判定する時の盤の状態
     */
    public boolean check(MyBoard board){
	Point bestArea = new Point();
	init();//初期化
	setBoard(board);//盤の状態を設定する
	calcTotal();//現在の状態の仮想盤の各行・列の占拠率を計算
	getBestArea();//縦か横か決める
	System.out.println("自分の番: "+tate);
	return tate;
    }

    
   
    /**
       各行・列の総和を計算するメソッド
     */
    private void calcTotal(){
	//呼び出すことで合計を計算する
	//各縦の総和を計算
	for(int x = 0; x < SIZE; x++){
	    for(int y = 0; y < SIZE; y++){
		tateTotal[x] += board.getMass(x,y);
	    }
	}

	//各横の総和を計算
	for(int y = 0; y < SIZE; y++){
	    for(int x = 0; x < SIZE; x++){
		yokoTotal[y] += board.getMass(x,y);
	    }
	}
    }

    /**
       自分は縦か横撃ちのどちらがいいか調査する
     */
    private void getBestArea(){
	//行の中で最も低いエリアを探す
	int bestArea = tateTotal[0];
	for(int i = 0;i < SIZE; i++){
	    System.out.print("縦["+i+":"+tateTotal[i]+"] ");
	    if(tateTotal[i] < bestArea){
		bestArea = tateTotal[i];
		tate = false;
	    }
	}
	System.out.println("");

	//列の中で行の最低値より低い物を探す
	for(int i = 0;i < SIZE; i++){
	    System.out.print("横["+i+":"+yokoTotal[i]+"] ");
	    if(yokoTotal[i] < bestArea){
		bestArea = yokoTotal[i];
		tate = true;
	    }
	}
	System.out.println("");
    }

      /**
       各行・列の総和の中で最も高いエリアを返す
       @return Point型で返す(x,0):列の総和が, (0,y):行の総和を表す
     */
    private Point getBadArea(){
	Point target = new Point();
	
	//行の中で最も高いエリアを探す
	int bestArea = tateTotal[0];
	for(int i = 0;i < SIZE; i++){
	    System.out.print("縦["+i+":"+tateTotal[i]+"] ");
	    if(tateTotal[i] > bestArea){
		bestArea = tateTotal[i];
		target = new Point(i, 0);
	    }
	}
	System.out.println("");

	//列の中で行の最高値より高い物を探す
	for(int i = 0;i < SIZE; i++){
	    System.out.print("横["+i+":"+yokoTotal[i]+"] ");
	    if(yokoTotal[i] > bestArea){
		bestArea = yokoTotal[i];
		target = new Point(0, i);
	    }
	}
	System.out.println("");

	return target;//最も高いエリアを返す
    }

    /**
       最高値のエリアに合わせて縦横を区別する
       @param target 受け取る座標
       @param bestArea 最高値のエリアの座標
     */
    private void checkRange(Point target, Point bestArea){
	int x = bestArea.x;
	int y = bestArea.y;

	if(y == 0){tate = false;}//横撃ち(最高値が行にあるため)

	if(target.x >= x-1 && target.x <= x+1){tate = true;}
	System.out.println("target["+x+y+"] best["+target.x+target.y+"]");
    }

        public static void main(String[] args){//テスト用
	MyBoard board = new MyBoard();
	CheckLine checkLine = new CheckLine();
	Point point = new Point(3, 1);
	Point point1 = new Point(4, 4);
	Point point2 = new Point(5, 6);
	Point point4 = new Point(5, 4);
	Point point5 = new Point(3, 5);
	board.set(point, 1, 4, false);
	board.set(point1, -1, 2, false);
	board.set(point2, -1, 3, false);
	board.set(point2, 1, 4, false);
	board.set(point2, 1, 5, false);
	board.show();

	checkLine.check(new Point(4,4), board);
	checkLine.check(new Point(0,0), board);
	checkLine.check(new Point(0,1), board);
	checkLine.check(new Point(7,0), board);
    }
}
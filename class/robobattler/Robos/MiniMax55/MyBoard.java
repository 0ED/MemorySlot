
/*
 最終課題
 ファイル名：MyBoard.java
 学生証番号:144155
 氏名:今江健悟
 説明:
 盤の状態を保存するクラス
 
 感想/工夫した点：
 --目的--
 盤の状態を自分がわかる範囲で保存するクラス
 盤の状態からどこにおくのが一番効率が良いのかなどを判断するため
 盤の情報の登録、参照をしやすくするために設計しました.
 javadocを作れるようにしているので,メソッド毎の詳細を確認したい場合にどうぞ.
 
 --Point型について--
 Point型のフィールドを直接参照しているが、
 課題のプログラムの中でも行っており、尚かつgetXメソッドを使うとint型に変換が必要になるため
 こちらの方が見通しがよくなると思い、直接参照しています。仕様を見てみるとフィールドの値が
 privateではなかったため、このような使い方が許可されていると思ってしています。
 
 --カプセル化--
 このMyBoardクラスを作る上で工夫したことは盤にミサイルを登録するということさえわかっていれば
 その他の細かい仕様については理解していなくても問題がないようにしています。普通に二次元配列の
 マップにミサイルを追加する場合は、ミサイルの種類によって追加の仕方の区別をつけて登録したり、
 画面外に出ているものはどうするかなどをいちいち決めていかなければならなくなります。それは
 めんどくさいのでこのMyBoardクラスではミサイルの種類と座標を渡せば適切に登録されるように
 しています。一度このクラスを作ってしまえば、以降はこのクラスに種類と座標を渡すだけでいいため
 プログラムの効率が向上しました。
 
 --コンストラクタ--
 コンストラクタが２種類あるのは、現在のMyBoardの状態を複製したい場合にコピーするために
 あります。引き数なしは初期状態の盤を引き数有りは、複製したいMyBoardのgetBoard()を使い
 その時の盤の状態を受け渡す.
 
 
 
 */

import robobattler2.*;
import java.awt.Point;

/**
 盤の状態を保存するクラス
 */
public class MyBoard{
    //定数
    public static final int SIZE = 9;//マス目の数
    
    //フィールド
    private int[][] board = new int[SIZE][SIZE];//ボードの状態を保存するフィールド
    private MissileArea missileArea = new MissileArea();//ミサイルの相対座標を得る
    
    /**
     初期状態のボードを作成する
     */
    MyBoard(){
        //盤を初期化する処理
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                board[x][y] = 0;
            }
        }
    }
    
    /**
     盤の情報を受け取ってボードを作成する
     @param board 受け取ったボードの情報
     */
    MyBoard(int[][] board){
        //盤を設定する処理
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                this.board[x][y] = board[x][y];
            }
        }
    }
    
    /**
     盤の情報を取り出すメソッド
     @return 盤に登録されている全てのマスの情報
     */
    public int[][] getBoard(){
        return this.board;
    }
    
    /**
     指定したマスを返すメソッド
     @param x x座標の値
     @param y y座標の値
     @return 指定した位置に登録されているマスの情報
     */
    public int getMass(int x, int y){
        return this.board[x][y];
    }
    
    /**
     現在の盤の状況(占拠率)を得るメソッド
     @return 現在の盤の占拠率
     */
    public int getTotal(){
        int total = 0;;
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                total += board[x][y];//全てのマスの合計を足して行く
            }
        }
        return total;
    }
    
    /**
     盤にマスの状態を保存するメソッド
     @param target 保存する座標のオブジェクト
     @param player "1"なら自分,"-1"なら相手の保存に設定する
     @param type ミサイルのタイプを表す番号(詳細はMissileクラス参照)
     @param tate Line型ミサイルの縦横
     */
    public void set(Point target, int player, int type, boolean tate){
        Point[] area = new Point[10];//相対座標を入れておく配列
        //Line型の区別をするためbooleanを使う(まだやってないけど)
        area = missileArea.getArea(type,tate);//タイプに合わせた配列を受け取る
        setMissile(target, player, area);//タイプに合わせて座標を保存する
    }
    
    /**
     ミサイルの爆撃位置を設定するメソッド
     @param target 保存する座標のオブジェクト
     @param player "1"なら自分,"-1"なら相手の保存に設定する
     @param area 保存するミサイルの相対座標の配列
     */
    private void setMissile(Point target, int player, Point[] area){
        
        for(Point p : area){//爆撃範囲のそれぞれに対して処理を行う
            int x = target.x + p.x;//実際の座標を計算する
            int y = target.y + p.y;
            if (0 <= x && x < SIZE && 0 <= y && y < SIZE) {//フィールド内
                board[x][y] = player;//爆撃したプレイヤーのマスとする
            } else {//フィールド外
                //特に処理はしない
            }
            
        }
    }
    
    
    /**
     現在の盤の状態を出力するメソッド
     */
    public void show(){
        System.out.println("----------------------------");
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                System.out.printf("%3d",board[x][y]);//フォントがずれない様に
            }
            System.out.println("");
        }
        System.out.println("----------------------------");
    }
    
    /**
     テスト用
     */
    public static void main(String[] args){//テスト用
        MyBoard board = new MyBoard();
        Point point = new Point(3, 1);
        Point point1 = new Point(4, 4);
        Point point2 = new Point(5, 6);
        Point point4 = new Point(5, 4);
        Point point5 = new Point(3, 5);
        board.set(point, 1, 1,false);
        board.set(point1, -1, 2, false);
        board.set(point2, 1, 3, false);
        board.set(point2, -1, 4, false);
        board.set(point2, 1, 5, false);
        board.show();
    }
}

/*
最終課題
ファイル名：MissileArea.java
学生証番号:144155
氏名:今江健悟
説明:
盤の状態を保存するクラス
  
感想/工夫した点：
--目的--
ミサイルの相対座標を得るためのクラス
ミサイルの相対座標を他のクラスでも使う場合に取り出しやすくするため

--なぜクラスにしている？--
このクラスは相対座標を受け渡すだけのクラスなので、つかうクラスで相対座標をフィールドとして
定義しておけばよいのでは？と思いましたが、まずプログラムの上の方にずらーっと書かれていると
見ていてごちゃごちゃしているなと感じたためであります。また、クラスが複数存在しているため、
いちいち全てのクラスの上にフィールドとして定義するのではなく取り出すだけのクラスを作成し、
使うクラスではMissileAreaクラスを呼び出し,相対座標を使用するという目的で作成しました.

実行結果:


 */

import robobattler2.*;
import java.awt.Point;

/**
   指定されたミサイルの相対座標を返すクラス
 */
public class MissileArea{
    //Line型の相対座標の配列
    private final Point[] LINE_AREA_TATE = {  // 縦の場合の爆撃範囲の座標列（相対値）
	new Point(0, -3), new Point(0, -2), new Point(0, -1),
	new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)};
    private final Point[] LINE_AREA_YOKO = {  // 横の場合の爆撃範囲の座標列（相対値）
	new Point(-3, 0), new Point(-2, 0), new Point(-1, 0),
	new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)};

    //Plus型の相対座標の配列
    private final Point[] PLUS_AREA = {
	new Point(-1, 0), new Point(0, 0), 
	new Point(1, 0), new Point(0, -1), new Point(0, 1)};
    //X型の相対座標の配列
    private final Point[] X_AREA = {
	new Point(-1, -1), new Point(0, 0),
	new Point(1, 1), new Point(1, -1), new Point(-1, 1)};

    //BigPlus型の相対座標の配列
    private final Point[] BIGPLUS_AREA = {
	new Point(-2, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),
	new Point(0, -2), new Point(0, -1), new Point(0, 1), new Point(0, 2)};
    //BigX型の相対座標の配列
    private final Point[] BIGX_AREA = {
	new Point(-2, -2), new Point(-1, -1), new Point(1, 1), new Point(2, 2),
	new Point(2, -2), new Point(1, -1), new Point(-1, 1), new Point(-2, 2)};
    
    /**
       対応するミサイルの相対座標の配列を返すメソッド
       @param type ミサイルのタイプを表す番号(詳細はMissileクラス参照)
       @param tate Line型のミサイルの向きを決定する(true:縦, false:横)
       @return 指定されたミサイルの相対座標の配列
     */
    public Point[] getArea(int type, boolean tate){
	switch(type){
	case 1://Line型の場合
	    if(tate){
		return LINE_AREA_TATE;
	    }else{
		return LINE_AREA_YOKO;
	    }
	case 2://Plus型の場合
	    return PLUS_AREA;
	case 3://X型の場合
	    return X_AREA;
	case 4://BigPlus型の場合
	    return BIGPLUS_AREA;
	case 5://BigX型の場合
	    return BIGX_AREA;
	default://どの場合にも引っかからない場合
	    System.out.println("ミサイルのタイプが不正です.");
	}
	return null;//どの場合にもひっからなかった場合
    }

}
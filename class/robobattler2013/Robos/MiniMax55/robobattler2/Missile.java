package robobattler2;

import java.awt.Point;

/**
   ミサイルの抽象クラス。このクラスを継承して5種類のミサイルのサブクラスを実装する。
 */
abstract public class Missile {
    // 定数
    public static final int  MISSILE_TYPE_LINE = 1;
    public static final int  MISSILE_TYPE_PLUS = 2;
    public static final int  MISSILE_TYPE_X = 3;
    public static final int  MISSILE_TYPE_BIGPLUS = 4;
    public static final int  MISSILE_TYPE_BIGX = 5;

    /**
       ミサイルの種類を返す。サブクラスごとに実装する。
       @return ミサイルの種類の値
     */
    abstract public int getType();

    /**
       ミサイルの爆撃範囲の配列を返す。サブクラスごとに実装する。
       @return 着弾座標からの相対座標の配列
    */
    abstract Point[] getArea();

    /**
       ミサイルの爆撃範囲の向きを垂直方向に設定する。方向のないミサイル（LineMissile以外）のために、なにもしないメソッドを実装している。
     */
    public void setVertical() {
	// do nothing
    }

    /**
       ミサイルの爆撃範囲の向きを水平方向に設定する。方向のないミサイル（LineMissile以外）のために、なにもしないメソッドを実装している。
     */
    public void setHorizontal() {
	// do nothing
    }
}

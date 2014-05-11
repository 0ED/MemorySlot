package robobattler2;

import java.awt.Point;

/**
   爆撃範囲が大きい十字型のミサイル。着弾地点を中心に縦および横2マスの計8マスが爆撃範囲（着弾地点を含まないことに注意）。
 */
public class BigPlusMissile extends Missile {
    // 定数
    private final Point[] AREA = {
	new Point(-2, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),
	new Point(0, -2), new Point(0, -1), new Point(0, 1), new Point(0, 2)
    };

    /**
       ミサイルの種類を返す。
       @return ミサイルの種類の値（Missile.MISSILE_TYPE_BIGPLUS）
     */
    public int getType() {
	return MISSILE_TYPE_BIGPLUS;
    }

    /**
       ミサイルの爆撃範囲の配列を返す。
       @return 着弾座標からの相対座標の配列
    */
    Point[] getArea() {
	return AREA;
    }
}


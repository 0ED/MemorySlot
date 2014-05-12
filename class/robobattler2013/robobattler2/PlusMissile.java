package robobattler2;

import java.awt.Point;

/**
   爆撃範囲が十字型のミサイル。着弾地点および、着弾地点を中心に縦および横1マスの計5マスが爆撃範囲。
 */
public class PlusMissile extends Missile {
    // 定数
    private final Point[] AREA = {
	new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)
    };

    /**
       ミサイルの種類を返す。
       @return ミサイルの種類の値（Missile.MISSILE_TYPE_PLUS）
     */
    public int getType() {
	return MISSILE_TYPE_PLUS;
    }

    /**
       ミサイルの爆撃範囲の配列を返す。
       @return 着弾座標からの相対座標の配列
    */
    Point[] getArea() {
	return AREA;
    }
}


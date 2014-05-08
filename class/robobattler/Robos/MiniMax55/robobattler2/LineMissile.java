package robobattler2;

import java.awt.Point;

/**
   爆撃範囲が直線のミサイル。着弾地点を中心に縦あるいは横7マスが爆撃範囲。縦か横かはsetVerticalメソッドまたはsetHorizontalメソッドで設定できる。
 */
public class LineMissile extends Missile {
    // 定数
    private final Point[] VERTICAL_AREA = {  // 縦の場合の爆撃範囲の座標列（相対値）
	new Point(0, -3), new Point(0, -2), new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)
    };
    private final Point[] HORIZONTAL_AREA = {  // 横の場合の爆撃範囲の座標列（相対値）
	new Point(-3, 0), new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)
    };

    // フィールド
    private boolean isVertical = false;  // 爆撃範囲が縦か横かを覚えているフィールド。縦の時にtrue

    /**
       ミサイルの種類を返す。
       @return ミサイルの種類の値（Missile.MISSILE_TYPE_LINE）
     */
    public int getType() {
	return MISSILE_TYPE_LINE;
    }

    /**
       ミサイルの爆撃範囲の配列を返す。
       @return 着弾座標からの相対座標の配列
    */
    Point[] getArea() {
	if (isVertical) {
	    return VERTICAL_AREA;
	} else {
	    return HORIZONTAL_AREA;
	}
    }

    /**
       ミサイルの爆撃範囲の向きを垂直方向に設定する。
     */
    public void setVertical() {
	isVertical = true;
    }

    /**
       ミサイルの爆撃範囲の向きを水平方向に設定する。
     */
    public void setHorizontal() {
	isVertical = false;
    }
}


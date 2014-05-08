import robobattler2.*;
import java.awt.Point;
public class MissileArea{
    
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
     引数で与えられた武器の爆撃範囲を返すメソッド
     @return 武器の爆撃範囲
     @param weapon 武器の番号 vertical ラインの縦
     */
    public Point[] getArea(int weapon, boolean vertical) {
        switch(weapon) {
            case Missile.MISSILE_TYPE_LINE:
                if(vertical) return LineVerticalArea;
                else return LineHorizontalArea;
            case Missile.MISSILE_TYPE_PLUS:
                return PlusArea;
            case Missile.MISSILE_TYPE_X:
                return XArea;
            case Missile.MISSILE_TYPE_BIGPLUS:
                return BigPlusArea;
            case Missile.MISSILE_TYPE_BIGX:
                return BigXArea;
            default:
                return null; //武器をうたない場合
        }
    }
}

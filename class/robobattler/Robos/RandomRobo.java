import	robobattler2.*;
import	java.awt.Point;


public class RandomRobo extends Robo {
	protected void action(robobattler2.Missile missile) {
		Point target = searchTarget();
		int type = searchType();
		if (target != null)  System.out.println("search: type = " + type + ", target = " + target.x + ", " + target.y);
		int x = (int)(Math.random() * Field.SIZE);
		int y = (int)(Math.random() * Field.SIZE);
		System.out.println("fire: " + x + ", " + y + ", missile = " + missile.getType());
		fire(x, y);
	}
}

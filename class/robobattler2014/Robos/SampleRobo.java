import robobattler3.*;
import java.awt.Point;

public class SampleRobo extends Robo
{
	public SampleRobo()
	{
		super();
	}
	
    protected void initGame(Ship[] ships)
	{
		System.out.println("----- SampleRobo -----");

		for (Ship aShip : ships)
		{
			int x = (int)(Math.random() * BattleManager.AREA);
			int y = (int)(Math.random() * BattleManager.AREA);
			Point aPoint = new Point(x, y);
			aShip.init(aPoint, true);
		}
    }
	
    protected void action()
	{
		System.out.println("----- SampleRobo -----");	
		int x = (int)(Math.random() * BattleManager.AREA);
		int y = (int)(Math.random() * BattleManager.AREA);
		fire(x, y);
    }
}

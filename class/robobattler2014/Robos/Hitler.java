import robobattler3.*;
import java.awt.Point;
import java.util.Arrays;

public class Hitler extends Robo
{
	/**
	 * 自分と相手のラベル。
	 */
	private static final int ME = 0;
	private static final int ENEMY = 1;
	
	/**
	 * 4近傍のポイント
	 */
	private static final Point[] neighbors = { new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1) };
	
	/**
	 * 現在のターン。
	 */
	private int turn;
	
	/**
	 * 自分と相手の前回の爆撃ポイント。
	 */
	private Point[] backPoints;
	
	/**
	 * 自分が前回放った爆撃結果。
	 */
	private int backFireResult;
	
	/**
	 * 自分と相手が打ったゲームフィールド。
	 */
	private int[][][] fields;
	
	/**
	 * 初期化を行うコンストラクタ。
	 */
	public Hitler()
	{
		super();
	}
	
	/**
	 * すべての船を初期配置をする。
	 */
    protected void initGame(Ship[] ships)
	{
		System.out.println("----- Hitler initGame -----");
		this.turn = 0;
		this.backFireResult = 0;
		this.backPoints = new Point[2];
		this.fields = new int[2][BattleManager.AREA][BattleManager.AREA];
		for (int[][] aField : fields) {
			for (int[] row : aField) {
				for (int column : row) {
					column = 0;
				}
			}
		}
		for (Ship aShip : ships)
		{
			int x = (int)(Math.random() * BattleManager.AREA);
			int y = (int)(Math.random() * BattleManager.AREA);
			Point aPoint = new Point(x, y);
			aShip.init(aPoint, false);
		}
		
		return;
    }
	
	private void showDebug()
	{
		for (int who=0; who < this.backPoints.length; who++)
		{
			if (backPoints[who] == null) { continue; }
			if (who == ME) { System.out.print("ME "); }
			else { System.out.print("ENEMY "); }
			System.out.println(backPoints[who].x + " " + backPoints[who].y);
		}
		for (int[][] aField : fields) {
			for (int[] row : aField) {
				for (int column : row) {
					System.out.print(column + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		return;
	}
	
	/**
	 * バトルフィールドに相手の最後の爆撃ポイントの目印つけ、そこから最適な爆撃ポイントを返す。
	 */
	private Point searchShip()
	{
		this.backPoints[ENEMY] = this.getLastTarget();
		this.fields[ENEMY][this.backPoints[ENEMY].x][this.backPoints[ENEMY].y] = 1;
		
		int x=0,y=0;
		if (this.backFireResult == Robo.HIT) {
			System.out.println("HIT!");
			for (Point aNeighbor : this.neighbors) {
				x = backPoints[ENEMY].x + aNeighbor.x;
				y = backPoints[ENEMY].y + aNeighbor.y;
				if (fields[ME][x][y] == Robo.FAIL)
				{
					
				}
			}
		}
		else if (this.backFireResult == Robo.FAIL) {
			
		}
		else if (this.backFireResult == Robo.HIT_AND_SINK) {
			
		}
		else {
			x = (int)(Math.random() * BattleManager.AREA);
			y = (int)(Math.random() * BattleManager.AREA);
		}
		x = (int)(Math.random() * BattleManager.AREA);
		y = (int)(Math.random() * BattleManager.AREA);
		return new Point(x,y);
	}
	
	/**
	 * 爆撃砲を打つ。
	 */
	private void shootFire()
	{
		this.backPoints[ME] = searchShip();
		int x = backPoints[ME].x;
		int y = backPoints[ME].y;
		this.backFireResult = fire(x,y);
		this.fields[ME][x][y] = 1;

		return;
	}
	
	
	/**
	 * 自機を行動させる。
	 */
    protected void action()
	{
		System.out.println("----- Hitler -----");
		shootFire();
		this.showDebug();
		this.turn++;
		
		return;
    }
}

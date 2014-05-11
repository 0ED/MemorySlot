import robobattler3.*;
import java.awt.Point;
import java.util.Arrays;
import java.util.Stack;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Nanoクラス。
 */
public class Nano extends Robo
{
	/**
	 * 自分と相手のラベル。
	 */
	private static final int ME = 0;
	private static final int ENEMY = 1;
	
	/**
	 * 4近傍のポイント。
	 */
	private static final Point[] neighbors = { new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1) };
	
	/**
	 * バトルフィールドの初期値のための数字。
	 */
	private static int STILL;
	
	/**
	 * 現在のターン。
	 */
	private int turn;
	
	/**
	 * ヒットした爆撃ポイントを格納する。
	 */
	private List<Point> hitPoints;
	
	/**
	 * 前回の爆撃ポイント。
	 */
	private int backPoint;
	
	/**
	 * 自分と相手が打った各バトルフィールド。
	 */
	private int[][][] fields;
	
	/**
	 * 初期化を行うコンストラクタ。
	 */
	public Nano()
	{
		super();
		this.STILL = 0;
		this.HIT = 1;
		this.FAIL = 2;
		this.HIT_AND_SINK = 9;
	}
	
	/**
	 * デバッグを見せる。
	 */
	private void showDebug()
	{
		for (int[][] aField : fields)
		{
			for (int row=0; row < BattleManager.AREA; row++)
			{
				for (int column=0; column < BattleManager.AREA; column++)
				{
					System.out.print(aField[column][row] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		return;
	}
	
	/**
	 * 船を配置する。
	 */
	private void arrangeShips(Ship[] ships)
	{
		for (Ship aShip : ships)
		{
			int x = (int)(Math.random() * BattleManager.AREA);
			int y = (int)(Math.random() * BattleManager.AREA);
			Point aPoint = new Point(x, y);
			aShip.init(aPoint, false);
		}
		
		return;
	}
	
	/**
	 * ゲーム開始時に自動的に呼ばれる。
	 */
    protected void initGame(Ship[] ships)
	{
		System.out.println("----- Nano initGame -----");
		this.turn = 0;
		this.hitPoints = new ArrayList<Point>();
		
		/* ゲームフィールドの初期化 */
		this.fields = new int[2][BattleManager.AREA][BattleManager.AREA];
		for (int[][] aField : fields)
		{
			for (int row=0; row < BattleManager.AREA; row++)
			{
				for (int column=0; column < BattleManager.AREA; column++)
				{
					aField[column][row] = this.STILL;
				}
			}
		}
		
		/* 船を配置 */
		this.arrangeShips(ships);
		
		return;
    }
	
	/**
	 * バトルフィールドに相手の最後の爆撃ポイントの目印つけ、そこから最適な爆撃ポイントを返す。
	 */
	private Point searchShip()
	{
		int x=0,y=0;
		if (this.hitPoints.isEmpty())
		{
			x = (int)(Math.random() * BattleManager.AREA);
			y = (int)(Math.random() * BattleManager.AREA);
			return new Point(x,y);
		}
		else if (this.hitPoints.size() == 1)
		{
			Point aPoint = this.hitPoints.get(0);
			for (Point aNeighbor : this.neighbors)
			{
				x = aPoint.x + aNeighbor.x;
				y = aPoint.y + aNeighbor.y;
				if (0 <= x && x <= BattleManager.AREA &&
					0 <= y && y <= BattleManager.AREA )
				{
					if (this.fields[ME][x][y] == this.STILL)
					{
						break;
					}
				}
			}
			return new Point(x,y);
		}
		else
		{
			List<Integer> xs = new ArrayList<Integer>();
			List<Integer> ys = new ArrayList<Integer>();
			for (Point aPoint : this.hitPoints)
			{
				xs.add(aPoint.x);
				ys.add(aPoint.y);
			}
			
			int edgeBeginX = Collections.min(xs) - 1;
			int edgeEndX = Collections.max(xs) + 1;
			int edgeBeginY = Collections.min(ys) - 1;
			int edgeEndY = Collections.max(ys) + 1;
			if (xs.get(0) == xs.get(1))
			{
				x = xs.get(0);
				/*
				 * Y座標にバグ（12月27日）
				 */
				if (0 <= edgeBeginY)
				{
					if (fields[ME][x][edgeBeginY] == this.STILL)
					{
						y = edgeBeginY;
					}
				}
				else if (edgeEndY <= BattleManager.AREA)
				{
					if (fields[ME][x][edgeEndY] == this.STILL)
					{
						y = edgeEndY;
					}
				}
			}
			else
			{
				y = ys.get(0);
				/*
				 * X座標にバグ（12月27日）
				 */
				if (0 <= edgeBeginX) {
					if (fields[ME][edgeBeginX][y] == this.STILL)
					{
						x = edgeBeginX;
					}
				}
				else if (edgeEndX <= BattleManager.AREA) {
					if (fields[ME][edgeEndX][y] == this.STILL)
					{
						x = edgeEndX;
					}
				}
			}
		}
		
		return new Point(x,y);
	}
	
	
	
	
	/**
	 * バトルフィールドにラベルを貼る。
	 */
	private void putLabelToField(Point aPoint, int label)
	{
		if (label == this.HIT)
		{
			this.hitPoints.add(aPoint);
			this.fields[ME][aPoint.x][aPoint.y] = this.HIT;
		}
		else if (label == this.HIT_AND_SINK)
		{
			for (Point aHitPoint : this.hitPoints)
			{
				this.fields[ME][aHitPoint.x][aHitPoint.y] = this.HIT_AND_SINK;
			}
			this.hitPoints.removeAll(this.hitPoints);
		}
		else
		{
			this.fields[ME][aPoint.x][aPoint.y] = label;
		}
		
		return;
	}
	
	/**
	 * 爆撃砲を打つ。
	 */
	private void shootFireToField()
	{
		Point aPoint = searchShip();
		int label = fire(aPoint.x, aPoint.y);
		this.putLabelToField(aPoint, label);
		
		return;
	}
	
	/**
	 * 自機を行動させる。
	 */
    protected void action()
	{
		System.out.println("----- Nano -----");
		
		/* 敵の爆撃ポイントがあればラベルづけする。 */
		Point aPoint = this.getLastTarget();
		if (aPoint != null)
		{
			this.fields[ENEMY][aPoint.x][aPoint.y] = this.HIT;
		}
		this.shootFireToField();
		this.showDebug();
		this.turn++;
		
		return;
    }
}

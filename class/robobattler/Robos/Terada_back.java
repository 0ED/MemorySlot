import robobattler2.*;
import java.awt.Point;

public class  Terada_back extends Robo {
    private int turn=1;
	private int enemyX,enemyY;
	private int myX,myY;
	private int enemyWeapon,myWeapon;
	
	private void hogehoge() {
		Point target = searchTarget();
		
		if (target == null) {
			myX=4;
			myY=4;
		}
		else {
			enemyX = (int)target.getX();
			enemyY = (int)target.getY();
			System.out.println("enemyX = "+enemyX + "enemyY = "+enemyY);
		}
	}
	
	protected void action(Missile missile) {
        System.out.println("ターン数"+turn);
		enemyWeapon = searchType();
		myWeapon = missile.getType();
		System.out.println("enemyType"+searchType());
		System.out.println("myType"+missile.getType());
		
		hogehoge();
		
		myX=enemyX;
		myY=enemyY;
        if(turn != 1) {
            fire(myX, myY);
        }
		turn++;
	}
}
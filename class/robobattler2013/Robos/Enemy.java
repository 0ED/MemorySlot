import robobattler2.*;
import java.awt.Point;

public class Enemy extends Robo {
    
    private static int turn=1;
    
    protected void action(robobattler2.Missile missile) {
        if(turn >= 17) turn = 1;
        int buki = missile.getType();
        System.out.println("--------Enemy--------");
        
        int x = (int)(Math.random() * Field.SIZE);
        int y = (int)(Math.random() * Field.SIZE);
        if(turn != 4)fire(x,y);
        turn++;
    }
}
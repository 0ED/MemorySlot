
/*
 =========================================
 ＜学生証番号、氏名＞
 ＜評価してもらいたい点＞
 ＜工夫した点＞
 ＜苦労した点＞
 ＜考察＞
 ========================================= 
 */
import java.awt.Point;
import robobattler2.*;
public class Terada extends Robo { //ロボクラスを継承
    private int tekiWeapon,meWeapon; //敵武器と自分武器
    private int meX,meY; //自分の座標
	private int tekiX,tekiY; //敵の座標
	private static int countPower; //パワーサーチを数える
    private static int turn=1;
	
    
    /* メインメソッド　*/
    protected void action(robobattler2.Missile missile) {
        if(turn > 16) {
            countPower=0;
            turn=1;
        }
        memoryWeapon(missile); //武器を記憶する
		nullKaihi(); //ここがメインの処理です
		
		meX=tekiX; //敵の座標を自分の座標にする
		meY=tekiY;
        fire(meX, meY); //爆撃!
        turn++;
	}
    
    
    /* 武器を記憶するメソッド */
    private void memoryWeapon(robobattler2.Missile missile) {
        meWeapon = missile.getType();
        tekiWeapon = searchType();
		//System.out.println("自分の武器"+meWeapon);
        //System.out.println("敵の武器"+tekiWeapon);
    }
    
    
     /* nullを回避する為のメソッド　*/
	private void nullKaihi() {
		Point search = searchTarget();
		
		if (search == null) {
			meX=5;
			meY=5;
		}
		else {
            if(typeEquals()){ //同じような武器があったら
                Point search_power = powerSearchTarget();
                tekiX = (int)search_power.getX(); //パワーサーチする
                tekiY = (int)search_power.getY();
                countPower++;
                //System.out.println("count"+countPower); //パワーサーチを使った回数を出力
            }
            else {
                tekiX = (int)search.getX(); //サーチする
                tekiY = (int)search.getY();
                //System.out.println("tekiX = "+tekiX + "tekiY = "+tekiY);
            }
		}
	}
    
    
    /* 武器タイプが一緒のようなときtrueを返すメソッド */
    private boolean typeEquals() {
        
        boolean hantei = false;
        if (meWeapon == tekiWeapon) {
            if ( !(meWeapon == 1 && tekiWeapon == 1) ) hantei = true;
        }
        else if ( (meWeapon==5) && (tekiWeapon==3) ) hantei = true;
        else if ( (meWeapon==2) && (tekiWeapon==4) ) hantei = true;
        else if ( (meWeapon==3) && (tekiWeapon==5) ) hantei = true;
        else if ( (meWeapon==4) && (tekiWeapon==1) ) hantei = true;
        else if ( (meWeapon==4) && (tekiWeapon==2) ) hantei = true;
        else if ( (meWeapon==1) && (tekiWeapon==4) ) hantei = true;
        else {
            hantei = false;
        }
        return hantei;
    }
}
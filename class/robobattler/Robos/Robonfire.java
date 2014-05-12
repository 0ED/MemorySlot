/*145479 吉元 佑太
 * 基本的に、相手が撃ったところを真似して撃ってくる人が多いと思ったので、
 * 空白の部分を確保できるようにした。しかし結果的にはうまくいっていない。
 * 
 * */
import robobattler2.*;
import java.util.*;
import java.awt.Point;

public class Robonfire extends Robo {
	/*フィールド*/
	//ミサイルの種類 1:line, 2:plus, 3:X, 4:bigplus, 5:bigX
	robobattler2.Missile missile;
	/*先攻か後攻かを保存しておく変数*/
	private int firstatk = -1;
	/*パワーサーチを使った回数をチェック*/
	private int powersearch;
	/*ターン数を記録するための変数*/
	private int inning;
	/*相手が追尾型かどうかのチェック*/
	private boolean antiTrace;
	/*対追尾型攻撃のタイプ*/
	private int typeAntiTrace;
	/*自分と敵が撃った場所を保存しておくためのリスト*/
	private List<Point> myCoordinates = new LinkedList<Point>();
	private List<Point> opponentCoordinates = new LinkedList<Point>();
	
	/**
	 * フィールドの値を初期化する。
	 * 連続で試合を行うときに必要なメソッド。
	 */
	private void initField() {
		powersearch = 0;
		inning = 0;
		antiTrace = false;
		myCoordinates.clear();
		opponentCoordinates.clear();
	}
	
	 /**
	  * 先攻か後攻かをチェック
	  */
	private void firstCheck() {
		/*攻撃順序判定*/
		firstatk = (searchTarget() == null? 1:0);	
	}
	
	/**
	 *  自分と相手が攻撃した座標を記録する。
	 */
	private void doCollectCoodinates(Point mc, Point oc) {
		myCoordinates.add(mc);
		/*相手は初回のみnullの場合がある*/
		if (oc == null) {
		}else{
			opponentCoordinates.add(oc);
		}
	}
	
	/**
	 * 相手が追尾してきているかどうかをチェックするメソッド
	 * 基本的に直近3つの座標をチェックする。
	 */
	private void checkTrace() {
		int mx=0, my=0;
		int ox=0, oy=0;
		int amx, amy;
		int aox, aoy;
		
		//System.out.println("自座標数" + myCoordinates.size());
		//System.out.println("敵座標数" + opponentCoordinates.size());
		
		try {
			int a = (opponentCoordinates.size() - 3 < 0)? 0:opponentCoordinates.size() - 3;
			
			for (int i = opponentCoordinates.size(); i > a; i--) {
				//System.out.println("i: " + i + "ocs: " + opponentCoordinates.size());
				//System.out.println("a: " + a);
				mx += myCoordinates.get(i-1).x;
				my += myCoordinates.get(i-1).y;
				ox += opponentCoordinates.get(i-1).x;
				oy += opponentCoordinates.get(i-1).y;
			}
			amx = mx / 3;
			amy = my / 3;
			aox = ox / 3;
			aoy = oy / 3;
			
			// System.out.println(amx + " " + amy + " " + aox + " " + aoy);
			if (Math.abs(amx - aox) <= 2 && Math.abs(amy - aoy) <= 2) {
				//System.out.println("alert:t");
				antiTrace = true;
			}else{
				antiTrace = false;
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("要素の数、変やで");
		}		
	}
	
	/**
	 * フィールドの外に爆撃範囲がいきそうなとき、座標を修正する。
	 * ミサイルのタイプによって行う補正が異なるが、修正しない場合もある。
	 * @param point 補正したいミサイルの座標
	 * @param ms 打つミサイル
	 * @return 補正後のミサイルの座標
	 */
	private Point fixTargetCoodinate(Point point, robobattler2.Missile ms) {
		int r;
		switch (ms.getType()) {
		case 1:
			// ライン型のとき
			/*ランダムで向きを変える*/
			r = (int)(Math.round(Math.random()));
			//System.out.println("rand:" + r);
			if (r == 1) {
				ms.setVertical();
			}else{
				ms.setHorizontal();
			}
			break;
		case 2:
			// プラス型のとき
			// 爆撃範囲が外に出そうであれば、補正する。
			// 四隅以外
			if ((point.x == 0 && point.y == 0) ||
				(point.x == 0 && point.y == 8) ||
				(point.x == 8 && point.y == 0) ||
				(point.x == 8 && point.y == 8) ) {
			}else{
				if (point.x <= 0) {
					point.x = 1;
				}else if(point.x >= 8){
					point.x = 7;
				}
				if (point.y <= 0) {
					point.y = 1;
				}else if(point.y >= 8){
					point.y = 7;
				}
			}
			break;
		case 3:
			// X型のとき
			// 爆撃範囲が外に出そうであれば、補正する。
			if (point.x <= 0) {
				point.x = 1;
			}else if(point.x >= 8){
				point.x = 7;
			}
			if (point.y <= 0) {
				point.y = 1;
			}else if(point.y >= 8){
				point.y = 7;
			}
			break;
		case 4:
		case 5:
			// デカいプラス、デカいX型のとき
			// 爆撃範囲が外に出そうであれば、補正するかもしれないが、
			// 補正しない場合もある。
			r = (int)(Math.round(Math.random()));
			if (r == 1) {
				if (point.x <= 1) {
					point.x = 2;
				}else if(point.x >= 7){
					point.x = 6;
				}
				if (point.y <= 1) {
					point.y = 2;
				}else if(point.y >= 7){
					point.y = 6;
				}
			}else{
			}
			break;
		default:
			break;
		}
		return point;
	}
	
	/**
	 * 相手が追尾してくると判定した時の攻撃
	 * 逃げるように、自分がまだ撃ってない座標から選択する場合、ランダムに打つ場合、相手が打ったところから打つ場合から選択する
	 * @param tc 攻撃する座標
	 * @return 攻撃する座標
	 */
	private Point antiTraceAttack(Point tc) {
		while(true) {
			int d = (int)Math.round(Math.random() * 5);
	    	if (d == 0 || d == 1) {
	    		typeAntiTrace = 1;
		    	for (int i = 0; i < myCoordinates.size(); i++) {
					tc.x = (int)(Math.random() * Field.SIZE);
			    	tc.y = (int)(Math.random() * Field.SIZE);
			    	tc = fixTargetCoodinate(tc, missile);
					if ((myCoordinates.get(i).x == tc.x) && myCoordinates.get(i).y == tc.y ) {
						continue;
					}else{
						return tc;
					}	
				}				
			}else if(d == 2 || d == 3 || d == 4) {
				typeAntiTrace = 2;
				tc.x = (int)(Math.random() * Field.SIZE);
		    	tc.y = (int)(Math.random() * Field.SIZE);
		    	return fixTargetCoodinate(tc, missile);
			}else if (d == 5){
				typeAntiTrace = 3;
				for (int i = 0; i < opponentCoordinates.size(); i++) {
					tc.x = (int)(Math.random() * Field.SIZE);
			    	tc.y = (int)(Math.random() * Field.SIZE);
			    	tc = fixTargetCoodinate(tc, missile);
			    	if ((opponentCoordinates.get(i).x == tc.x) && opponentCoordinates.get(i).y == tc.y ) {
			    		return tc;
			    	}else{
			    		continue;
			    	}
				}
			}

		}
	}
	
	/**
	 * 座標を受け取り、攻撃する。
	 */
	private void attack(Point tg) {
		fire(tg.x, tg.y);
	}
	
	/**
	 * ターンごとに呼び出されるメソッド
	 * ここにロボの動きを記述する
	 */
    protected void action(robobattler2.Missile missile) {
    	Point opponentCoordinate = null;
    	Point targetCoordinate;
    	this.missile = missile;
    	int x = (int)(Math.random() * Field.SIZE);
    	int y = (int)(Math.random() * Field.SIZE);
    	targetCoordinate = new Point(x, y);
    	targetCoordinate = fixTargetCoodinate(targetCoordinate, missile);
    	
    	/*初回のみ*/
    	if (firstatk == -1) {
    		initField();
    		firstCheck();
    	}
    	/*ターン数をカウント*/
    	inning++;
    	
    	//System.out.println("inning" + inning);
    	/*先攻で、かつ3ターン以上で追尾チェック*/
    	if (inning >= 3 && firstatk == 1) {
    		checkTrace();
    	}
    	/*初期化するためのフラグ立て*/
    	if (inning >= 16) {
    		/*デバッグ用*/
    		if (firstatk == 1) {
				//System.out.println("先攻でした");
			}else if(firstatk == 0){
				//System.out.println("後攻でした");
			}
    		firstatk = -1;
    	}
    	
    	/*先攻、もしくは相手が打たなかった場合、適当に撃つ*/
    	if (searchTarget() == null) {
    		doCollectCoodinates(targetCoordinate, null);
    		attack(targetCoordinate);
    		return;
    	}else{
    		/*相手が追尾してきてる気がしたとき*/
    		if (antiTrace) {
    			opponentCoordinate = searchTarget();
    			//System.out.println("anti tracemode");
    			targetCoordinate = antiTraceAttack(targetCoordinate);
			}else{
				/*相手のミサイルと一致したら重ねて撃つ*/
				/*時々ずらす*/
				if (missile.getType() == searchType()) {
	    			if (powersearch < 5) {
	    				targetCoordinate = opponentCoordinate = powerSearchTarget();
	    				powersearch++;
	    				int d = (int)Math.round(Math.random() * 2);
	    				if (d == 0) {
							int fx = (int)Math.round(Math.random() * 2 -1); 
							int fy = (int)Math.round(Math.random() * 2 -1);
							targetCoordinate.x += fx;
							targetCoordinate.y += fy;
							targetCoordinate = fixTargetCoodinate(targetCoordinate, missile);
							//System.out.println("あああ");
						}
	    				//System.out.println("Counter successed.");
	    			}
	    		}else{
	    			opponentCoordinate = searchTarget();
	    			targetCoordinate = fixTargetCoodinate(opponentCoordinate, missile);
	    		}
			}
			
    		/*相手の空白を効率よく埋めれそうだったら*/
    		//System.out.println(typeAntiTrace);
    		if (typeAntiTrace == 1 || typeAntiTrace == 3) {
    			if ((missile.getType() == 2 && searchType() == 3) ||
    					(missile.getType() == 3 && searchType() == 2) ||
    					(missile.getType() == 4 && searchType() == 5) ||
    					(missile.getType() == 5 && searchType() == 4)) {
    	    			if (powersearch < 5) {
    	    				targetCoordinate = opponentCoordinate = powerSearchTarget();
    	    				powersearch++;
    	    				//System.out.println("ps:b");
    	    			}
    			}
			}
			
    		/*先攻だったときのしがみつき*/
    		if ((inning == 16) && (firstatk == 1)) {
    			targetCoordinate.x = 0;
    			targetCoordinate.y = 0;
    			
			}
    	}
    	//System.out.println("Robonfire: " + missile.getType());
    	doCollectCoodinates(targetCoordinate, opponentCoordinate);
    	attack(targetCoordinate);
    }

}

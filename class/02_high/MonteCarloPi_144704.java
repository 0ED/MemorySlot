/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 例題02_10
 提出日： 10/7
 ファイル名： MonteCarloPi_1444704.java
 プログラムの動作説明：
	モンテカルロ法により，円周率を求める
	（コマンドラインから入力した数値多きほど、
	正確な値となる）プログラム
 実行結果：
 ==========================================

 Task $ java MonteCarloPi_144704 10
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 Program_answer(π /4) = 0.7
 True_answer(π /4) = 0.7853981633974483
 
 Task $ java MonteCarloPi_144704 1000
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 Program_answer(π /4) = 0.788
 True_answer(π /4) = 0.7853981633974483
 
 ==========================================
 */
public class MonteCarloPi_144704 {
	public static void main (String arg[]) {
		
		//初期値after
		int i;
		double theta;
		double x_ran,y_ran;
		boolean hantei;
		
		//初期値now
		int hit=0;
		int siko = Integer.parseInt(arg[0]);
		
		
		//モンテカルロ処理
		for (i = 0; i < siko; i++) {
			
			hantei = false; //初期化
			
			//ランダム処理
			x_ran = Math.random();
			y_ran = Math.random();
			
			
			for (theta = 0; theta <= 90; theta+= 0.1) {
				if (   ( x_ran <= Math.cos(theta) ) && ( y_ran <= Math.sin(theta) )   ) 
					hantei = true;
			}
			
			//ヒット回数計算
			if (hantei == true) hit++; 
		}
	
		
		double answer = (double)hit/(double)siko;
		System.out.println("Program_answer(π /4) = " + answer);
		System.out.println("True_answer(π /4) = " + Math.PI/4);
		
	}
}
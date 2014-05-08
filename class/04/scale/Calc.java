/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習04_01
 提出日： 10/12
 ファイル名： Calc.java
 プログラムの動作説明：
	引数としてx,y,sが渡され、そのx座標とy座標がs倍に
	拡大するプログラム
 実行結果：
 ==========================================
 Task $ java Calc
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 4 6
 
 Task $ java Calc
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 40 80
 ==========================================
 */

public class Calc{
	
	//オブジェクト作成
	Point_144704 pt = new Point_144704();
	
	public void run(){
		int x=2,y=3;
		
		//ptオブジェクトのメソッドに引数を与える
		pt.setScale(x,y,2);
		
		
		//X,Yをそれぞれ拡大するメソッド
		scale();
		
		
		//出力
		pt.show();
	}
	
	public void scale(){
		
		//配列（配列数:2）を作成
		int[] vec = new int[2];
		
		//計算
		vec[0] = pt.x * pt.s; //biggerは倍率
		vec[1] = pt.y * pt.s;
		
		//戻り値
		pt.getScale(vec);
	}
	
	public static void main(String[] args){
		Calc app = new Calc();
		app.run();
	}
}
/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習02_02
 提出日： 10/7
 ファイル名： NewtonMethod_1444704.java
 プログラムの動作説明：
	0より大きい実数をコマンドライン入力すると、
	その実数の平方根を求めてくれるプログラム
 実行結果：
 ==========================================
 
 Task $ java NewtonMethod_144704 0.25
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 sqrt(0.25) = 0.5000000795866174
 
 Task $ java NewtonMethod_144704 2
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 sqrt(2.0) = 1.4142156862745099
 
 Task $ java NewtonMethod_144704 3
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 sqrt(3.0) = 1.7320508100147276
 
 Task $ java NewtonMethod_144704 4
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 sqrt(4.0) = 2.0
 
 Task $ java NewtonMethod_144704 1000000000000
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 sqrt(1.0E12) = 1000000.0
 
 ==========================================
 */

public class NewtonMethod_144704{
	
	private double delta = 0.0001;
	private double startX = 2d; //double = 2.0
	
	public double calculate(double alpha){
		
		double x = startX; /* 最初: x=2 */
	
		
		if(alpha < 0){
			return Double.NaN; //マイナスなら（平方根の結果が虚数になるから）
		}
		while(Math.abs(x*x-alpha) > delta){
			x = x - (x*x-alpha) / (2*x);
		}
		return x;
	}
	
	public static void main(String args[]){
		double alpha =  Double.parseDouble(args[0]);

		//Newtonオブジェクト作成
		NewtonMethod_144704 nm = new NewtonMethod_144704();
		
		//計算結果をvalueに代入
		double value = nm.calculate(alpha);
		
		//出力
		System.out.println( "sqrt(" + alpha + ") = " + value );
	}
}
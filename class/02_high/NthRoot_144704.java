/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 例題02_11
 提出日： 10/7
 ファイル名： NthRoot_144704.java
 プログラムの動作説明：
	コマンドラインからdouble型の値（kとn）を2つ入力すると、
	累乗根:[n]root(k) が求まるプログラム
 実行結果：
 ==========================================
 
 Task $ java NthRoot_144704 5 2
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 [2.0]root(5.0) = 2.236085834662618
 
 Task $ java NthRoot_144704 1000 5
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 [5.0]root(1000.0) = 3.9810717663754622
 
 Task $ java NthRoot_144704 2.718 1000
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 [1000.0]root(2.718) = 1.0010004123372653
 
 ==========================================
*/

public class NthRoot_144704 {
	private double delta = 0.0001;
	private double startX = 2d; //double = 2
	
	// x^nを求める
	public double x_n(double x, double n){
		double answer = 1;
		for (int i = 0; i < n; i++) answer *= x;
		return answer;
	}
	
	// f(x)
	public double f(double x, double k, double n) {
		double answer = x_n(x,n) - k;
		return answer;
	}
	
	// f'(x)
	public double f_dash(double x, double k, double n) {
		double answer = (n-1) * x_n(x,n);
		return answer;
	}
	
	public double keisan(double k, double n){
		
		double x = startX; //x = 2
		
		if(k < 0){
			return Double.NaN; //マイナスなら（平方根の結果が虚数になるから）
		}
		while(Math.abs( f(x,k,n) ) > delta){
			x = x - f(x,k,n) / f_dash(x,k,n);
		}
		/*上の式は 0 = f'(x0)*(x-x0) + f(x0)を変形させた形
		 すなわち、x = x0 - f'(x0)/f(x0)の式である
		 f(x)が0に近づくまで接線とX軸との交点のX座標を求めつづける　
		*/
		
		return x;	
	}
	
	public static void main(String args[]){
		double k =  Double.parseDouble(args[0]); //kを受け取る
		double n =  Double.parseDouble(args[1]); //nを受け取る 

		//Newtonオブジェクト作成_
		NthRoot_144704 nr = new NthRoot_144704();
		
		//kのn乗根を求める
		System.out.println("["+n+"]root("+k+") = " + nr.keisan(k,n));	
	}
}
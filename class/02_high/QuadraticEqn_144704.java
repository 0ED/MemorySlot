/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 例題02_09
 提出日： 10/7
 ファイル名： QuadraticEqn_1444704.java
 プログラムの動作説明：
	ax2+bx+c=0のa, b, cをコマンドライン引数として与え
	られたときの2次方程式の解を出力するプログラム
 実行結果：
 ==========================================
 
 Task $ java QuadraticEqn_144704 1 3 2
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 x = -1.0, -2.0
 
 Task $ java QuadraticEqn_144704 1 2 1
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 x = -1.0
 
 Task $ java QuadraticEqn_144704 1 1 1
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 x = (-1.0 + 3.0i)/2.0, (-1.0 - 3.0i)/2.0
 
 ==========================================
 */
public class QuadraticEqn_144704 {
	public static void main (String arg[]) {
		
		double a = Double.parseDouble(arg[0]);
		double b = Double.parseDouble(arg[1]);
		double c = Double.parseDouble(arg[2]);
		double x1,x2;
		double d_hanbetu = b*b-4*a*c;
		
		
	
		if (d_hanbetu > 0) { //実数解が2つあるとき
			x1 = ( -b + Math.sqrt(d_hanbetu) ) / (2*a);
			x2 = ( -b - Math.sqrt(d_hanbetu) ) / (2*a);
			System.out.println("x = " +x1+ ", " +x2);
		}
		
		else if (d_hanbetu == 0){ //実数解が1つあるとき
			x1 = - ( b / (2*a) );
			System.out.println("x = "+ -(b / (2*a)) );
			
		}
		else { //虚数解が2つあるとき
			System.out.print("x = ("+ -b + " + " + -d_hanbetu + "i)/" + 2*a + ", ");
			System.out.println("("+ -b + " - " + -d_hanbetu + "i)/" + 2*a);
		}


	}
}
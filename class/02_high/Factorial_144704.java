/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 例題02_08
 提出日： 10/7
 ファイル名： Factorial_1444704.java
 プログラムの動作説明：
	コマンドラインで与えられた整数型の値の階乗を
	出力するプログラム
 実行結果：
 ==========================================
 Task $ java Factorial_144704 0
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 0! = 1
 
 Task $ java Factorial_144704 5
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 5! = 120
 
 Task $ java Factorial_144704 20
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 20! = 2432902008176640000
 ==========================================
 */
public class Factorial_144704 {
	public static void main (String arg[]) {
		
		int i;
		int number = Integer.parseInt(arg[0]); //整数型の受け取り
		long answer=1L; //answerの限界はnumberが20より大きいときです
		
		for (i = 0; i <= number; i++) {
			
			if (i == 0) answer = 1L; //0! = 1
			else answer *= i;
			
		}
		System.out.println(number+"! = "+answer);

	}
}
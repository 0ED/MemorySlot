/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習02_01
 提出日： 10/7
 ファイル名： PatternPrinter_1444704.java
 プログラムの動作説明：
	複数の文字Xを並べ変え、図形Xを
	実行結果のように表示するプログラム
 実行結果：
 ==========================================
 Task $ java PatternPrinter_144704
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 X        X
  X      X 
   X    X  
    X  X   
	 XX    
     XX    
    X  X   
   X    X  
  X      X 
 X        X
 ==========================================
*/
 

public class PatternPrinter_144704 {
	public static void main(String arg[]) {
		
		byte x,y;
		
		for (y = 0; y < 10; y++) {
			for (x = 0; x < 10; x++) {
				if (x == y || y == 9-x ) {  
					//y=x, y=-x+9の傾きが表示される（実際、座標自体は反転しているが）
					System.out.print("X"); //文字表示
				}
				else {
					System.out.print(" "); //空白
				}

			}
			System.out.println(); //改行
		}
	}
}
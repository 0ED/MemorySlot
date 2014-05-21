/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 課題03_02
 提出日： 10/10
 ファイル名： Array3D_144704.java
 プログラムの動作説明：
	3行3列の行列の転置行列を求めるプログラム
 
 実行結果：
 ==========================================
 
 Task $ java Array3D
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 1	2	3	
 4	5	6	
 7	8	9
 
 ==========================================
 */
public class Array3D_144704 {
	
	//変換関数
	int[][] transpose(int[][] a) {
		int[][] answer = new int[3][3];
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				answer[x][y] = a[y][x];
			}
		}
		return a;
	}
	
	//出力関数
	void output(int[][] trans) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				System.out.print(trans[x][y] + "\t");
			}
			System.out.println();
		}
	}
	
	
	static public void main(String[] args) {
		
		Array3D_144704 app = new Array3D_144704();
		int[][] a = { {1, 4, 7}, {2, 5, 8}, {3, 6, 9} };
		
		//変換した行列を入れる
		int[][] trans = app.transpose(a);
	
		//出力
		app.output(trans);
	}
	
}
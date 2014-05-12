/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 課題03_01
 提出日： 10/7
 ファイル名： ArrayCopy_144704.java
 プログラムの動作説明：
	16個の配列を作成し、i番目の要素の値が2^iとなるような配列にする
	その後、配列を反転させ、その配列の奇数個目のみを表示するプログラム
 実行結果：
 ==========================================
 
 Task $ java ArrayCopy_144704
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 hanten[1] = 16384
 hanten[3] = 4096
 hanten[5] = 1024
 hanten[7] = 256
 hanten[9] = 64
 hanten[11] = 16
 hanten[13] = 4
 hanten[15] = 1
 
 ==========================================
 */
public class ArrayCopy_144704 {
	public void run() {
		int[] shinsu_2 = new int[16];
		int[] hanten = new int[16];
				
		//2^nを配列として作る
		shinsu_2[0] = 1;
		for (int i = 0; i < shinsu_2.length - 1; i++) {
			shinsu_2[i + 1] = shinsu_2[i] * 2;
		}
		
		//反転処理
		for (int i = 0; i < shinsu_2.length; i++) {
			hanten[i] = shinsu_2[shinsu_2.length - 1 - i];
		}
		
		
		//出力（デバッグ）
		for (int i = 0; i < hanten.length; i++) {
			if (i % 2 != 0) {
				System.out.println("hanten["+i+"] = " + hanten[i]);
			}
		}
		
	}
	
	public static void main(String[] args) {
		ArrayCopy_144704 app = new ArrayCopy_144704();
		app.run(); //アプリ（メソッド起動）
	}
	
}
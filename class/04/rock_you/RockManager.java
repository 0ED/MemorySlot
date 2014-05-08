/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 課題04_04
 提出日： 10/22
 ファイル名： RockManager.java
 プログラムの動作説明：
	乱数で指定した重さ（weight）を10個の配列に格納し、
	それぞれの配列を表示し、配列の数をすべて足した解を
	表示するプログラム。
 実行結果：
 ==========================================
 Task $ java RockManager
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 2.904966417434347
 4.10682401210277
 7.987753706998353
 3.3653675290902836
 8.933830456615848
 7.331375499313764
 8.478986645076201
 3.62826646753212
 9.94179434960247
 9.56976736079028
 重さの合計 = 66.24893244455643
 ==========================================
 */
public class RockManager {
	public static void main(String[] arg) {
		
		//クラスの配列を10個確保する
		Rock[] weight = new Rock[10];
		double sum=0;
		
		for (int i = 0; i < weight.length; i++) {
			weight[i] = new Rock( (Math.random()*9.5) + 0.5 );
		}
		
		for (int i = 0; i < weight.length; i++) {
			System.out.println(weight[i].getWeight());
			sum += weight[i].getWeight();
		}
		System.out.println("重さの合計 = " + sum);
	}
}
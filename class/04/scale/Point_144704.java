/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習04_01
 提出日： 10/12
 ファイル名： Point_144704.java
 プログラムの動作説明：
	引数、戻り値、出力のメソッドをもつオブジェクト
 */

class Point_144704 {
	int x,y,s; //フィールド（sは倍率）
	
	//引数メソッド
	void setScale(int x, int y, int s) {
		this.x = x;
		this.y = y;
		this.s = s;
	}
	
	//返り値メソッド
	void getScale(int[] array){
		this.x = array[0];
		this.y = array[1];
	}
	
	//出力メソッド
	void show() {
		System.out.println(this.x + " " + this.y);
	}	
}
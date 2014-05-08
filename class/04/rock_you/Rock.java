/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 課題04_03
 提出日： 10/22
 ファイル名： Rock.java
 プログラムの動作説明：
	重さを受け取り、それを返すクラスプログラム
*/
class Rock { 
	double weight=0;
	
	//コンストラクタ
	public Rock(double weight) {
		this.weight = weight; //重さを設定
	}
	
	double getWeight() {
		return this.weight;
	}
}
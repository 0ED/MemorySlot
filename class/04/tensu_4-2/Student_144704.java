/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 課題04_02
 提出日： 10/22
 ファイル名： Student_144704.java
 プログラムの動作説明：
	引数として、3つの科目の点数をもらい、
	戻り値として、3つの科目の平均、最大、最小を
	それぞれ返し、学生証番号が正しいが判別する
	クラスプログラム
 実行結果：
 ==========================================
 
 Task $ java Evaluator
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 平均: 63.333333333333336, 最大: 80, 最小: 50
 
 Task $ java Evaluator
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 引数が負の数が入っているので、正の数に変更しました
 引数を再度確認してください。
 平均: 63.333333333333336, 最大: 80, 最小: 50
 
 ==========================================
 */
class Student_144704 {
	
	//フィールド（可視化しやすいようにprivateに）
	private int id;
	private String name;
	private int[] score = new int[3]; 
	/*一般的に、点数は小数になる事は無く、範囲は正の数の0~100（もしくは0~無限）。
	ここでは、点数の範囲がわからないので、0~ほぼ無限までとした。*/
	
	
	//コンストラクタ（戻り値はないし、つけない。引数のみ）
	public Student_144704 (String id, String name) {
		this.id = Integer.parseInt(id);
		this.name = name;
	}
	
	
	//setter（引数） score[0]=math, score[1]=japan, score[2]=english
	void setMathScore(int score) {
		if (score < 0) {
			score = -score;
			System.out.println("点数に負の数が入っているので、正の数に変更しました");
			System.out.println("点数を念のため、確認してください。");
		}
		this.score[0] = score;
	}
	
	void setJapaneseScore(int score) {
		if (score < 0) {
			score = -score;
			System.out.println("点数に負の数が入っているので、正の数に変更しました");
			System.out.println("点数を念のため、確認してください。");
		}
		this.score[1] = score;
	}
	
	void setEnglishScore(int score) {
		if (score < 0) {
			score = -score;
			System.out.println("点数に負の数が入っているので、正の数に変更しました");
			System.out.println("点数を念のため、確認してください。");
		}
		this.score[2] = score;
	}
	
	
	
	//getter（戻り値）
	double getAverage() { //平均
		double average = 0;
		for (int i = 0; i < this.score.length; i++) {
			average += this.score[i]; //double = double + int なので、変換しなくてよい。
		}
		average = average / this.score.length; //科目の個数分だけ割る
		return average;
	}
	
	int getMax() { //最大値
		int max = score[0]; 
		for (int i = 0; i < this.score.length; i++) {
			/*1 <= i <= score.length + 1とするべきだが、
			見やすさを考えれてこのようにした。*/
			if (score[i] > max) max = score[i];
		}
		return max;
	}

	int getMin() { //最小
		int min = score[0]; //これなら、値が大きい初期値でも対応できる
		for (int i = 0; i < this.score.length; i++) {
			if (score[i] < min) min = score[i];
		}
		return min;
	}
	
	
	//学生証番号の合計をだし、下桁が0か判定
	boolean isValidId() {
		
		int n = this.id, back_n=0;
		int sum=0, count=0;
		
		//与えられた数が何桁かを計算
		for (int i = 1; i <= n; i*= 10) {
			if (n / i < 1) break;
			count++; 
		}
		
		//1文字ずつ読み込み、足し合わせる
		for (int i = 0; i < count; i++) {
			back_n = n;
			n = n / 10; //nの値が変化する
			sum = sum + back_n - (n * 10);
		}	
		
		//足した合計が10の倍数（下桁が0）ならtrue
		return sum % 10 == 0; 
	}
}


public class Evaluator {
	
	//サブルーチン（引数はint型のscore）
	public void print (int score) {
		if (90 <= score && score <= 100) System.out.println("秀");
		else if (80 <= score && score <= 89) System.out.println("優");
		else if (70 <= score && score <= 79) System.out.println("良");
		else if (60 <= score && score <= 69) System.out.println("可");
		else if (0 <= score && score <= 59) System.out.println("不可"); 
		//成績がマイナスになることはないだろうから、0から59とした。
		
		else System.out.println("不正な成績");
	}
	
	//メインルーチン
	public static void main (String args[]) {
		
		//classを唱える
		Evaluator ev;
		ev = new Evaluator();
		
		int score = Integer.parseInt(args[0]); //scoreに70を代入
		ev.print(score); 
		//オブジェクトevの中のフィールドであるprint関数に仮引数scoreを代入し、実行させる。
	}
}
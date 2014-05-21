public class Example0207 {
	
	//サブルーチン（引数はint型のscore）
	public void print (char grade) {
		
		switch (grade) {
			case 'S':
				System.out.println("非常に優秀な成績で合格する.");
				break;
			case 'A':
				System.out.println("優秀な成績で合格する.");
				break;
			case 'B':
				System.out.println("良い成績で合格です.");
				break;
			case 'C':
				System.out.println("合格です.");
				break;
			case 'D':
				System.out.println("不合格です.");
				break;
				
			default:
				System.out.println("成績が正しくありません.");
				break;
		}
		//成績がマイナスになることはないだろうから、0から59とした。
		
	}
	
	public char hantei (int score) {
		if (90 <= score && score <= 100) return 'S';
		else if (80 <= score && score <= 89) return 'A';
		else if (70 <= score && score <= 79) return 'B';
		else if (60 <= score && score <= 69) return 'C';
		else if (0 <= score && score <= 59) return 'D';
		else return '';

	}
	
	
	//メインルーチン
	public static void main (String args[]) {
		
		//classを唱える
		Example0207 ex;
		ex = new Example0207();
		
		int score = Integer.parseInt(args[0]); //scoreにコマンドラインで入力した文字を代入
		char a = ex.hantei(score);
		ex.print( a ); //char型戻り値を引数にして、ev.printに代入
	}
}
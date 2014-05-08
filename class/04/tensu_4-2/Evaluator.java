public class Evaluator{
	public Evaluator(){
		
		Student_144704 s1 = new Student_144704("144704", "玉田");
		
		
		if( s1.isValidId() == false) { //間違った学生証番号なら
			System.out.println("学生証番号が間違っています。入力し直してください。");
		}
		else {		//正しい学生証番号なら
			s1.setMathScore(60);
			s1.setJapaneseScore(80);
			s1.setEnglishScore(50);
	
			//出力
			System.out.println(
						   "平均: " + s1.getAverage() +
						   ", 最大: " + s1.getMax() +
						   ", 最小: " + s1.getMin());
		}
	}
	
	public static void main(String[] args){
		Evaluator evaluator = new Evaluator(); //コンストラクタはそのまま実行される
	}
}
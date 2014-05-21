public class ExamClass {

	//フィールド
	private ExamScore[] scores = new ExamScore[10];

	public void setExamScore(int num, ExamScore score) {
		this.scores[num] = score;
	}

	
	//クラス全員分の数学の平均
	public double getMathAve() { 
		double sum;
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i].getMath();
		}
		return sum / scores.length;
	}

	//クラス全員分の物理の平均
	public double getPhysAve() {
		double sum;
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i].getPhys();
		}
		return sum / scores.length;
	}


	public double getAve() {
		return (getMathAve() + getPhysAve() ) / 2.0;
	}


	public ExamClass() {
		//空っぽ
	}






	//メイン
	public static void main(String[] args) {
		ExamClass[] classes = new ExamClass[3];
        ExamScore[i] score = new ExamScore(Math.random()*100,Math.random()*100);

		for(int y = 0; y < 3; y++) { //組数：3組
			for (int x = 0; x < 10; x++) { //人数：10人
                classes[y].setExamScore(x, score);
            }
		}
	
		for(int y = 0; y < 3; y++) { //組数：3組
			System.out.println("class"+y+"：数学の平均 = " + classes[y].getMathAve() );
			System.out.println("class"+y+"：物理の平均 = " + classes[y].getPhysAve() );
			System.out.println("class"+y+"：両科目の平均 = " + classes[y].getAve() );
		}
	}
}


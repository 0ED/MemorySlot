public class ExamScore {
		
		//フィールド
		private int math; //（数学の点数）
		private int phys; //（物理の点数）
		private double ave; //（2科目の平均点）
		
		public void setMath(int m) {
			if (m < 0|| 100 < m){
				System.out.println("点数が範囲外です");
				math = 0;
			}
			math = m;
		}
		
		public void setPhys(int p) {
			if (  p < 0  || 100 < p) {
				System.out.println("点数が範囲外です");
				phys = 0;
			}
			phys = p;
		}
		
		public int getMath() {return math;}
		public int getPhys() {return phys;}
		public double getAve() {
			ave = (double)(math+phys) / 2;
			return ave;
		}
		
		
		//コンストラクタ
		private ExamScore() { //（点数を全て0点とする）
			math = 0;
			phys = 0;
		}
		public ExamScore(int m, int p) { //（点数を設定する）
			if (  m < 0  || 100 < m) {
				System.out.println("点数が範囲外です");
				math = 0;
			}
			if (  p < 0  || 100 < p) {
				System.out.println("点数が範囲外です");
				phys = 0;
			}
			math = m;
			phys = p;
		}
}
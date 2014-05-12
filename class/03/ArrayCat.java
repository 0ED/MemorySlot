public class ArrayCat {
	public void run() {
		int[] three = new int[10]; //0 ~ 9
		int[] two = new int[20];
		int[] hanten = new int[20];
		
		//3の倍数を格納（配列数: 10）
		three[0] = 3;
		for (int i = 0; i < three.length - 1; i++) {
			three[i + 1] = three[i] + 3;
		}
		
		//2の倍数を格納（配列数: 20）
		two[0] = 2;
		for (int i = 0; i < two.length - 1; i++) {
			two[i + 1] = two[i] + 2;
		}
		
		//twoの10配列にthreeを代入（配列数: 20）
		for (int i = 0; i < three.length; i++) {
			two[i + 10] = three[i];
		}
	
		
		//文字反転処理（配列数: 20）
		for (int i = 0; i < two.length; i++) hanten[i] = two[19-i];
		
		
		//出力（デバッグ）
		for (int i = 0; i < two.length; i++) {
			System.out.println(hanten[i]);
		}
		
	}
	
	public static void main(String[] args) {
		ArrayCat app = new ArrayCat();
		app.run();
	}
	
}
public class Fibonacchi {
	public void run(int number) {
		int[] fibo = new int[number]; //数列を確保する配列を用意
		fibo[0] = 0;
		fibo[1] = 1;
		for (int i = 0 ; i < fibo.length - 2; i++) {
			fibo[i + 2] = fibo[i] + fibo[i + 1]; //過去の値と現在の値を計算し、未来の値へ
		}
		for (int i = 0; i < fibo.length; i++) {
			System.out.println(fibo[i]); //fiboを出力
		}
	}
	
	public static void main(String[] args) {
		Fibonacchi app = new Fibonacchi();
		int number = Integer.parseInt(args[0]);
		app.run(number);
	}
	
}
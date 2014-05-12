public class Aho {
	
	public boolean hantei (int n) {
		
		int count=0;
		int back_n,answer;
		boolean bool = false; //初期化
		
		//与えられた数が何桁かを計算
		for (int i = 1; i <= n; i*= 10) {
			//System.out.println("i = " + i + " count = " + count);
			if (n / i < 1) break; 
			count++; 
		}
		
		//nが3の倍数なら、trueを返す
		if (n % 3 == 0) bool = true;
		
		//3がつく数字があれば、trueを返す
		for (int e = 0; e < count; e++) {
			back_n = n; 
			n = n / 10; //nの値が変化する
			answer = back_n - (n * 10);
			if (answer == 3) bool = true;
		}	
		
		return bool;
	}
	
	public static void main(String arg[]) {
		
		int n = Integer.parseInt(arg[0]);
		Aho aho = new Aho();
		
		if (n > 1000000) System.out.println("値が大きすぎるから、もう一度入れ直してね");
		else {
			for (int i = 1; i <= n; i++) {
				if (aho.hantei(i) == true) {
					System.out.println(i + " アホ");
				}
				else {
					System.out.println(i);
				}
			}
		}
		
	}
}
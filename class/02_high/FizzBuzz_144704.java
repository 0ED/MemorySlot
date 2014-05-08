public class FizzBuzz_144704 {
	public static void main (String arg[]) {
		
		int i;
		int number = Integer.parseInt(arg[0]);
		
		for (i = 1; i <= number; i++) {
			
			//3で割り切れるとき
			if (i % 3 == 0) {
				if(i % 5 == 0) System.out.println("FizzBuzz");//3と5で割り切れる場合
				else  System.out.println("Fizz");//3で割り切れるとき
			}
			else if (i % 5 == 0) {
				System.out.println("Buzz"); //5で割り切れるとき
			}
			else {
				System.out.println(i);
			}
		}
	}
}
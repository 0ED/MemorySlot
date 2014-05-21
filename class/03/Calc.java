public class Calc {
	public static void main(String[] args) {
		int answer;
		int amari;
		int number_1 = Integer.parseInt(args[0]);
		int number_2 = Integer.parseInt(args[2]);
		
		if (args[1].equals("+") ) {
			answer = number_1 + number_2;
			System.out.println(answer);
		}
		
		else if (args[1].equals("-") ) {
			answer = number_1 - number_2;
			System.out.println(answer);
		}
		
		else if (args[1].equals("x") ) {
			answer = number_1 * number_2;
			System.out.println(answer);
		}
		
		else if (args[1].equals("/")) {
			answer = number_1 / number_2;
			amari = number_1 % number_2;
			System.out.println(answer + "..." + amari);
		}
		
		else {
			System.out.println("入力し直してください");
		}

		
	}
}
public class Conversion {
	public static void main(String arg[]) {
		
		//型宣言+文字列の変換
		int zero = Integer.parseInt("0");
		double pi = Double.parseDouble("3.14");
		
		//出力
		System.out.println("変数：zero = " + zero);
		System.out.println("文字列：zero = " + Integer.toString(zero) );
		System.out.println("変数：pi = " + pi);
		System.out.println("文字列：pi = " + Double.toString(pi) );
		
	}
}
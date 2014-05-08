public class StringCompare {
	public static void main(String arg[]) {
		String a="string", b, c;
		
		//代入
		b = a; 
		c = arg[0];
		
		//出力
		System.out.println("a: " +a+ " b: " +b+ " c: " +c);
		
		
		System.out.println("a == b: " + (a == b) ); /*かっこをつけるとそこから優先的に計算される*/
		System.out.println("b == c: " + (b == c) ); /*"=="は参照している場所が同じかどうかを判断する*/
		System.out.println("c == a: " + (c == a) );
		System.out.println("a equals b: " + a.equals(b) ); /*中身が本当に一緒かどうかを1文字づつ確認する*/
		System.out.println("b equals c: " + b.equals(c) );
		System.out.println("c equals a: " + c.equals(a) );
	}
}
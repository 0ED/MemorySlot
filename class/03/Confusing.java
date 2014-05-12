public class Confusing {
	public void run() {
		int p =	1;
		int[] q = {1, 2, 3};
		zero(p,q);
		System.out.println(p);
		System.out.println(q[0]);
	}
	
	public void zero(int i, int[] a) { 
		
		
		//i = p; ローカル変数i が p に
		//a = q; ローカル変数a のポインタが q に
		
		i = 0;
		a[0] = 0;
	}
	
	public static void main(String[] args) {
		Confusing c = new Confusing();
		c.run();
	}
}
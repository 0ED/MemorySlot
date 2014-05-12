public class Example0213 {
	public static void main(String[] args) {
		int[] phys = {10, 20};
		int[] phys2;
		phys2 = phys;
		int[] phys3 = {10, 20};
		System.out.println(phys == phys2);
		System.out.println(phys == phys3);
	}
}
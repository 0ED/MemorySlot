public class Array2D {
	static public void main(String[] args) {
		int[][] array1 = new int[3][3];
		int[][] array2 = {{1, 2, 3}, {4, 5}, {6}};
		
		for (int i = 0; i < 3; i++) {
			System.out.println("array1[" + i + "] " + array1[i].length);
			System.out.println("array2[" + i + "] " + array2[i].length);
		}
		
		array1[2][2] = 9;
		array2[2][2] = 9;
	}
}
class Exercise52 {
	
	static int classVar = 0;
	int instanceVar = 0;
	
	public static void main(String[] args) {
		
		Exercise52 obj1 = new Exercise52();
		Exercise52 obj2 = new Exercise52();
		
		obj1.classVar = 1;
		obj1.instanceVar = 1;
		
		System.out.println("obj2.classVar = " + obj2.classVar); //スタティックをすると、別のオブジェクトからでも値が保たれる
		System.out.println("obj2.instanceVar = " + obj2.instanceVar);
	}
}
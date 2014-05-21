class ArgTest {
	void run() {
		int i = 1;
		MyInt j = new MyInt(2);
		MyInt k = new MyInt(3); 
		iMod(i); 
		jMod(j); 
		kMod(k);
		System.out.println("i = " + i);
		System.out.println("j = " + j.getValue() );
		System.out.println("k = " + k.getValue() );
	}
	
	void iMod(int val) { 
		val = 4; //引数を書き変えただけでは何も変わらない
	}
	
	
	void jMod(MyInt val) { //"MyInt j"が渡される
		val.setValue(5);
	}
	
	
	void kMod(MyInt val) { //"MyInt k"が渡される
		val = new MyInt(6); //引数を書き換えただけでは何も変わらない
	}
	
	
	
	public static void main(String[] args) { 
		ArgTest app = new ArgTest(); 
		app.run();
	}
}
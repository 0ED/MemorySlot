public class Triangle {
	
	//サブルーチン
	public void draw() {
		Turtle t = new Turtle();
		t.move(200, 230);
		t.penDown();
		t.rotate(30);
		t.go(60);
		t.rotate(60);
		t.go(60);
		t.rotate(60);
		t.go(60);
		t.rotate(60);
		t.go(60);
		t.rotate(60);
		t.go(60);
		t.rotate(60);
		t.go(60);
	}

	//メイン
	public static void main(String[] args) {
		Triangle tri = new Triangle();
		tri.draw();	
	}

}
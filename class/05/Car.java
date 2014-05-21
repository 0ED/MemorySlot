/*
 144704 高橋 右
 2つエラーが出ています
 時間がないので出しときます。
*/
public class Car {
	
	//フィールド
	private String driver; //ドライバーの名前
	private int[] position = new int[2]; //位置
	private int speed; //速度
	private int theta; //角度
	private Tire[] tires = new Tire[4];
	
	
	//コンストラクタ
	public Car(String driver) { 
		this.driver = driver; 
		
		//4つのタイヤの初期値を設定
		for(int i = 0; i < tires.length; i++){
			tires[i] = new Tire(1.0, 2.5);
		}
	}
	
	
	//返し
	public String getDriver() { return this.driver; }
	public int getPosition() { return this.position; }
	public int getSpeed() { return this.speed; }
	public int getTheta() { return this.theta; }
	
}
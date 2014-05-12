/*144704 高橋 右*/
public class Tire {
	
	//フィールド
	private float rub; //摩耗度
	private float air;
	
	
	public Tire(float rub, float air) {
		this.rub = rub;
		this.air = air;
	}
	
	float getRub() {return rub; }
	float getAir() {return air; }
}
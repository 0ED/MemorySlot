public class Example0301 {
	public static void main(String[] args){
		
		MemberCard m1 = new MemberCard();
		MemberCard m2 = new MemberCard();
		
		//初回得点ポイント
		m1.init(1,"玉田",2000);
		m2.init(2,"高橋",200);
		
		
		//ポイント使った（m1）
		m1.usePoints(20);
		
		//ポイント使った（m2）
		m2.usePoints(20);
		m2.usePoints(19);
		
		
		//ポイント参照
		m1.show();
		m2.show();
	}
}
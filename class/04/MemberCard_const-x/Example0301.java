public class Example0301 {
	/*staticメソッド
	（thisはstaticの中では定義できない）
	余計なときにはstaticフィールドは定義しない*/
	
	public static void main(String[] args){
		
		MemberCard m1 = new MemberCard("玉田"); //みんな初期化してくれる
		MemberCard m2 = new MemberCard("高橋",200);
		MemberCard m3 = new MemberCard("ああ",400);
		
		//ポイント使った（m1）
		//m1.usePoints(20);
		
		//ポイント使った（m2）
		//m2.usePoints(20);
		
		
		//自己破産
		//m_card.init(0,"",0);
		
		//ポイント参照
		m1.show();
		m2.show();
		m3.show();
	}
}
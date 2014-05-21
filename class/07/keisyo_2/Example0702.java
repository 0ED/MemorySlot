public class Example0702{
	public static void main(String[] args){
		GoldMemberCard g1 = new GoldMemberCard("玉田", 1000);
		String name1 = g1.getName();
		System.out.println("会員氏名は"+ name1 + "です．");
		g1.addPoints(100);
		g1.show();
	}
}
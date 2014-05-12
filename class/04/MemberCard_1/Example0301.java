public class Example0301 {
	public static void main(String[] args){
		
		MemberCard m_card = new MemberCard();
		
		
		m_card.setId(1);
		m_card.setName("玉田先生");
		m_card.addPoints(2000);
		m_card.show();
	}
}
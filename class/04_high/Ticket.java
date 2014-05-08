/*エラー0*/
public class Ticket {
	private Event event;
	private String rank; //座席のランク
	private int price;
	
	//setter
	public Ticket(Event event, String rank, int price) {
		this.event = event;
		this.rank = rank; 
		this.price = price;
	}
	
	//getter
	Event getEvent() {return event;}
	String getRank() {return rank;}
	int getPrice() {return price;}
	
	//出力
	void show() {
		event.show();
		System.out.println("座席のランク = " + rank);
		System.out.println("値段 = " + price);
	}
}
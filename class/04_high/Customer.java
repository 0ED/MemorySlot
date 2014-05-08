/*エラー17*/
public class Customer {
	private String name; //お客さんの名前
	private Ticket[] tickets = new Ticket[10];
	static int i=0;
	
	public Customer(String name) {
		this.name = name;
	}
	
	void buy(Ticket tickets) {
		this.tickets[i] = tickets;
		i++;
	}
	
	//出力
	void show() {
		System.out.println("名前 = " + name);
		tickets[i].show();
	}	
}
public class TicketManager {
	void run() {
		Event[] events = new Event[3];
		Customer[] customers = new Customer[2];
		Ticket[] tickets = new Ticket[2];
		
		
		//イベント（引数は日時、興行主、場所）
		events[0] = new Event("1月1日","秋元康", "武道館"); 
		events[1] = new Event("4月4日","キム・ジョンウン", "アメリカ");
		events[2] = new Event("10月1日","木村勉", "天下一品");
		
		
		
		//お客さん（引数は）
		customers[0] = new Customer("大仁田厚");
		customers[1] = new Customer("真鍋アナ");
		
		
		//チケット（引数はイベントの詳細、座席の階級、チケット価格）
		tickets[0] = new Ticket(events[0],"A",10000);
		tickets[1] = new Ticket(events[1],"B",10);
		
		//チケット購入（引数はチケット情報）
		customers[0].buy(tickets[1]);
		customers[0].buy(tickets[0]);
		customers[0].buy(tickets[0]);
		customers[0].buy(tickets[1]);
		customers[0].show();
		
		customers[1].buy(tickets[0]);
		customers[1].buy(tickets[1]);
		customers[1].buy(tickets[0]);
		customers[1].buy(tickets[1]);
		customers[1].show();
		
	}
	
	public static void main(String args[]) {
		TicketManager app = new TicketManager();
		app.run();
	}
}
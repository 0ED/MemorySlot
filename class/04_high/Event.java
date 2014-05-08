/*エラー0*/
public class Event {
	private String date;
	private String promoter; //興行主
	private String place;
	
	//setter
	public Event(String date, String promoter, String place) {
		this.date = date;
		this.promoter = promoter; 
		this.place = place;
	}
	
	//getter
	String getDate() {return date;}
	String getPromoter() {return promoter;}
	String getPlace() {return place;}
	
	//出力
	void show() {
		System.out.print("開催日時 = " + date);
		System.out.print(" 興行主 = " + promoter);
		System.out.println(" 開催場所 = " + place);
	}
}
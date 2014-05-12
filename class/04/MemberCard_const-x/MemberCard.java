class MemberCard {
	private int id;
	private String name;
	private int points;
	private static int numberOfMember;
	static { 
		numberOfMember = 1;
	}
	
	
	void setId(int id) {
		this.id = id;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void addPoints(int point) {
		this.points = this.points + point;
	}
	
	void usePoints(int point) {
		int fusoku;
		
		if (points < point) {
			point = points;
		}
		this.points = this.points - point;
	}
	
	
	
	//show（出力）メソッド
	void show() {
		System.out.println("会員番号は" + id + "番");
		System.out.println("会員氏名は" + name);
		System.out.println("累計ポイントは" + points + "pt");
	}
	
	//MemberCard
	public MemberCard(String name,int initialPoints) {
		this.id = numberOfMember;
		this.name = name;
		this.points = initialPoints;
		numberOfMember++;
	}
	
	public MemberCard(String name) {
		this(name,50);
	}
	
}



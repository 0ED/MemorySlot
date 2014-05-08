class MemberCard {
	int id;
	String name;
	int points;
	
	
	void setId(int id) {
		this.id = id;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void addPoints(int point) {
		this.points = this.points + point;
	}
	
	
	void show() {
		System.out.println("会員番号は" + id + "番");
		System.out.println("会員氏名は" + name);
		System.out.println("累計ポイントは" + points + "pt");
	}
}



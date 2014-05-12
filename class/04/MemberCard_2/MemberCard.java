class MemberCard {
	private int id;
	private String name;
	private int points;
	
	
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
		if (points <= point) {
			points = 0;
		}
        else {
            this.points = this.points - point;            
        }
	}
	
	
	
	//show（出力）メソッド
	void show() {
		System.out.println("会員番号は" + id + "番");
		System.out.println("会員氏名は" + name);
		System.out.println("累計ポイントは" + points + "pt");
	}
	
	//init（初期化）メソッド
	void init(int id, String name, int initialPoints) {
		this.id = id;
		this.name = name;
		this.points = initialPoints;
	}
}



class GoldMemberCard extends MemberCard { //MemberCardのクラスを受け取る（コンストラクタ以外）
	
	//フィールド
	private static int numberOfGoldMember = 0;
	private int goldNumber;
	
	//コンストラクタ
	public GoldMemberCard(String name,int initPoints){
		super(name, initPoints); //MemberCardのコンストラクタに渡す
		numberOfGoldMember++; //カード（class）を作るごとに溜まって行く
		goldNumber = numberOfGoldMember;
	}
	
	public void addPoints(int points){
		int bonus;
		bonus = (int)(points*0.05);
		this.points += points + bonus;
		System.out.println(points +
						   "ポイントを追加しました（内ボーナスポイント"
						   + bonus + "pt）");
	}
	
	//ゲッター
	public int getGoldNumber(){
		return goldNumber;
	}
}
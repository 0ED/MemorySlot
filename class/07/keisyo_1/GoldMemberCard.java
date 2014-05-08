class GoldMemberCard extends MemberCard{ //MemberCardのクラスを受け取る（コンストラクタ以外）
	
	//フィールド
	private static int numberOfGoldMember = 0;
	private int goldNumber;
	
	//コンストラクタ
	public GoldMemberCard(String name,int initPoints){
		super(name, initPoints); //MemberCardのコンストラクタに渡す
		numberOfGoldMember++; //カード（class）を作るごとに溜まって行く
		goldNumber = numberOfGoldMember;
	}
	
	//ゲッター
	public int getGoldNumber(){
		return goldNumber;
	}
}
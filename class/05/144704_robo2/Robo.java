/*
 氏名： 高橋　右
 学籍番号： 144704
*/ 
class Robo {		
		//フィールド
		private String name; //名前
		private int attack; //攻撃値
		private double armored; //装甲値
		private int hp_now, hp_max; //現在の耐久値 最大の耐久値
		private boolean overheat; //オーバーヒートの有無
	
		
		private static final int NUM_ITEMS = 8; //オブジェクトごとに使う必要は無いので、static
		private Item[] items = new Item[NUM_ITEMS];
}
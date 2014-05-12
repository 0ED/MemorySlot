/*
 氏名： 高橋　右
 学籍番号： 144704
*/ 
class Item_144704 {
			
	/* フィールド
	=================================== */
	//アイテムのパラメータ（各パラメータは初期値から変更される事は無いので、static final）
	private final String item_name; //アイテムの名前
	private final int attack_sobi; //装備時の攻撃修正
	private final int attack_use; //使用時の攻撃修正
	private final double armored_use;  //使用時の装甲修正（装甲：ダメージを受ける確率）
	private final boolean overheat_use;  //使用時のオーバーヒート
	
	
	/* 受け取り
	=================================== */
	//コンストラクタ
	public Item_144704(String item_name, int attack_sobi, int attack_use, double armored_use, boolean overheat_use) {
		this.item_name = item_name;
		this.attack_sobi = attack_sobi;
		this.attack_use = attack_use;
		this.armored_use = armored_use;
		this.overheat_use = overheat_use;
	}
	
	
	//getter
	String getItemName() {return item_name;}
	int getAttackSobi() {return attack_sobi;}
	int getAttackUse() {return attack_use;}
	double getArmoredUse() {return armored_use;}
	boolean getOverheatUse() {return overheat_use;}
	
}
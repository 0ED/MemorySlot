/*
 氏名： 高橋　右
 学籍番号： 144704
*/ 
public class Robo_144704 {
	
	/* フィールド
	=================================== */
	//ロボットのパラメータ
	private String name; //名前
	private int attack; //攻撃値
	private double armored; //装甲値（ダメージを受ける確率）
	private int hp_now, hp_max; //現在の耐久値 最大の耐久値
	private boolean overheat; //オーバーヒートの有無
	
	//定数
	private static final int NUM_ITEMS = 8; //各オブジェクトごとに使う必要は無いので、static
    private static final int CRITICAL = 1/50;
    
    //8つのアイテム作成
	private Item_144704[] items = new Item_144704[NUM_ITEMS];

		
	/* アクセッサーメソッド
	=================================== */
	//コンストラクタ
	public Robo_144704(String name, int attack, double armored, int hp_max) {
		this.name = name;
		this.attack = attack;
		this.armored = armored;
		this.hp_max = hp_max;
	}
	
	//setter
	void setName(String name) {this.name = name;}
	void setAttack(int attack) {this. attack = attack;}
	void setArmored(double armored) {this.armored = armored;}
	void setHpMax(int hp_max) {this.hp_max = hp_max;}
	
	
	//getter
	String getName() {return name;}
	int getAttack() {return attack;}
	double getArmored() {return armored;}
	int getHpMax() {return hp_max;}
	
    
    /* その他のメソッド
     =================================== */
    //アイテムを装着する
    Item_144704 ItemSobi(Item_144704 item, int itemNum) {
        Item_144704 back_item = items[itemNum];//取り外すアイテムをback_itemに一度格納
        this.items[itemNum] = item; //アイテムを入れ替える

        if(items[itemNum] == null) {return null;}
        else {return back_item;}
    }
    
    void ItemUse(int itemNum) {
        //アイテム使用時
        if (items[itemNum] == null) {} //アイテムが無いなら何も起こらない
        else {
            this.attack += items[itemNum].getAttackUse(); //攻撃値（使用時）
            this.attack += items[itemNum].getAttackSobi(); //攻撃値（装備時）
            this.armored += items[itemNum].getArmoredUse();//装甲値（使用時）
            this.overheat = items[itemNum].getOverheatUse(); //オーバーヒートの有無
            items[itemNum] = null; //使用した後はアイテムがなくなる
        }
    }
    
    int attackHantei(int attackReceive){
        if (Math.random() <= armored) {
            hp_now -= attackReceive;
        }
        else {
            if (Math.random() <= CRITICAL) hp_now -= 2*attackReceive;
        }
        if(hp_now < 0) hp_now=0;
        return hp_now;
    }
    
    int Restore(int rest) {
        if(hp_max < hp_now + rest) {
            return this.hp_now = hp_max; //体力が全回復する
        }
        else {
            return hp_now+= rest; //体力がrest分回復する
        }
    }
}
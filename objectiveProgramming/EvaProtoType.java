/* 
 * 1.プロトタイプ
 */
public class EvaProtoType implements Robot {
	/** 
	 * エンジン
	 */
	public int energie;
	
	/**  
	 * ミサイルの数
	 */
	public int numberOfMissile;
	
	/**
	 * エヴァプロトタイプのコンストラクタ。
	 */
	public EvaProtoType(int energie, int n) {
		this.energie = energie;
		this.numberOfMissile = n;
	}
	
	/**
	 * 腕を動かす。
	 */
	public void moveArm() {
		this.energie-=5;
	}
	
	/**
	 * 足を動かす。
	 */
	public void moveFoot() {
		this.energie-=5;
	}
	
	/**
	 * ミサイルを打つ。
	 */
	public void shootMissile() {
		this.energie-=15;
		this.numberOfMissile--;
	}
	
	/**
	 * パラメータを見せる
	 */
	public void showParameter() {
		System.out.println("エネルギー: "+this.energie);
		System.out.println("ミサイルの数: "+this.numberOfMissile);
		System.out.println("------------------");
	}
}
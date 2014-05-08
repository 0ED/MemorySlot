/* 
 * ガンダムタイプ
 */
public class Gundam implements Robot {
	/** 
	 * エンジン
	 */
	private int energie;
	
	/**  
	 * ソードの種類
	 */
	private int sordType;
	
	/**
	 * ガンダムのコンストラクタ。
	 */
	public Gundam(int energie, int sordType) {
		this.energie = energie;
		this.sordType = sordType;
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
	 * ソードで切る。
	 */
	public void cutBySord() {
		this.energie-=15;
	}
	
	/**
	 * パラメータを見せる。
	 */
	public void showParameter() {
		System.out.println("エネルギー: "+this.energie);
		System.out.println("------------------");
	}
}
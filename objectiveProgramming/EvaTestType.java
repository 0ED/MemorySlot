public class EvaTestType extends EvaProtoType
	implements Robot {
	/**
	 * エヴァテストタイプのコンストラクタ。
	 */
	public EvaTestType(int energie, int n) {
		super(energie,n);
	}
	
	public void moveArm() {
		super.energie-=10;
	}
}
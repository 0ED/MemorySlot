/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習04_05
 提出日： 10/28
 ファイル名： ListNode_144704.java
 プログラムの動作説明：
	文字とポインタの引数および返り値を設定する
	ListNode_144704クラス
 実行結果：
 ==========================================
 Task $ java LinkedListDemo a e g e
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 a
 e
 g
 e
 ==========================================
 */
public class ListNode_144704 {
	
	String args = null; //文字を格納
	ListNode_144704 next = null; //ポインタを格納

	//コンストラクタ
	public ListNode_144704(String args) {
		this.args = args;
	}
	
	//ListNodeポインタを受け取る
	void setNext(ListNode_144704 next) {
		this.next = next;
	}
	
	//ListNodeポインタを返す
	ListNode_144704 getNext() {
		return this.next;
	}
	
	//コンストラクタで入れた文字を返す
	String getValue() {
		return this.args;
	}
}
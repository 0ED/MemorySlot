class PachiMax extends Pachi{
    // 定数
    private static final double	OOATARI_NORMAL = 1.0 / 350.0;  // 通常時大当たり確率
    private static final double	OOATARI_KAKUHEN = 1.0 / 35.0;  // 確変時大当たり確率
    private static final double	KAKUHEN = 0.7;  // 確変確率
    private static final int	OOATARI_DEDAMA = 1400;  // 大当たり時の出玉

    // フィールド
    private boolean kakuhen = false;  // 確変状態

    void chusen() {
	/* 大当たり抽選
	   通常時と確変時での大当たり確率は異なる
	*/
        double kakuritsu = kakuhen ? OOATARI_KAKUHEN : OOATARI_NORMAL;//boolean ? すれば、kakuhen=1なら左
        if (Math.random() < kakuritsu) {
            ooatari();
        }
    }

    void ooatari() {
	/* 大当たり処理
	   確変判定で確変状態か確変終了に振り分ける
	 */
        System.out.println("大当たり");
        tama += OOATARI_DEDAMA;
        if (Math.random() < KAKUHEN) {
            System.out.println("  確変!!");
            kakuhen = true;
        } else {
            System.out.println("  確変終了");
            kakuhen = false;
        }
    }
}

class PachiLight extends Pachi{
    // 定数
    private static final double	OOATARI = 1.0 / 100.0;  // 大当たり確率
    private static final double	RENCHAN = 1.0 / 2.5;  // 連チャン確率
    private static final int	OOATARI_DEDAMA = 400;  // 大当たり時の出玉

   

    void chusen() {
	/* 大当たり抽選 */
        if (Math.random() < OOATARI) {
            ooatari();
        }
    }

    void ooatari() {
	/*
	  大当たり処理
	  連荘確率未満で連続して大当たりする
	 */
        System.out.println("大当たり");
        tama += OOATARI_DEDAMA;
        if (Math.random() < RENCHAN) {
            System.out.print("連荘!! ");
            ooatari();
        }
    }
}

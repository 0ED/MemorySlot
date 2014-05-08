/*144704 高橋 右*/
class PachiMiddle extends Pachi{
    // 定数
    private static final double	OOATARI_NORMAL = 1.0 / 200.0;  // 通常時大当たり確率
    private static final int	OOATARI_DEDAMA = 800;  // 大当たり時の出玉
    private static final int	OOATARI_COUNT = 75;  // 大当たり時直後、球を消費せずに大当たり抽選できる回数
    
    void chusen() {
        if (Math.random() < OOATARI_NORMAL) {
            ooatari();
            for(int i = 0; i < OOATARI_COUNT; i++) {
                chusen(); //大当たりが起こると、75回大当たり抽選される
            }
        }
    }
    
    void ooatari() {
        System.out.println("大当たり");
        tama += OOATARI_DEDAMA;
    }
    
}

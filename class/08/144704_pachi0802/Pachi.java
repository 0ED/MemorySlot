/*144704 高橋 右*/
abstract class Pachi {

    //定数
    private static final double	NYUUSHOU = 0.15;  // 入賞確率

    // フィールド
    int	tama = 0;  // 持ち玉数

    // アクセッサ
    void setTama(int tama)  {this.tama = tama;}
    int getTama()  {return tama;}

    void play() throws PachiEmptyException{
	/* 1回のプレイ。玉を1個消費して入賞判定する。
	   入賞したら大当たり抽選に進む
	*/
        if (tama <= 0)
            throw new PachiEmptyException();
        tama--;
        if (Math.random() < NYUUSHOU)  chusen();
    }

    abstract void chusen();
    abstract void ooatari();
}

package memorySlot.bet;

public class BetModel{
    private int chip;
    
    /**
     * Betするチップの初期化する。
     */
    public BetModel(){
        clearChip();
    }
    
    /**
     * Betするチップを追加する。
     * @param chip Betするチップ
     */
    public void addChip(int chip){
        this.chip+= chip;
    }
    
    /**
     * Betしたチップを返す
     * @return Betしたチップ
     */
    public int getChip(){return chip;}
    
    /**
     * Betしたチップをクリアする
     */
    public void clearChip(){chip=0;}
}
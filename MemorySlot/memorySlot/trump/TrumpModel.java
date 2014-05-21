package memorySlot.trump;

public class TrumpModel {

    private String suit;
    private int rank;
    private boolean open;
    private boolean light;
    
    /**
     * トランプの初期設定。引数のスートとランクを設定する。
     * またトランプを裏向けにし、光らせないようにする。
     * @param suit トランプのスート
     * @param rank トランプのランク
     */
    public TrumpModel(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        this.open = false; //裏向き
        this.light = false; //光らせない
    }
    
    
    /**
     * このトランプが表（true）か裏か（false）を設定する。
     * @param open 表（true）か裏か（false）
     */
    public void setOpen(boolean open) {this.open = open;}
    
    
    /**
     * このトランプのスートを返す
     * @return スートの文字列（clab,dia,heart,spad）
     */
    public String getSuit() {return suit;}
    
    
    /**
     * このトランプのランクを返す
     * @return ランクの整数（1~13）
     */
    public int getRank() {return rank;}
    
    
    /**
     * このトランプが表（true）か裏か（false）を返す。
     * @return 表（true）か裏か（false）
     */
    public boolean getOpen() {return open;}

}
package memorySlot.trump;
import memorySlot.Mediator;
import memorySlot.Colleague;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class TrumpArea extends JPanel implements Colleague {
    
    /* スートとランクのリスト */
    private static final String suits[] = {"spad","heart","clab","dia"};
    private static final int ranks[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    
    /* サイズを調節 */
    private static final int HORIZONTAL_GAP = 10; //トランプ同士の幅（水平方向）
    private static final int VERTICAL_GAP = 2;  //トランプ同士の幅（垂直方向）
    public static final int WIDTH = (TrumpView.WIDTH + HORIZONTAL_GAP)*10;
    public static final int HEIGHT = (TrumpView.HEIGHT + VERTICAL_GAP)*6 ;
    
    /* トランプのMVC */
    private Mediator mediator;
    private TrumpModel[][] trumpModel;
    private TrumpView[][] trumpView;
    private TrumpControl[][] trumpControl;
    private boolean[][] trumpRom;

    /**
     * トランプのMVCを52（4*13）作り、shuffleメソッドを使って、ランダムにトランプを配置する。
     */
    public TrumpArea() {
        super();
        setSize(WIDTH,HEIGHT);
        setOpaque(false); //透明
        setLayout(new GridLayout(6,10));
        
        trumpModel = new TrumpModel[suits.length][ranks.length];
        trumpView = new TrumpView[suits.length][ranks.length];
        trumpControl = new TrumpControl[suits.length][ranks.length];
        
        for (int ri=0; ri<ranks.length; ri++) { //0 <= ri < 13
            for (int si=0; si<suits.length; si++) { //0 <= si < 4
                trumpModel[si][ri] = new TrumpModel(suits[si], ranks[ri]);
                trumpView[si][ri] = new TrumpView(trumpModel[si][ri]);
                trumpControl[si][ri] = new TrumpControl(trumpModel[si][ri], trumpView[si][ri], this);
                add(trumpView[si][ri]);
            }
        }
        
        trumpRom = new boolean[suits.length][ranks.length];
        
        for(int ty=0; ty<6; ty++) {
            for(int tx=0; tx<10; tx++) {
                if( !(3<=tx&&tx<=6 && 2<=ty&&ty<=3) ) {
                    Point trump = shuffle(); //Point型のtrump.xがスート、trump.yがランク
                    trumpView[trump.x][trump.y].setLocation(
                        tx*(TrumpView.WIDTH + HORIZONTAL_GAP),
                        ty*(TrumpView.HEIGHT + VERTICAL_GAP)
                    );
                }
            }
        }
        
        
    }
    
    /**
     * ランダムにトランプの配置場所を決め、トランプの座標を返す。
     * @return ランダムに決められたトランプの座標
     */
    private Point shuffle() {
        int suit,rank;
        while(true) {
            suit = (int)(Math.random() * suits.length); //0 <= suit < 4
            rank = (int)(Math.random() * ranks.length); // 0 <= rank < 13
            if(trumpRom[suit][rank]==false) { //初めてのsuitとrankなら
                trumpRom[suit][rank]=true;
                break;
            }
        }
        return new Point(suit, rank);
    }
    
    /**
     * Mediator実装クラスから呼び出され、このクラスとコントローラにMediatorをセットする。
     * @param mediator メディエイター
     */
    public void setMediator(Mediator mediator){
        this.mediator = mediator;
        for (int si=0; si<suits.length; si++) {
            for (int ri=0; ri<ranks.length; ri++) {
                trumpControl[si][ri].setMediator(mediator);
            }
        }
    }
}
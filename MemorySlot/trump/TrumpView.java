package memorySlot.trump;
import memorySlot.MemorySlot;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.awt.event.MouseListener;
import java.awt.Color;


public class TrumpView extends JPanel {
    
    //トランプのポーカーサイズは63:88
    public static final int WIDTH = (int)(63*0.75);
    public static final int HEIGHT = (int)(88*0.75);
    private TrumpModel trumpModel;
    private Image openTrumpImage;
    private Image closeTrumpImage;

    /**
     * トランプの初期設定を行う
     * @param model トランプ情報
     */
    public TrumpView(TrumpModel model) {
        super();
        this.trumpModel = model;
        setOpaque(false); //背景を透明に
        setSize(WIDTH,HEIGHT);
        installTrumpImage(trumpModel.getSuit(),trumpModel.getRank());
    }
    
    
    /**
     * トランプが「開いている」か「閉じている」によって、画像を切り替え描画する。
     * @param g グラフィックスのコンテキスト
     */
    public void paint(Graphics g) {
        super.paint(g);
        if(trumpModel.getOpen()) {
            g.drawImage(openTrumpImage, 0, 0, WIDTH, HEIGHT, this);
        }
        else {
            g.drawImage(closeTrumpImage, 0, 0, WIDTH, HEIGHT, this);
        }
    }
    
    
    /**
     * コントローラから呼ばれ、このトランプパネルのクリックを「受け付ける」ようにする。
     * @param mouseListener マウス受け取りマシン
     */
    void addToTrumpMouseListener(MouseListener mouseListener) {
        this.addMouseListener(mouseListener);
    }
    
    
    /**
     * トランプを「開いたとき」と「閉じたとき」のイメージ画像を読み込む
     * @param suit スート（トランプの柄）
     * @param rank ランク（トランプの数字）
     */
    private void installTrumpImage(String suit, int rank) {
        String file;
        String dir = System.getProperty("user.dir")+File.separator;
        
        //表向きイメージ
        file = "memorySlot/Image/trump/"+suit+"/"+suit+rank+".png";
        openTrumpImage = Toolkit.getDefaultToolkit().getImage(dir+file);
        
        //裏向きイメージ
        file = "memorySlot/Image/trump/close.png";
        closeTrumpImage = Toolkit.getDefaultToolkit().getImage(dir+file);
    }
    
}
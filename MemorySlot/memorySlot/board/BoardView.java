package memorySlot.board;
import memorySlot.MemorySlot;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;


public class BoardView extends JPanel {
    
    public static final int HEIGHT = 640;
    public static final int WIDTH = (int)(HEIGHT * 1.618); //黄金比率の1.618
    private Image boardImage;
    private Image gameTitleImage;
    
    /**
     * 画像を読み込み、その画像を表示する。これがメインの背景画像となる。
     */
    public BoardView() {
        super();
        setOpaque(false);
        setSize(WIDTH, HEIGHT);
        installBoardImage();
    }
    
    /**
     * 背景画像を描画する。
     * @param g グラフィックスのコンテキスト
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(gameTitleImage, 0, 0,280,180, this); //大きさを後で調節
        g.drawImage(boardImage, 0, 10, WIDTH, HEIGHT, this);
    }
    
    /**
     * イメージ画像を読み込んで保存しておく。
     */
    private void installBoardImage() {
        String file;
        String dir=System.getProperty("user.dir")+File.separator;
        file = "memorySlot/Image/background/background.png";
        boardImage = Toolkit.getDefaultToolkit().getImage(dir+file);
        file = "memorySlot/Image/background/gameTitle.png";
        gameTitleImage = Toolkit.getDefaultToolkit().getImage(dir+file);
    }
    
}
package memorySlot.slot;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SlotView extends JPanel{
	
	public static final int WIDTH = 230;
    public static final int HEIGHT = 140;
	public static final int  INTERVAL_TIME = 90;
	
	private SlotModel slotModel;
	private Image[] slotMarkImages;
	private Image slotBoardImage;
	private Image shadowImage;
	
    /**
     * スロットボードイメージと初期のスロットのマーク画像をインストールする。
     * @param slotModel SlotModelクラス
     */
	public SlotView(SlotModel slotModel) {
        this.slotModel = slotModel;
        this.slotMarkImages = new Image[3];
        setOpaque(false);
        setLayout(null);
		setSize(WIDTH, HEIGHT);
		installSlotBoardImage();
        int[] defaultMark = {0,0,0}; //7,7,7
        installSlotMarkImage(defaultMark);
	}
	
    /**
     * インストールされた画像イメージを基に、スロットボード、スロットのマークを描画する。
     * @param g グラフィックスのコンテキスト
     */
	public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(slotBoardImage,0, 0, WIDTH, HEIGHT, this);
		for(int i=0;i<3;i++){
			g.drawImage(slotMarkImages[i],62*i+30,62, this);
		}
        g.drawImage(shadowImage, 26, 70,this);
	}
	
    /**
     * 3つの整数に対応したスロットのマークの画像イメージを保存する。
     * @param インストールしたいスロットのマーク
     */
	public void installSlotMarkImage(int[] slotMark){
        String file;
        String dir=System.getProperty("user.dir")+File.separator;
		for(int i=0; i<3; i++) {
            file = "memorySlot/Image/slot/mark"+slotMark[i]+".png";
            slotMarkImages[i] = Toolkit.getDefaultToolkit().getImage(dir+file);
        }
	}
	
    /**
     * スロットボードとシャドウの画像イメージを保存する。
     */
	private void installSlotBoardImage(){
        String dir=System.getProperty("user.dir")+File.separator;
		String file1 = "memorySlot/Image/slot/slot.png";
		String file2 = "memorySlot/Image/slot/shadow.png";
		slotBoardImage = Toolkit.getDefaultToolkit().getImage(dir+file1);
		shadowImage = Toolkit.getDefaultToolkit().getImage(dir+file2);
	}
}
package memorySlot.slot;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

public class ProbabilityView extends JPanel{
    public static final int WIDTH = 200;
    public static final int HEIGHT = 300;
	SlotModel slotModel;
	String[] probability;
	private Image[] slotMarkImages;
	
    /**
     * スロットのマークの画像をすべてインストールする。
     * @param slotModel SlotModelクラス
     */
	public ProbabilityView(SlotModel slotModel) {
        this.slotModel = slotModel;
        probability = new String[7];
        slotMarkImages = new Image[7];
        setOpaque(false);
		setSize(WIDTH, HEIGHT);
		installSlotMarkImage();
	}
	
    /**
     * 左中央に設置する確率分布を描画する。
     * @param g グラフィックスのコンテキスト
     */
	public void paint(Graphics g){
		probability=slotModel.getProbabilityString();
		super.paint(g);
        g.setFont(new Font("Serif",Font.BOLD, 14));
        g.setColor(Color.WHITE);
		for(int i=0;i<7;i++){
			for(int j=0;j<3;j++){
				g.drawImage(slotMarkImages[i],42*j,38*i, 45, 40, this);
			}
			g.drawString(probability[i]+"%", 130, 38*i+28);
		}
	}
	
    /**
     * 左中央に設置する確率分布を表示するための画像イメージを保存する。
     */
	private void installSlotMarkImage(){
        String file;
        String dir=System.getProperty("user.dir")+File.separator;
		for(int iImg=0;iImg<7;iImg++){
			file = "memorySlot/Image/slot/mark"+iImg+".png";
			slotMarkImages[iImg] = Toolkit.getDefaultToolkit().getImage(dir+file);
		}
	}
}
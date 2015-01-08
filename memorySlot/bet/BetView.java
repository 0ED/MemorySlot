package memorySlot.bet;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class BetView extends JPanel{
    
    public static final int WIDTH=250;
    public static final int HEIGHT=150;
    public static final String chips[] = {"1","5","10","25"};
     private BetModel betModel;
    private JLabel[] chipLabel;
    private String betText;
    private JButton clearButton;
    
    
    /**
     * 「Betするためのチップラベル（$1、$5、$10、$25 ：チップは画像で表示）」と「クリアボタン」を表示する。
     * @param betModel BetModelクラス
     */
    public BetView(BetModel betModel){
        super();
        this.betModel=betModel;
        setOpaque(false); //不透明に
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        
        //チップボタン
        String dir,file;
        chipLabel=new JLabel[chips.length];
        for(int ci=0; ci<chips.length; ci++) {
            dir=System.getProperty("user.dir")+File.separator; //jar用
            file = "memorySlot/Image/bet/"+chips[ci]+".png";
            chipLabel[ci]= new JLabel(new ImageIcon(dir+file));
            chipLabel[ci].setName(chips[ci]);
            chipLabel[ci].setBounds(ci*50, 80-ci*20, 40, 40);
            add(chipLabel[ci]);
        }
        
        //クリアボタン
        clearButton= new JButton("CLEAR");
        clearButton.setBounds(150, 70, 70, 30);
        clearButton.setFocusable(false);
        add(clearButton);
    }
    
    
    /**
     * ベットした金額を描画する。
     * @param g グラフィックスのコンテキスト
     */
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(new Font("Serif",Font.BOLD, 31-4));
        g.setColor(Color.WHITE);
        g.drawString("Bet:"+String.valueOf(betModel.getChip()), 110, 120); //位置
    }
    
    
    /**
     *「Betするためのチップラベル」にMouseListenerを設定する。コントローラ呼び出す
     * @param mouseListener マウス受け取りマシン
     */
    public void addToLabelMouseListener(MouseListener mouseListener){
        for(int ci=0; ci<chipLabel.length; ci++) {
            chipLabel[ci].addMouseListener(mouseListener);
        }
    }
    
    
    /**
     * 「クリアボタン」にActionListenerを設定する。コントローラから呼び出す
     * @param actionListener 動作受け取りマシン
     */
    public void addToButtonActionListener(ActionListener actionListener){
        clearButton.addActionListener(actionListener);
    }
    
}
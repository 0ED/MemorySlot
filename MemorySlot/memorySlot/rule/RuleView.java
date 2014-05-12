package memorySlot.rule;
import memorySlot.board.BoardView;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class RuleView extends JFrame {
    
    /**
     * ルール説明のためのフレーム。
     * メニューバーのMemorySlotのルール説明をクリックするか、スタート画面のルール説明をクリックすると呼び出される。
     */
    public RuleView(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(BoardView.WIDTH,0,490, 1000); //JFrameの大きさ
        //ここでフレームの位置を指定する。
        setBackground(Color.WHITE);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setPreferredSize(new Dimension(450,900));	//x(450)は横幅(ウインドウに収まるように)
        //yは縦幅は900に調整
        String dir=System.getProperty("user.dir")+File.separator;
        
        ImageIcon icon1 = new ImageIcon(dir+"memorySlot/Image/trump/dia/dia1.png");
        ImageIcon icon2 = new ImageIcon(dir+"memorySlot/Image/trump/heart/heart1.png");
        ImageIcon icon3 = new ImageIcon(dir+"memorySlot/Image/slot/mark0.png");
        ImageIcon icon4 = new ImageIcon(dir+"memorySlot/Image/slot/mark1.png");
        ImageIcon icon5 = new ImageIcon(dir+"memorySlot/Image/slot/mark2.png");
        ImageIcon icon6 = new ImageIcon(dir+"memorySlot/Image/slot/mark3.png");
        ImageIcon icon7 = new ImageIcon(dir+"memorySlot/Image/slot/mark4.png");
        ImageIcon icon8 = new ImageIcon(dir+"memorySlot/Image/slot/mark5.png");
        ImageIcon icon9 = new ImageIcon(dir+"memorySlot/Image/slot/mark6.png");
        ImageIcon icon10 = new ImageIcon(dir+"memorySlot/Image/trump/clab/clab13.png");
        
        
        JLabel label1 = new JLabel("Rule", JLabel.LEFT);
        label1.setBounds(10,30,500,30);
        label1.setFont(new Font("BlairMdITC TT", Font.BOLD, 18));
        label1.setForeground(Color.pink);
        
        JLabel label2 = new JLabel("  Memory Slotは神経衰弱にスロット機能を加えたゲームです。", JLabel.LEFT);
        label2.setBounds(10,60,500,30);
        JLabel label3 = new JLabel("  BASIC RULE",JLabel.LEFT);
        label3.setBounds(10,80,500,30);
        label3.setFont(new Font("Arial Black", Font.BOLD, 12));
        label3.setForeground(Color.red);
        JLabel label4 = new JLabel("  裏向きにばらまかれた52枚のカードの中から2枚のカードを選択する。");
        label4.setBounds(10,100,500,30);
        JLabel label5 = new JLabel("  その2枚のカードが同じ数字の場合、その2枚のカードを獲得する。", JLabel.LEFT);
        label5.setBounds(10,120,500,30);
        JLabel label6 = new JLabel("  獲得したときに、画面中央のスロットが回転し、スロットの絵柄がそろえば",JLabel.LEFT);
        label6.setBounds(10,140,500,30);
        JLabel label7 = new JLabel("  絵柄に応じてイベントが発生する", JLabel.LEFT);
        label7.setBounds(10,160,500,30);
        JLabel label8 = new JLabel("  SCORE ", JLabel.LEFT);
        label8.setBounds(10,180,500,30);
        label8.setFont(new Font("Arial Black",Font.BOLD,12));
        label8.setForeground(Color.blue);
        JLabel label10 = new JLabel("  最初の手持ちスコアは1000点です。 ", JLabel.LEFT);
        label10.setBounds(10,200,500,30);
        JLabel label11 = new JLabel("  手持ちスコアを任意の数字分betします。 ", JLabel.LEFT);
        label11.setBounds(10,220,500,30);
        JLabel label12 = new JLabel("  カードを2枚選択したときに、数字がそろえばbetの２倍スコアがUP ", JLabel.LEFT);
        label12.setBounds(10,240,500,30);
        JLabel label13 = new JLabel("  ベッドの上限は$75です。", JLabel.LEFT);
        label13.setBounds(10,260,500,30);
        JLabel label14 = new JLabel("  SLOT ", JLabel.LEFT);
        label14.setBounds(10,280,500,30);
		label14.setFont(new Font("Arial Black",Font.BOLD,12));
		label14.setForeground(Color.green);
        JLabel label15 = new JLabel("   選択した2枚のカードが同じ数字でない場合でも、スロットの当たる確率が", JLabel.LEFT);
        label15.setBounds(10,300,500,30);
        JLabel label16 = new JLabel("   上がる特殊条件がある。", JLabel.LEFT);
        label16.setBounds(10,320,500,30);
        JLabel label17 = new JLabel("   条件１）2枚のカードのマークが同じ場合、当たる確率UP(弱)", JLabel.LEFT);
        label17.setBounds(10,340,500,30);
        JLabel label18 = new JLabel(icon1, JLabel.CENTER);
        label18.setBounds(-55,370,500,70);
        JLabel label36 = new JLabel(icon2, JLabel.CENTER);
        label36.setBounds(-5,370,500,70);
        JLabel label19 = new JLabel("   条件２）2枚のカードでブラックジャックが出た場合、当たる確率UP(強)", JLabel.LEFT);
        label19.setBounds(10,440,500,30);
        JLabel label20 = new JLabel(icon2, JLabel.CENTER);
        label20.setBounds(-55,470,500,70);
        JLabel label37 = new JLabel(icon10,JLabel.CENTER);
        label37.setBounds(-5,470,500,70);
        JLabel label21 = new JLabel("当たり図柄は全部で７種類です。", JLabel.LEFT);
        label21.setBounds(10,550,500,30);
        JLabel label22 = new JLabel(icon3, JLabel.LEFT);
        label22.setBounds(10,580,500,50);
        JLabel label23 = new JLabel("スコアが2倍になります。",JLabel.LEFT);
        label23.setBounds(80,590,500,30);
        JLabel label24 = new JLabel(icon4,JLabel.LEFT);
        label24.setBounds(10,620,500,50);
        JLabel label25 = new JLabel("スロットがもう一度行われ、スコアが1500上昇します。",JLabel.LEFT);
        label25.setBounds(80,630,500,30);
        JLabel label26 = new JLabel(icon5,JLabel.LEFT);
        label26.setBounds(10,660,500,50);
        JLabel label27 = new JLabel("スコアが1500上昇します。",JLabel.LEFT);
        label27.setBounds(80,670,500,30);
        JLabel label28 = new JLabel(icon6,JLabel.LEFT);
        label28.setBounds(10,700,500,50);
        JLabel label29 = new JLabel("スコアが800上昇します。",JLabel.LEFT);
        label29.setBounds(80,710,500,30);
        JLabel label30 = new JLabel(icon7,JLabel.LEFT);
        label30.setBounds(10,740,500,50);
        JLabel label31 = new JLabel("スコア500上昇します。",JLabel.LEFT);
        label31.setBounds(80,750,500,30);
        JLabel label32 = new JLabel(icon8,JLabel.LEFT);
        label32.setBounds(10,790,500,50);
        JLabel label33 = new JLabel("スコア500上昇します。",JLabel.LEFT);
        label33.setBounds(80,800,500,30);
        JLabel label34 = new JLabel(icon9,JLabel.LEFT);
        label34.setBounds(10,840,500,40);
        JLabel label35 = new JLabel("スロットがもう一度行われ、スコア100上昇します。",JLabel.LEFT);
        label35.setBounds(80,840,500,30);
        
        
        p.add(label1);
        p.add(label2);
        p.add(label3);
        p.add(label4);
        p.add(label5);
        p.add(label6);
        p.add(label7);
        p.add(label8);
        p.add(label10);
        p.add(label11);
        p.add(label12);
        p.add(label13);
        p.add(label14);
        p.add(label15);
        p.add(label16);
        p.add(label17);
        p.add(label18);
        p.add(label19);
        p.add(label20);
        p.add(label21);
        p.add(label22);
        p.add(label23);
        p.add(label24);
        p.add(label25);
        p.add(label26);
        p.add(label27);
        p.add(label28);
        p.add(label29);
        p.add(label30);
        p.add(label31);
        p.add(label32);
        p.add(label33);
        p.add(label34);
        p.add(label35);    
       	p.add(label36);
        p.add(label37);
        
        JScrollPane scrollpane = new JScrollPane(p);
        Container contentPane = getContentPane();
        contentPane.add(scrollpane, BorderLayout.CENTER);
        setVisible(true); 
    }
}




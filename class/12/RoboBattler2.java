import javax.swing.*;
import java.awt.event.*;

class RoboBattler2 {
    private FieldPanel fieldPanel;
    private JLabel[] scoreLabels = new JLabel[2]; //文字列やイメージを表示できるオブジェクトを2つ作成
    private JButton manualStartButton;
    
    RoboBattler2() {
        JFrame frame = new JFrame();
        frame.setTitle("Robo Battler"); //フレームの名前
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //×ボタンを押したら、プログラム終了
        frame.setSize(640, 480);
        
        //JPanelクラスを使っている
        fieldPanel = new FieldPanel();
        fieldPanel.setBounds(140, 60, 361, 361); //361*361のパネルを(x,y)=(140,60)から描画
        frame.getContentPane().setLayout(null); //getContentPaneは薄いパネル
        frame.getContentPane().add(fieldPanel);
        
        frame.setVisible(true); //描画!
        
    }
    public static void main(String[] args) {
        RoboBattler2 app = new RoboBattler2();
    }
}
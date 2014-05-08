import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Anime {
    private FieldPanel fieldPanel;
    private JLabel[] scoreLabels = new JLabel[2]; //文字列やイメージを表示できるオブジェクトを2つ作成
    private JButton startButton;
    private JButton stopButton;
    private BallMove ballmove;
    
    Anime() {
        //フレーム
        JFrame frame = new JFrame();
        frame.setTitle("Robo Battler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //×ボタンを押したら、プログラム終了
        frame.setSize(640, 480);
        
        
        //パネル
        fieldPanel = new FieldPanel();
        fieldPanel.setBounds(140, 60, 361, 361); //361*361のパネルを(x,y)=(140,60)から描画
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(fieldPanel);
        
        
        //スタートボタン
        startButton = new JButton("start");
        startButton.setBounds(500,420,120,30);
        startButton.setFocusable(false); //周り色を解除
        startButton.setEnabled(true); //最初スタートボタンは有効
        startButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { //ボタンを押したとき
                System.out.println("start");
                ballmove.paintComponent(Graphics g);
                stopButton.setEnabled(true); //ストップボタンを有効化する
                startButton.setEnabled(false); //スタートボタンを無効化する
                
            }
	    });
        frame.getContentPane().add(startButton); //ボタンをフレームに加える
        
        //ストップボタン
        stopButton = new JButton("stop");
        stopButton.setBounds(500,390,120,30);
        stopButton.setFocusable(false); //周り色を解除
        stopButton.setEnabled(false); //最初ストップボタンは無効
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ストップボタンを押したとき
                System.out.println("stop");
                startButton.setEnabled(true); //スタートボタンを有効化する
                stopButton.setEnabled(false); //ストップボタンを無効化する
            }
	    });
        frame.getContentPane().add(stopButton); //ボタンをフレームに加える
        
        
        
//        //キー
//        frame.addKeyListener(new KeyAdapter() { 
//            public void keyTyped(KeyEvent e) { //キーを押したとき
//                System.out.println("key typed: " + e.getKeyChar());
//            }
//        });
        
        
        frame.setVisible(true); //描画!
    }
    public static void main(String[] args) {
        Anime app = new Anime();
    }
}
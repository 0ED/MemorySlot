package memorySlot.board;
import memorySlot.MemorySlot;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class StartBoardView extends BoardView {

    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 50;
    private static final int VERTICAL_GAP = 20;
    private JButton startButton;
    private JButton ruleButton;
    
    /**
     * スタートボタンとルール説明ボタンを配置し、位置をセットする。
     */
    public StartBoardView() {
        super();
        setLayout(null);
        setOpaque(true); //背景を不透明に
        setBackground(new Color(0,90,0));
        startButton = new JButton("スタート");
        ruleButton = new JButton("ルール説明");

        JPanel buttonArea = new JPanel(); /* スタートとルール説明のボタンを含めたエリア*/
        buttonArea.setOpaque(false);
        buttonArea.setLayout(new GridLayout(2,1,0,VERTICAL_GAP) );
        buttonArea.setBounds(   (BoardView.WIDTH/2)-(BUTTON_WIDTH/2),
                                (BoardView.HEIGHT/2)-(BUTTON_HEIGHT+VERTICAL_GAP),
                                BUTTON_WIDTH,
                                BUTTON_HEIGHT*2+VERTICAL_GAP
                              );
        buttonArea.add(startButton);
        buttonArea.add(ruleButton);
        add(buttonArea);
    }

    /**
     * スタートボタンとルール説明ボタンにActionListenerを取り付ける。コントローラから呼び出す。
     * @param actionListener 動作受け取りマシン
     */
    void addToButtonActionListener(ActionListener actionListener) { 
        startButton.addActionListener(actionListener);
        ruleButton.addActionListener(actionListener);
    }
}
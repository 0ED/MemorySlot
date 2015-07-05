package memorySlot.board;
import memorySlot.MemorySlot;
import memorySlot.rule.RuleView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class StartBoardControl implements ActionListener {
    
    private MemorySlot memorySlot;
    private StartBoardView startBoardView;
    
    /**
     * Viewからスタートボタンとルール説明ボタンにActionListenerを設置する。
     * @param memorySlot MemorySlotクラス
     * @param view StartBoardViewクラス
     */
    public StartBoardControl(MemorySlot memorySlot, StartBoardView view) {
        this.memorySlot = memorySlot;
        this.startBoardView = view;
        startBoardView.addToButtonActionListener(this);
    }
    
    /**
     * スタートまたはルール説明のボタンを押したとき、受け取る。
     * @param ev クリック情報
     */
    public void actionPerformed(ActionEvent ev){
        JButton botton = (JButton) ev.getSource();
        if(botton.getText().equals("スタート")) { //ここでボードの切り替えを行う
            memorySlot.startGame();
        }
        if(botton.getText().equals("ルール説明")) new RuleView();
    }
    
}

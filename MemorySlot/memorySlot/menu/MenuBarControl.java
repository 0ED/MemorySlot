package memorySlot.menu;
import memorySlot.MemorySlot;
import memorySlot.rule.RuleView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JFrame;

public class MenuBarControl implements ActionListener {
    
    private MemorySlot memorySlot;
    private MenuBarView menuBarView;
    
    /**
     * Viewから、ActionListenerを設定する。
     * @param memorySlot MemorySlotクラス
     * @param view MenuBarViewクラス
     */
    public MenuBarControl(MemorySlot memorySlot, MenuBarView view) {
        this.memorySlot = memorySlot;
        this.menuBarView = view;
        menuBarView.addToItemActionListener(this);
    }
    
    /**
     * メニューアイテムを押したときに呼び出され、それぞれクリックしたとき、反応が起きる。
     * ルール説明を押すと、ルールを説明するフレームが出てくる。
     * リセットを押すとスタート画面に戻り、終了を押すとゲームが終わり、ウィンドウが閉じる。
     * @param ev クリック情報
     */
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) ev.getSource();
            if(item.getText().equals("ルール説明")) new RuleView();
            if(item.getText().equals("リセット")) memorySlot.resetGame();
            if(item.getText().equals("終了")) System.exit(0);
        }
    }
}
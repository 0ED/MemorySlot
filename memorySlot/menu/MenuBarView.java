package memorySlot.menu;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.Color; 

public class MenuBarView extends JMenuBar {
    
    private JMenu[] menu;
    private JMenuItem[][] item;
    
    /**
     * メニューバー、メニュー、アイテムの作成を行い、表示する。
     */
    public MenuBarView() {
        makeMenuAndItem();
        setBackColor();
        setShortKeyToItem();
        insertItemToMenuBar();
    }
    
    /**
     * メニューとアイテムのオブジェクトを作成する
     */
    private void makeMenuAndItem() {
        menu = new JMenu[1];
        item = new JMenuItem[1][3]; //メニューが増えたときのため配列にしている
        
        menu[0] = new JMenu("MemorySlot");
        item[0][0] = new JMenuItem("ルール説明");
        item[0][1] = new JMenuItem("リセット");
        item[0][2] = new JMenuItem("終了");
    }
    
    /**
     * メニュー、メニューバー、アイテムの色を設定する
     */
    private void setBackColor() {
        this.setBackground(new Color(200,255,255));
        for(int m=0; m<menu.length; m++) {
            menu[m].setBackground(new Color(200,255,255));
            
            for(int i=0; i<item[m].length; i++) {
                /* メニューは各メニュー項目の数が色々なのでNULLになるときがある
                 もしメニューが増えたときの為に、こうしておく*/
                if(item[m][i] != null) {
                    item[m][i].setBackground(new Color(240,255,255));
                }
            }
        }
    }

    /**
     * ショートカットを設定する
     */
    private void setShortKeyToItem() {
        item[0][0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK)); //Control+i
        item[0][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK)); //Control+r
        item[0][2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK)); //Control+q
    }
    
    /**
     * アイテムとメニューをメニューバーに挿入する
     */
    private void insertItemToMenuBar() {
        for(int m=0; m<menu.length; m++) {
            for(int i=0; i<item[m].length; i++) {
                if(item[m][i] != null) menu[m].add(item[m][i]); //メニュー項目をメニューに貼付け
            }
            this.add(menu[m]); //メニューをメニューバーに貼付け
        }
    }
    
    /**
     * メニューのアイテムにそれぞれActionListenerを取り付ける。コントローラからアクセスできるようにする。
     * @param actionListener 動作受け取りマシン
     */
    void addToItemActionListener(ActionListener actionListener) {
        item[0][0].addActionListener(actionListener);
        item[0][1].addActionListener(actionListener);
        item[0][2].addActionListener(actionListener);
    }
    
    
}
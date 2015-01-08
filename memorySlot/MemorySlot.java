package memorySlot;

import memorySlot.board.BoardView;
import memorySlot.board.GameBoardView;
import memorySlot.board.StartBoardView;
import memorySlot.board.StartBoardControl;
import memorySlot.menu.MenuBarView;
import memorySlot.menu.MenuBarControl;
import javax.swing.JFrame;
import java.awt.event.ActionListener;

public class MemorySlot extends JFrame {
    
    /*
     * ここでのBoardView.HEIGHTとは、フレーム全体の内メニューバーと上フレームの分の44pxを抜いた640pxです。
     * ですのでフレーム全体のサイズは、「BoardView.WIDTH BoardView.HEIGHT+44」となっております。
     * ちなみに別サブパッケージのクラスでも使うため、publicです。
     */
    private StartBoardView startBoardView;
    private GameBoardView gameBoardView;
    
    /**
     * MemorySlotプログラムが作動する
     */
    public MemorySlot() {
        getContentPane().setLayout(null); 
        setTitle("MemorySlot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BoardView.WIDTH, BoardView.HEIGHT+44); //メニューバーと上フレームの分の44px
        getContentPane().setLayout(null);
        setResizable(false); //サイズ調整できないように
        
        //メニューバー
        MenuBarView menuBarView = new MenuBarView();
        MenuBarControl menuBarControl = new MenuBarControl(this,menuBarView);
        
        //スタート画面
        startBoardView = new StartBoardView();
        StartBoardControl startBoardControl = new StartBoardControl(this,startBoardView);
        
        getContentPane().add(startBoardView);
        setJMenuBar(menuBarView);
        setVisible(true);
    }
    
    /**
     * ゲーム画面に切り替える
     */
    public void startGame() {
        getContentPane().remove(startBoardView);
        gameBoardView = new GameBoardView(); //ゲームの基盤が作られる
        getContentPane().add(gameBoardView);
        gameBoardView.repaint();
    }
    
    /**
     * スタート画面に切り替える
     */
    public void resetGame() {
        if(gameBoardView != null) { //ゲームボードからクリックされたなら
            getContentPane().remove(gameBoardView);
            getContentPane().add(startBoardView);
            gameBoardView.repaint();
            startBoardView.repaint();
        }
    }
    
    
    
    /*
     * プログラムの実行部分
     */
    public static void main(String[] args) {
        MemorySlot app = new MemorySlot();
    }
}
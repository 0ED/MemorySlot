package memorySlot.bet;
import memorySlot.Mediator;
import memorySlot.Colleague;
import java.lang.Integer;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

public class BetControl extends MouseAdapter implements ActionListener, Colleague {
    
    private Mediator mediator;
    private BetModel betModel;
    private BetView betView;
    
    
    /**
     * 「Betのするためのチップラベル」と「クリアボタン」にそれぞれMouseListenerとActionListenerを加える。
     */
    public BetControl(BetModel model, BetView view){
        this.betModel=model;
        this.betView=view;
        this.betView.addToLabelMouseListener(this);
        this.betView.addToButtonActionListener(this);
    }
    
    
    /**
     * 各Chipのボタン（$1、$5、$10、$25）のどれかを押すと呼び出され、そのボタンの金額に応じて、ModelからBetを加算する。
     * @param ev クリック情報
     */
    public void mouseClicked(MouseEvent ev){
        JLabel label = (JLabel) ev.getSource();
        int clickedChip;
        /* 
         * クリックされたラベルの名前（1,5,10,25）の金額分、ベットを追加する。
         * また、その分スコアを減らす
         */
        for(int ci=0; ci<BetView.chips.length; ci++) {
            if(label.getName().equals(BetView.chips[ci])) {

                clickedChip = Integer.parseInt(BetView.chips[ci]);
                if( this.betModel.getChip()+clickedChip <= 75){
                    betModel.addChip(clickedChip);
                    betView.repaint();
                    mediator.removeScore(clickedChip);
                }
            }
        }
    }
    
    
    /**
     * クリアのボタンを押すと呼び出され、ModelからBetを0にする。そして、Viewからrepaintで再描画する。
     * @param ev クリック情報
     */
    public void actionPerformed(ActionEvent ev){
        mediator.restoreScore();
        betModel.clearChip();
        betView.repaint();
    }
    
    
    /**
     * Mediator実装クラスからTrumpAreaを経由して呼び出し、Mediatorをセットする。
     * @param mediator メディエイター登録
     */
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
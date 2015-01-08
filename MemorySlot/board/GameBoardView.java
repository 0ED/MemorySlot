package memorySlot.board;
import memorySlot.MemorySlot;
import memorySlot.Mediator;
import memorySlot.trump.TrumpModel;
import memorySlot.trump.TrumpArea;
import memorySlot.bet.*;
import memorySlot.score.*;
import memorySlot.slot.*;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JLayeredPane;

public class GameBoardView extends JLayeredPane implements Mediator {
    
    // トランプ
    private TrumpArea trumpArea;
    
    // スロット&確率分布
    private SlotModel slotModel;
    private SlotView slotView;
    private SlotControl slotControl;
    private ProbabilityView probabilityView;
    
    // ベット
    private BetModel betModel;
    private BetView betView;
    private BetControl betControl;
    
    // スコア
    private ScoreModel scoreModel;
    private ScoreView scoreView;
    
    // トランプの組み合わせを記憶する。
    private HashMap<TrumpModel,TrumpModel> trumpCombinationMap;
    
    
    /**
     * ベット、スコア、スロット、確率分布、トランプをここに置き、スーパークラスのレイヤー機能（JLayeredPane）
     * を使って配置する。（このクラスはMediatorの実装クラス）
     */
    public GameBoardView() {
        super();
        setOpaque(true); //最背面なので透明でない
        setLayout(null);
        setBounds(0, 0, BoardView.WIDTH, BoardView.HEIGHT);
        setBackground(new Color(0,90,0));
        
        //トランプの組み合わせを記憶する。
        trumpCombinationMap = new HashMap<TrumpModel,TrumpModel>();
        
        //メインの飾り
        BoardView boardView = new BoardView();
        
        //トランプ
        TrumpArea trumpArea = new TrumpArea();
        
        //スロット
        slotModel = new SlotModel();
        slotView = new SlotView(slotModel);
        probabilityView = new ProbabilityView(slotModel);
        slotControl = new SlotControl(slotModel,slotView,probabilityView);
        
        //ベット
        betModel = new BetModel();
        betView = new BetView(betModel);
        betControl = new BetControl(betModel, betView);
        
        //スコア
        scoreModel = new ScoreModel(1000); //初期スコア
		scoreView = new ScoreView(scoreModel);
        
        
        // 配置
        trumpArea.setLocation(  (BoardView.WIDTH/2)-(TrumpArea.WIDTH/2)+5,
                                (BoardView.HEIGHT/2)-(TrumpArea.HEIGHT/2)-50);
        slotView.setLocation(   (BoardView.WIDTH/2)-(SlotView.WIDTH/2),
                                (BoardView.HEIGHT/2)-(SlotView.HEIGHT/2)-50-5); //-5は微調節
        probabilityView.setLocation( 30, (BoardView.HEIGHT/2)-(ProbabilityView.HEIGHT/2)+20  );
        betView.setLocation(    BoardView.WIDTH - BetView.WIDTH,
                                BoardView.HEIGHT - BetView.HEIGHT );
        scoreView.setLocation(      BoardView.WIDTH/2 - ScoreView.WIDTH/2,
                                    BoardView.HEIGHT/2 - ScoreView.HEIGHT/2 + 240);
        
        // レイヤー設定
        setLayer(betView,3);
        setLayer(boardView,2); //前面はタイトルとトランプの「飾り」
        setLayer(slotView,1);
        setLayer(probabilityView,1);
        setLayer(trumpArea,1);
        setLayer(scoreView,1);
        setLayer(this,0); //最背面は緑色のみ
        
        add(betView);
        add(scoreView);
        add(boardView);
        add(slotView);
        add(probabilityView);
        add(trumpArea);
        
        // Mediatorの設定
        betControl.setMediator(this);
        slotControl.setMediator(this);
        trumpArea.setMediator(this);
    }
    
    
    /**
     * スートが2枚そろったとき呼び出され、ベットをクリアし、再描画する。
     */ 
    public void clearBetChip() {
        betModel.clearChip();
        betView.repaint();
    }
    
    /**
     * ベットをクリアするときに呼び出され、ベットをした金額を取り戻す。
     */
    public void restoreScore() {
        scoreModel.addScore(betModel.getChip());
        scoreView.repaint();
    }
    

    /**
     * ベットが追加されると呼び出され、スコアをベットしたチップの分だけ減らし、再描画する。
     */
    public void removeScore(int clickedChip) {
        scoreModel.removeScore(clickedChip);
        scoreView.repaint();
    }
    
    
    /**
     * トランプが2枚オープンすると呼び出される。トランプの組み合わせによって、それぞれのイベントがおこる。
     * スートが同じ（このことをフラッシュという）なら、以前にそのトランプの組み合わせがあったかcheckNewTrumpCombinationメソッドで確認し、なければスロットの確率をあげる。
     * トランプのランクが1または10,11,12,13（このことをブラックジャックという）なら、以前にそのトランプの組み合わせがあったか確認し、なければスロットの確率をあげる。
     * ランクが同じ（このことをスロットという）なら、スロットを開始し、スロットの確率をクリアにし、スコアをベットの2倍分追加する。
     * また、イベントが起こると、確率が変動するので、確率分布を再描画する。
     * @param trump0 オープンされた1枚目のトランプデータ
     * @param trump1 オープンされた2枚目のトランプデータ
     */
    public void happenCardEvent(TrumpModel trump0, TrumpModel trump1, String trumpCombination) {
        

        /* 「フラッシュ」は確率を1段階up */
        if(trumpCombination.equals("フラッシュ")) {
            if(checkNewTrumpCombination(trump0, trump1)) {
                slotModel.addProbabilityLevel1();
            }
        }
        
        /* 「ブラックジャック」は確率を2段階up */
        if(trumpCombination.equals("ブラックジャック")) {
            if(checkNewTrumpCombination(trump0, trump1)) {
                slotModel.addProbabilityLevel2();
            }
            
        }
        
        /* 「フラッシュ&ブラックジャック」は確率を3段階up */
        if(trumpCombination.equals("フラッシュ&ブラックジャック")) {
            if(checkNewTrumpCombination(trump0, trump1)) {
                slotModel.addProbabilityLevel1();
                slotModel.addProbabilityLevel2();
            }
        }
        
        /*
         * 「スロット」はスロットをスタートさせ、スロットが終われば確率レベルを0にする
         * また賭け金の2倍、スコアが手に入る
         */
        if(trumpCombination.equals("スロット")) {
            slotControl.startSlot();
            slotModel.clearProbabilityLevel();
            scoreModel.addScore(2*betModel.getChip());
            System.out.println("スコア追加"+(2*betModel.getChip()) );
            scoreView.repaint();
        }
        
        /* 確率を更新 */
        probabilityView.repaint();

    }
    
    
    
    /**
     * スロットがそろったときのイベント
     * @param slotMark スロットのマーク
     */
    public synchronized void hitSlot(int slotMark) { //synchronizedによって、1つのスレッドしか受け付けない

        switch(slotMark) {
            case 0:
                scoreModel.addScore(scoreModel.getScore());
                scoreView.repaint();
                break;
            case 1:
                scoreModel.addScore(1500);
                scoreView.repaint();
                this.sleep(500);
                slotControl.startSlot();
                break;
            case 2:
                scoreModel.addScore(1500);
                scoreView.repaint();
                break;
            case 3:
                scoreModel.addScore(800);
                scoreView.repaint();
                //光らせる
                break;
            case 4:
                scoreModel.addScore(500);
                scoreView.repaint();
                break;
            case 5:
                scoreModel.addScore(500);
                scoreView.repaint();
                break;
            case 6:
                scoreModel.addScore(100);
                scoreView.repaint();
                this.sleep(500);
                slotControl.startSlot();
                break;
            default:
                System.out.println("ここが実行されればバグ。Class: GameBoardView");
                break;
        }
    }
    
    /**
     * スレッドを使って、指定した時間スリープする。
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException anException) {
            System.err.println(anException);
        }
    }
    
    
    /**
     * 新しいトランプの組み合わせがあったかをチェックする。
     * @param trump0 オープンされた1枚目のトランプデータ
     * @param trump1 オープンされた2枚目のトランプデータ
     * @return 初めてオープンされた組み合わせかどうか
     */
    private boolean checkNewTrumpCombination(TrumpModel trump0, TrumpModel trump1) {
        if( trump0 == trumpCombinationMap.get(trump1) || trump1 == trumpCombinationMap.get(trump0) ) {
            return false;
        }
        else {
            trumpCombinationMap.put(trump0,trump1);
            return true;
        }
    }
}
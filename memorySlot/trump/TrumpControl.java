package memorySlot.trump;
import memorySlot.Mediator;
import memorySlot.Colleague;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrumpControl extends MouseAdapter implements Colleague {
    
    /* 2枚分の情報を一時的に格納する。*/
    private static ArrayList<TrumpModel> trumpModelList;
    private static ArrayList<TrumpView> trumpViewList;
    private static String trumpCombination;
    private static Mediator mediator;
    private TrumpArea trumpArea;
    private TrumpModel trumpModel;
    private TrumpView trumpView;
    
    
    public TrumpControl(TrumpModel model, TrumpView view, TrumpArea area) {
        trumpModelList = new ArrayList<TrumpModel>();
        trumpViewList = new ArrayList<TrumpView>();
        this.trumpModel = model;
        this.trumpView = view;
        this.trumpArea = area;
        trumpView.addToTrumpMouseListener(this);
    }
    
    
    /**
     * トランプをクリックすることでトランプをめくる。
     * また、トランプの組み合わせによってイベントがおこる
     * @param ev クリック情報
     */
    public void mouseClicked(MouseEvent ev) {
        
        if(trumpModel.getOpen() == true) {
            if(trumpModelList.size() == 2) {
                removeOrCloseTrump();
            }
        }
        else {
            trumpModelList.add(trumpModel); 
            trumpViewList.add(trumpView);
            trumpModel.setOpen(true);
            trumpView.repaint();
        
            /*
             * 3枚めくっていれば、先頭2枚のトランプを閉じるか、削除する
             * 2枚めくっていれば、Mediatorに2枚の情報を知らせ、イベントを起こしてもらう。
             */
            if(trumpModelList.size() >= 3) {
                removeOrCloseTrump();
            }
            else if(trumpModelList.size() == 2){
                searchTrumpCombination(trumpModelList.get(0) ,trumpModelList.get(1));
                mediator.happenCardEvent(trumpModelList.get(0),trumpModelList.get(1),trumpCombination);
                mediator.clearBetChip(); //ベットを0にする
            }
        }
    }
    
    /**
     * 2枚のランクがそろった（スロット状態）なら、2枚のトランプを削除し、そうでなければ、2枚のトランプを閉じる。
     * また、一時的に保存していたトランプのリストの内、2枚分削除する。
     */
    private void removeOrCloseTrump() {
        for(int i=0; i<2; i++) {
            if(trumpCombination.equals("スロット")) {
                trumpArea.remove(trumpViewList.get(0));
                
            }
            else {
                trumpModelList.get(0).setOpen(false);
            }
            trumpArea.repaint();
            trumpModelList.remove(0);
            trumpViewList.remove(0);
        }
    }
    
    /**
     * 2枚のトランプがどのような組み合わせかを捜し出し、組み合わせの名前をつける。
     * "スロット"=ランクが同じ、"フラッシュ"=スートが同じ。
     * @param trump0 1枚目のトランプ
     * @param trump1 2枚目のトランプ
     */
    private void searchTrumpCombination(TrumpModel trump0, TrumpModel trump1) {
        trumpCombination = "";
        if(trump0.getSuit() == trump1.getSuit() ) {
            trumpCombination = "フラッシュ";
        }
        if( (trump0.getRank()==1) &&
           (10 <= trump1.getRank() && trump1.getRank() <=13)
           || (trump1.getRank()==1) &&
           (10 <= trump0.getRank() && trump0.getRank() <=13)) {
            if(trump0.getSuit() == trump1.getSuit() ) {
                trumpCombination = "フラッシュ&ブラックジャック";
            } else {
                trumpCombination = "ブラックジャック";
            }
        }
        if(trump0.getRank() == trump1.getRank()) {
            trumpCombination = "スロット";
        }
    }
    
    /**
     * Mediator実装クラスからTrumpAreaを経由して呼び出し、Mediatorをセットするs
     * @param mediator メディエイター登録
     */
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    
    
    /* !!!テスト用!!!（後で消す）
     private void show() {
     System.out.println(trumpModelList.get(0).getSuit()+trumpModelList.get(0).getRank() );
     System.out.println(trumpModelList.get(1).getSuit()+trumpModelList.get(1).getRank() );
     System.out.println(trumpCombination);
     }*/
    
}
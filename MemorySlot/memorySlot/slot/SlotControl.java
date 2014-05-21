package memorySlot.slot;
import memorySlot.Mediator;
import memorySlot.Colleague;
import java.awt.*;
import javax.swing.*;

public class SlotControl implements Colleague {
    private Mediator mediator;
	private SlotModel slotModel;
	private SlotView slotView;
	private ProbabilityView probabilityView;
	
    /**
     * SlotModel, SlotView, ProbabilityViewを設定する。
     * @param slotModel slotModelクラス
     * @param slotView SlotViewクラス
     * @param probabilityView ProbabilityViewクラス
     */
	public SlotControl(SlotModel slotModel, SlotView slotView, ProbabilityView probabilityView){
		this.slotModel = slotModel;
		this.slotView = slotView;;
		this.probabilityView = probabilityView;
	}
	
    /**
     * スロットをスタートする。2枚のトランプがスートのとき呼び出される。
     */
	public void startSlot(){
        int[] ans = slotModel.getRandomSlotMark();
		slotView.installSlotMarkImage(ans);
		slotView.repaint();
		if(ans[0]==ans[1]&&ans[1]==ans[2]){
            /* そろったマークによって、数秒まつ可能性がある。*/
			mediator.hitSlot(ans[0]); 
		}
	}
    
    /**
     * Mediator実装クラスから呼び出され、Mediatorをセットする。
     * @param mediator メディエイター
     */
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    
}


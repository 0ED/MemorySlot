package memorySlot.slot;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class SlotModel{
	private static final double[] PROBABILITY = new double[]{0.03,0.07,0.10,0.13,0.17,0.20,0.30};
	private static final double UPvariable = 0.10;//暫定デフォ0.10  (UPvariable * probabilityLevel)で調整確率を出す。
    private static final int INIT_LEVEL = 2;
    private int probabilityLevel = INIT_LEVEL ;  //初期調整確率は上げ幅の２倍
	
    
    /**
     * 確率レベル（段階）を1段階増やす。
     * ここでの確率レベルとは、1段階上がるごとにスロットのそろう確率が数%上がるものである。
     */
	public void addProbabilityLevel1(){
		probabilityLevel ++;
        if(probabilityLevel >8)probabilityLevel=8;//レベルが5以上になるとそれ以上上げない(レベル５でスロット的中確定なのでs)
	}
	
    
    /**
     * 確率レベル（段階）を2段階増やす。
     * ここでの確率レベルとは、1段階上がるごとにスロットのそろう確率が数%上がるものである。
     */
	public void addProbabilityLevel2(){
		probabilityLevel += 2;
        if(probabilityLevel >8)probabilityLevel=8;
	}
    
	
    /**
     * スロットの確率レベルをクリアする。
     */
	public void clearProbabilityLevel(){
		probabilityLevel = INIT_LEVEL ;
	}
	

	/**
     * スロットの確率レベル（段階）を基に乱数を発生させ、
     * 確率レベルが高いほど、当たり目がでるようにスロットのマークを割り出し、返す。
     * @return 乱数で決まったスロットのマーク3つ
     */
	public int[] getRandomSlotMark(){
		int ansSlot[] = new int[3];
		int iSum;
		//(int)(Math.random() * 7)
		double rand = Math.random();
		double nowProbability;
		
		nowProbability = 0;
		for(iSum=0;iSum<7;iSum++){//１個目の絵柄を決定					
			nowProbability += PROBABILITY[iSum];
			if(rand<nowProbability){
				ansSlot[0]=iSum;
				break;
			}
		}
		//System.out.println(rand);
		
		for(int i=1;i<3;i++){//2個目,３個目の絵柄を決定	
			nowProbability = 0;
			rand = Math.random();
			//System.out.println("乱数"+rand);
			for(int jSum=0;jSum<7;jSum++){				
				nowProbability += PROBABILITY[jSum];
				//System.out.println("**"+jSum+"**"+nowProbability);
				if(jSum == iSum){
					nowProbability +=(1-PROBABILITY[jSum])*(UPvariable * probabilityLevel);
					//System.out.println(nowProbability);
				}else{
					nowProbability -=PROBABILITY[jSum]*(UPvariable * probabilityLevel);
					//System.out.println("="+nowProbability);
				}
				if(rand<nowProbability){
					ansSlot[i]=jSum;
					break;
				}
			}
		}
		return ansSlot;
	}
    
	
	/**
     * 確率のレベル（段階）から、それぞれのスロットのマーク（7種）がそろう確率が何%になるかを求め、
     * それを描画する用にString型（7種）に変換し、返す。
     * @return それぞれのマークの組み合わせがそろう確率
     */
	public String[] getProbabilityString(){
		double ansProbability[] = new double[7];
		String probability[] = new String[7];
		
		for(int i=0;i<7;i++){ 
			ansProbability[i]=PROBABILITY[i]*(PROBABILITY[i]+(1-PROBABILITY[i])*(UPvariable * probabilityLevel))*(PROBABILITY[i]+(1-PROBABILITY[i])*(UPvariable * probabilityLevel));
			float x = (int)(ansProbability[i]*10000);
			probability[i]=String.valueOf(x/100);//確立を小数点第２位まで表示する処理
		}
		return probability;
	}
}
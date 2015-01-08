package memorySlot.score;
public class ScoreModel{
    
    private static int chipLimit=90;
    private int score;
    
    
    /**
     * 最初のスコアを設定する。
     */
    public ScoreModel(int score){
		this.score = score;
    }
    
    /**
     * スコアを増やす。
     */
    public void addScore(int score){
		this.score+= score;
    }
    
    /**
     * スコアを減らす。
     */
    public void removeScore(int score){
		this.score-=score;
    }
    
    /**
     * 現在のスコアを返す
     */
    public int getScore(){
		return score;
    }
    
    
    /**
     * スコアボードに、チップをはみ出ることなく表示させるため、現在のスコアを基に、
     * 各チップ（$1, $5, $10, $25, $50, $100の6種）の数を調整し、各チップ何枚ずつに振り分けるかを返す。
     * @return 各チップ何枚ずつに振り分けるか
     */
    public int[] getChipsCount(){
		int leftoverScore=this.score;
		int[] score = new int[7];
		int sumChip=0;
		int overChip=0;
		while(leftoverScore != 0){
			if(leftoverScore>0 && leftoverScore<=1*90){
				score[0]=leftoverScore;
				leftoverScore-=leftoverScore;
			}
			if(leftoverScore>1*90 && leftoverScore<=5*90){
				score[1]=leftoverScore/5;
				leftoverScore=leftoverScore%5;
			}
			if(leftoverScore>5*90 && leftoverScore<=10*90){
				score[2]=leftoverScore/10;
				leftoverScore=leftoverScore%10;
			}
			if(leftoverScore>10*90 && leftoverScore<=25*90){
				score[3]=leftoverScore/25;
				leftoverScore=leftoverScore%25;
                
			}
			if(leftoverScore>25*90 && leftoverScore<=50*90){
				score[4]=leftoverScore/50;
				leftoverScore=leftoverScore%50;
			}
			if(leftoverScore>50*90 && leftoverScore<=100*90){
				score[5]=leftoverScore/100;
				leftoverScore=leftoverScore%100;
			}
			if(leftoverScore>100*90 && leftoverScore<=200*90){
				score[6]=leftoverScore/200;
				leftoverScore=leftoverScore%200;
			}
			
		}
		for(int k=0; k<7; k++){
			sumChip+=score[k];
		}
		overChip=sumChip-90;

		if(overChip>0){//chipが90枚上の処理
			for(int i=0; i<7; i++){
				if(score[i]>=overChip){
					score[i]-=overChip;
					overChip=0;
				}
				else{
					overChip-=score[i];
					score[i]=0;
				}
				if(overChip==0)break;
			}
		}
		return score;
	}

    
    
    
    
    
    
    /*
    public int[] getChipsCount(){
        int[] score = new int[6];
        int leftoverScore=this.score;//元のスコアを一時的に格納。
        
        int chipSum=90;
        int temp;
        if(leftoverScore/100 != 0){
            score[5]=leftoverScore/100;
            leftoverScore=leftoverScore%100;
            chipSum-=score[5];
            if(chipSum < 0){
                score[5]+=chipSum;
            }
            
        }
        if(leftoverScore/50 != 0 && chipSum >= 0){
            score[4]=leftoverScore/50;
            leftoverScore=leftoverScore%50;
            chipSum-=score[4];
            if(chipSum < 0){
                score[4]+=chipSum;
            }
        }
        if(leftoverScore/25 != 0 && chipSum >= 0){
            score[3]=leftoverScore/25;
            leftoverScore=leftoverScore%25;
            chipSum-=score[3];
            if(chipSum < 0){
                score[3]+=chipSum;
            }
        }
        if(leftoverScore/10 != 0 && chipSum >= 0){
            score[2]=leftoverScore/10;
            leftoverScore=leftoverScore%10;
            chipSum-=score[2];
            if(chipSum < 0){
                score[2]+=chipSum;
            }
        }
        if(leftoverScore/5 != 0 && chipSum >= 0){
            score[1]=leftoverScore/5;
            leftoverScore=leftoverScore%5;
            chipSum-=score[1];
            if(chipSum < 0){
                score[1]+=chipSum;
            }
        }
        if(leftoverScore > 0 && chipSum >= 0){
            score[0]=leftoverScore;
            chipSum-=score[0];
            if(chipSum < 0){
                score[0]+=chipSum;
            }
        }
        
        //チップ分割処理
		while (chipSum>0&&score[5]>0){//空いてるスペースが1以上 かつ 100チップがある限り
			score[5]--;
			score[4]+=2;
			chipSum--;
		}
        while (chipSum>0&&score[4]>0){//空いてるスペースが1以上 かつ 50チップがある限り
            score[4]--;
            score[3]+=2;
            chipSum--;
        }
        while (chipSum>1&&score[3]>0){//空いてるスペースが2以上 かつ 25チップがある限り
            score[3]--;
            score[2]+=2;
            score[1]++;
            chipSum-=2;
        }
        while (chipSum>0&&score[2]>0){//空いてるスペースが1以上 かつ 10チップがある限り
            score[2]--;
            score[1]+=2;
            chipSum--;
        }
        while (chipSum>3&&score[1]>0){//空いてるスペースが4以上 かつ 5チップがある限り
            score[1]--;
            score[0]+=5;
            chipSum-=4;
        }
        
        
        
        int sum=score[0]+score[1]*5+score[2]*10+score[3]*25+score[4]*50+score[5]*100;
        //System.out.println("sum="+sum);
        //System.out.println("chipSum="+chipSum);
        for(int i=0; i<6; i++){
         System.out.println("score["+i+"]="+score[i]);
         }
         if(chipSum>90){
         System.out.println("chipSum="+chipSum);
         }
         if(sum!=this.score){
         System.out.println("score="+this.score);
         }
        return score;
    }
    */
}


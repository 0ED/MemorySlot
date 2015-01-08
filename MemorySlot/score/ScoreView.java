package memorySlot.score;
import memorySlot.MemorySlot;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.io.File;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;

public class ScoreView extends JPanel {
    public static final int HEIGHT = 88+15;
    public static final int WIDTH = 400;
    private static final int CHIP_WIDTH=33;
    private static final int CHIP_HEIGHT=27;
	private static final int CHIP_INTERVAL=6;
    private ScoreModel scoreModel;
    private Image[] scoreBoardImage;
    private Image[][] scoreChipsImage;
    private int[] chipScore;
    
    
    /**
     * ボードとチップの画像をインストールし、モデルクラスを受け取る。
     * @param scoreModel ScoreModelクラス
     */
    public ScoreView(ScoreModel scoreModel){
        super();
        this.scoreModel=scoreModel;
        scoreBoardImage = new Image[2];
        scoreChipsImage = new Image[3][7];
        chipScore = new int[7];
        installScoreChipImage();
        installScoreBoardImage();
        setSize(WIDTH, HEIGHT);
    }
    
    /**
     * スコアを可視化できるように、下中央に獲得したチップを描画する。
     * @param g グラフィックスのコンテキスト
     */
    public void paint(Graphics g) {
        super.paint(g);
        chipScore=scoreModel.getChipsCount();
        g.drawImage(scoreBoardImage[0], 0, 0, WIDTH, 85, this);
        int rand;
        int chip;
        int n=6;
        
        int[][] chipArray = new int[9][10];
        for(int i=0; i<9; i++){
            for(int k=0; k<10; k++){
                chipArray[i][k]=7;
            }
        }
        chip=chipScore[n];
        while(n>=0){
            for(int i=0; i<9; i++){
                for(int k=0; k<10; k++){
                    if(chip>0){
                        chipArray[i][k]=n;
                        chip--;
                    }
                    else {
                        k--;
                        n--;
                        if(n<0)break;
                        if(n>=0){
                            chip=chipScore[n];
                        }
                    }
                }
                if(n<0)break;
            }
        }
        
        boolean chipFlag=true;
        for(int i=0; i<9; i++){
            for(int t=0; t<10; t++){
				if(chipArray[i][9-t]==6){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][6], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);//305
                }
                else if(chipArray[i][9-t]==5){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][5], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);//305
                }
                else if(chipArray[i][9-t]==4){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][4], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);
                }
                else if(chipArray[i][9-t]==3){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][3], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);
                }
                else if(chipArray[i][9-t]==2){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][2], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);
                }
                else if(chipArray[i][9-t]==1){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][1], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);
                }
                else if(chipArray[i][9-t]==0){
                    rand=(int)(3*Math.random());
                    g.drawImage(scoreChipsImage[rand][0], 315-(i*33), (t*6), CHIP_WIDTH, CHIP_HEIGHT, this);
                }
                else{
                    if(chipArray[i][0] == 7){
                        chipFlag = false;
                        break;
                    }
                }
            }
            if(chipFlag==false){
                break;
            }
        }
        g.drawImage(scoreBoardImage[1], 0, 81, WIDTH, 18, this);
    }
    
    
    /**
     * チップに使う画像イメージ[3][6]を保存する。
     */
    private void installScoreChipImage() {
        String[] chipFile =new String[21];
        int num=0;
        int[] chipNum={1, 5, 10, 25, 50, 100, 200};
        for(int k=0; k<3; k++){
            for(int i=0; i<7; i++){
                chipFile[num]="MemorySlot/Image/score/chip"+chipNum[i]+"_"+k+".png";
                num++;
            }
        }	
        String dir=System.getProperty("user.dir")+File.separator;
        num=0;
        for(int i=0; i<3; i++){
            for(int k=0; k<7; k++){
                scoreChipsImage[i][k]=Toolkit.getDefaultToolkit().getImage(dir+chipFile[num]);
                num++;
            }
        }
    }
    
    
    /**
     * 獲得したチップを置くためのボードの画像イメージを保存する。
     */
    private void installScoreBoardImage(){
        String dir=System.getProperty("user.dir")+File.separator;
        String scoreBoardFile0="MemorySlot/Image/score/ScoreBoard0.png";
        String scoreBoardFile1="MemorySlot/Image/score/ScoreBoard1.png";
        scoreBoardImage[0]=Toolkit.getDefaultToolkit().getImage(dir+scoreBoardFile0);
        scoreBoardImage[1]=Toolkit.getDefaultToolkit().getImage(dir+scoreBoardFile1);
    }
}

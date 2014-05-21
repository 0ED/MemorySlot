/*
 ファイル名:random.java
 プログラムの説明：
    このプログラムはどれを選ぶのか迷ったときに使える
    そして、random関数を使って、n個の中から抽選出来る
    nがZISYO,精度がKAISUである
*/
public class random {
    private static final int ZISYO=2;
    private static final int KAISU=10000;
    
    public static void main(String[] args) {
        
        //変数
        int max = 0;
        double ran;
        double zyoken = 1 / (double)ZISYO;
        int[] array = new int[ZISYO];
        
        for(int i = 0; i < KAISU; i++) {
            ran = Math.random();
            for (int e = 0; e < ZISYO; e++) {
                if(e*zyoken <= ran && ran < (e+1)*zyoken) array[e]++;
            }
        }
        
        //出力
        for (int e = 0; e < ZISYO; e++) {
            //System.out.println("array["+e+"] = "+array[e]);
            if(array[e] > max) max = array[e];
        }
        
        for (int e = 0; e < ZISYO; e++) {
            if(array[e] == max) System.out.println("random(1~"+ZISYO+") = "+(e+1));
        }
        
        
    }
}
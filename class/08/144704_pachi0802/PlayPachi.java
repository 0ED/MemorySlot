/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習08_02
 提出日： 11/29
 ファイル名： PlayPachi.java
 プログラムの動作説明：
    球がなくなったとき、例外処理のクラスを作り、
    処理を行うパチンコプログラム
 
 実行結果：
 ============================================
 Task $ java PlayPachi
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 大当たり
 大当たり
 大当たり
 大当たり
 大当たり
 大当たり
 球がなくなりました
 獲得した球数 = 0
 ============================================
*/
class PlayPachi {
    static void simulate(Pachi pachi, int num) {
	// 引数の回数だけpachiをプレイする
        for (int i = 0; i < num; i++) {
            try {
               pachi.play(); 
            } catch (PachiEmptyException e) {
                System.out.println("球がなくなりました");
                break; //球がなくなったら、プレイ中止
            }
        }
    }

    public static void main(String[] args) {
        Pachi pachi3 = new PachiMiddle();
        
        pachi3.setTama(5000);
        simulate(pachi3, 10000);
        System.out.println("獲得した球数 = " + pachi3.getTama());
    }
}


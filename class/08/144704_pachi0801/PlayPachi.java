/*144704 高橋 右*/
class PlayPachi {
    static void simulate(Pachi pachi, int num) {
	// 引数の回数だけpachiをプレイする
        for (int i = 0; i < num; i++) {
            pachi.play();
        }
    }

    public static void main(String[] args) {
        /*Pachi pachi1 = new PachiMax();
        Pachi pachi2 = new PachiLight();*/
        Pachi pachi3 = new PachiMiddle();

        /*pachi1.setTama(5000);  //最初は5000発
        simulate(pachi1, 10000);  // 10000回プレイしてみる
        System.out.println("pachi1 = " + pachi1.getTama());  // 結果表示

        pachi2.setTama(5000);
        simulate(pachi2, 10000);
        System.out.println("pachi2 = " + pachi2.getTama());*/
        
        pachi3.setTama(5000);
        simulate(pachi3, 10000);
        System.out.println("pachi3 = " + pachi3.getTama());
    }
}


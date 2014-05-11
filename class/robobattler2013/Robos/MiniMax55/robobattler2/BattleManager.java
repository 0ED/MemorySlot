package robobattler2;

import java.awt.Point;

/**
 ゲームの進行を管理するクラス
 */
class BattleManager {
    // 定数
    private static final int  MAX_BOUTS = 7;  // 7本勝負
    
    // フィールド
    private RoboBattler2  parent;  // 勝敗を表示するために必要
    
    private Robo[]  robos = new Robo[2];  // 対戦中のロボ2体
    private Field  field;  // バトルフィールドを管理するオブジェクト
    private int[]  wins = new int[2];  // 勝ち数
    private int  bouts = 0;  // 現在の試合数
    
    private int  currentPlayer;  // 現在行動しているロボの番号（0か1）
    private int  turn;  // 現在のターン数
    private boolean  fighting;  // 戦闘中を表すフラグ
    private Point  lastTarget;  // 直前の目標座標
    private int  lastType;  // 直前に撃たれたミサイルの種類
    
    private Missile[] missiles1, missiles2;  // 先攻・後攻でミサイルの順序が同じにするために記憶しておく
    
    /**
     コンストラクタ
     @param parent BoboBattler2クラスインスタンス（GUIとのやりとりのため）
     @param roboNames 対戦するロボのクラス名2つ分
     */
    BattleManager(RoboBattler2 parent, String[] roboNames) {
        this.parent = parent;
        
        // ロボのクラスを読み込む
        // 内容はちょっと難しいので省略
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            Object object = loader.loadClass(roboNames[0]).newInstance();
            if (object instanceof Robo) {
                robos[0] = (Robo)object;
                robos[0].setManager(this);
            } else {
                System.err.println(roboNames[0] + "はロボではありません");
                System.exit(1);
            }
            object = loader.loadClass(roboNames[1]).newInstance();
            if (object instanceof Robo) {
                robos[1] = (Robo)object;
                robos[1].setManager(this);
            } else {
                System.err.println(roboNames[1] + "はロボではありません");
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("ロボの読み込みに失敗しました");
            e.printStackTrace();
            System.exit(1);
        }
        
        field = new Field();
    }
    
    /**
     バトルフィールドオブジェクトを参照するゲッターメソッド
     @return Fieldクラスオブジェクト
     */
    Field getField()  {return field;}
    
    /**
     戦闘を開始する
     */
    void startBattle() {
        bouts++;  // 試合数を1増やす
        System.out.println("*** battle " + bouts + " start ***");
        reset();  // 試合を初期状態に設定する
    }
    
    /**
     試合を1手分進める。
     currentRoboに行動させる（actionメソッドを呼ぶ）。
     双方が全部のミサイルを撃ち終えたら勝敗判定して試合を終了する。
     */
    void step() {
        if (!fighting)  return;  // 戦闘中でなければ無視する
        
        // currentPlayerの行動
        Missile missile = robos[currentPlayer].prepare();  // 次のミサイルを準備させる
        robos[currentPlayer].action(missile);  // 行動させる
        /* missileの値をactionメソッドで渡すようにしているのは、
         直接参照するようにさせるとactionメソッド内で
         値を書き換えられるおそれがあるので。
         */
        
        turn++;  // ターン数を増やす
        if (turn >= Robo.NUM_MISSILES * 2) {  // 双方がミサイルを撃つ尽くしたら
            fighting = false;  // 戦闘状態を解除
            int winner = field.checkResult();  // 勝敗判定
            endBattle(winner);  // 戦闘終了処理
        } else {
            currentPlayer = getOpponentID(currentPlayer);  // 行動中のロボを交代する
        }
    }
    
    /**
     ミサイルを撃つ。Roboクラスから呼ばれる。
     @param missile 撃つミサイルオブジェクト
     @param target ミサイルの目標座標
     @return 相手の陣地だったマス目の数
     */
    int fire(Missile missile, Point target) {
        lastType = missile.getType();  // このミサイルの種類を覚えておく
        
        if (!Field.isInField(target)) {  // 目標座標が有効かチェックする
            // 範囲外なら終了する
            lastTarget = null;
            return 0;
        }
        
        lastTarget = target;  // 目標座標を覚えておく
        
        int turnover = 0;  // 相手の陣地を塗り替えたマス目を数えるローカル変数
        Point[] area = missile.getArea();  // 爆撃範囲を取得する
        for (Point p : area) {  // 爆撃範囲のそれぞれに対して処理を行う
            /* ↑この書き方は拡張for文を使っている。次のように書くのと同じ意味となる。
             Point p;
             for (int i = 0; i < area.length; i++) {
             p = area[i];
             */
            int x = target.x + p.x;  // 実座標を計算する
            int y = target.y + p.y;
            if (Field.isInField(x, y)) {  // 爆撃座標が有効かチェックする
                turnover += field.setOwner(x, y, currentPlayer);  // 自分の陣地にする
            }
        }
        
        return turnover;
    }
    
    /**
     直前の相手の目標座標を参照する
     @return 座標
     */
    Point getLastTarget() {
        return lastTarget;
    }
    
    /**
     直前の相手の撃ったミサイルの種類を参照する
     @return 種類を表す値。値はMissileクラスで定義されているMISSILE_TYPEで始まる定数のいずれか
     */
    int getLastType() {
        return lastType;
    }
    
    private void endBattle(int winner) {
        fighting = false;  // 戦闘状態を解除する
        // 勝利数をカウントして表示させる
        if (winner == -1) {
            parent.setScore(-1, 0);
            System.out.println("*** draw ***");
        } else {
            wins[winner]++;
            parent.setScore(winner, wins[winner]);
            System.out.println("*** robo " + winner + " wins ***");
        }
    }
    
    private void reset() {
        // 試合を初期化する
        field = new Field();
        
        if (bouts == MAX_BOUTS) {  // 最終ラウンドの先手は抽選で選ぶ
            currentPlayer = (int)(Math.random() * 2);
            robos[0].reset();
            robos[1].reset();
        } else {
            currentPlayer = (bouts - 1) % 2;  // 先手は交互
            if (bouts % 2 == 1) {
                // 奇数試合目はミサイルの順番を新たに決める
                robos[0].reset();
                robos[1].reset();
                missiles1 = robos[0].getMissiles();  // ミサイルのコピーを保存しておく
                missiles2 = robos[1].getMissiles();
            } else {
                // 偶数試合目は前と同じミサイルの順番で交代する
                robos[0].reset(missiles2);
                robos[1].reset(missiles1);
            }
        }
        
        turn = 0;
        fighting = true;
        lastTarget = null;
    }
    
    private int getOpponentID(int id) {
        return  id == 0 ? 1 : 0;
    }
}

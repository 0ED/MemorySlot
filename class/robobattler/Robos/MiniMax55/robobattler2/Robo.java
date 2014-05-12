package robobattler2;

import java.awt.Point;

/**
   ロボを作るための元となるクラス。
   実際に戦わせるロボはこのクラスを継承して作成する。
*/
abstract public class Robo {
    // 定数
    static final int  NUM_MISSILES = 16;  // 1回のバトル時の持ちミサイル数
    private static final int  NUM_POWERSEARCH = 5;  // 1回のバトルで使えるパワーサーチの回数

    // フィールド
    private BattleManager  manager;  // 試合を管理するクラス
    private Missile[]  missiles = new Missile[NUM_MISSILES];  // 持ちミサイル
    private int  numPowerSearch;  // 残りパワーサーチ回数
    private Missile  nextMissile;  // 次のミサイル
    private int  nextMissileId;  // 次のミサイルの番号（missilesの添え字）
    private Point  searchResult;  // 相手のミサイルの着弾点。何回サーチしても同じ結果になるように覚えておく。


    /**
       戦闘管理クラスを設定する
       @param manager 試合を管理するBattleManagerクラスのオブジェクト
     */
    final void setManager(BattleManager manager) {
	this.manager = manager;
    }

    /**
       戦闘の初期状態にする
     */
    final void reset() {
	initMissiles();  // ミサイルを準備する
	numPowerSearch = NUM_POWERSEARCH;  // パワーサーチの残り回数を設定する
    }

    /**
       与えられたミサイルで戦闘の初期状態にする
    */
    final void reset(Missile[] missiles) {
	this.missiles = missiles;
	for (Missile m : missiles) {
	    m.setHorizontal();  // LineMissileの向きを水平方向に初期化しておく。Line以外では無視される。
	}
	nextMissileId = 0;
	numPowerSearch = NUM_POWERSEARCH;  // パワーサーチの残り回数を設定する
    }

    /**
       ミサイルを準備する。各種類のミサイルを所定の数だけ作成し、順番をランダムにする。
     */
    final void initMissiles() {
	// ミサイルを作成する
	for (int i = 0; i < 4; i++)  missiles[i] = new LineMissile();
	for (int i = 4; i < 8; i++)  missiles[i] = new PlusMissile();
	for (int i = 8; i < 12; i++)  missiles[i] = new XMissile();
	for (int i = 12; i < 14; i++)  missiles[i] = new BigPlusMissile();
	for (int i = 14; i < 16; i++)  missiles[i] = new BigXMissile();

	// 順番をランダムに入れ替える
	for (int src = 0; src < 16; src++) {
	    int dest = (int)(Math.random() * 16);
	    Missile swap = missiles[src];
	    missiles[src] = missiles[dest];
	    missiles[dest] = swap;
	}

	nextMissileId = 0;  // 次のミサイルの番号を初期化する
    }

    /**
       ミサイルを参照する。先攻と後攻で同じミサイル順になるようにするために使う。
       @return ミサイルの配列
    */
    final Missile[] getMissiles() {
	return missiles;
    }

    /**
       次のターンの準備をする
       @return 次のミサイル
     */
    final Missile prepare() {
	searchResult = null;  // 相手の着弾点の探索結果をリセット

	if (nextMissileId == NUM_MISSILES)  return null;  // ミサイルを撃ち尽くしていたらnullを返して終わる（念のため）
	nextMissile = missiles[nextMissileId];  // 次のミサイルを取り出す
	nextMissileId++;  // 次のミサイル番号を1増やす

	return nextMissile;
    }

    /**
       直前に相手が撃ったミサイルの種類を参照する
       @return ミサイルの種類の値。値の意味は、Missileクラスの定数MISSILE_TYPE*を参照。
     */
    protected final int searchType() {
	return manager.getLastType();
    }

    /**
       直前に相手が撃ったミサイルの着弾点を参照する。ただし、このメソッドではx軸、y軸方向それぞれに、1/2の確率で1マスずれた値が返される（バトルフィールド外になる場合はずれない）。なお、このメソッドは同一ターンで何度呼んでも同じ値を返す。
       @return 着弾点の座標を表すPointクラスオブジェクト。相手がまだ撃っていない場合はnullを返す。
     */
    protected final Point searchTarget() {
	if (searchResult != null)  return searchResult;  // 既にサーチ済みであればその結果を返す

	Point lastTarget = manager.getLastTarget();  // 正確な着弾点を参照する
	if (lastTarget == null)  return null;  // 相手がまだ撃っていない場合
	int x = lastTarget.x;
	int y = lastTarget.y;

	// x軸方向のゆらぎを加える
	double r = Math.random();
	if (r < 0.25) {
	    x++;
	} else if (r < 0.5) {
	    x--;
	}
	if (x < 0)  x = 0;
	if (x >= Field.SIZE)  x = Field.SIZE - 1;

	// y軸方向のゆらぎを加える
	r = Math.random();
	if (r < 0.25) {
	    y++;
	} else if (r < 0.5) {
	    y--;
	}
	if (y < 0)  y = 0;
	if (y >= Field.SIZE)  y = Field.SIZE - 1;

	searchResult = new Point(x, y);
	return searchResult;
    }

    /**
       パワーサーチを行う。パワーサーチでは直前に相手が撃ったミサイルの着弾点を正確に参照する。通常のサーチ結果は誤差を含むことがあるので、パワーサーチと結果が異なることがある。
       @return 着弾点の座標を表すPointクラスオブジェクト。相手がまだ撃っていない場合はnullを返す。パワーサーチの残り回数がない場合は通常のサーチを行う。
     */
    protected final Point powerSearchTarget() {
	if (numPowerSearch <= 0) {  // パワーサーチが残っているか
	    return searchTarget();
	} else {
	    numPowerSearch--;  // 残り回数を1減らす
	    Point lastTarget = manager.getLastTarget();
	    if (lastTarget == null)  return null;
	    searchResult = (Point)lastTarget.clone();  // 値を書き換えれないようにコピーを返す
	    return searchResult;
	}
    }

    /**
       ミサイルを撃つ
       @param x 着弾点のx座標
       @param y 着弾点のy座標
       @return 相手の陣地だったマス目の数
     */
    protected final int fire(int x, int y) {
	Point point = new Point(x, y);
	return manager.fire(nextMissile, point);
    }

    /**
       行動をプログラミングする抽象メソッド。自機の番に自動的に呼ばれる。
       出場するロボはこのメソッドを実装しなければならない。
       @param missile 次に撃つミサイル。このメソッドが呼ばれるときに自動的に渡される。
     */
    abstract protected void action(Missile missile);
}

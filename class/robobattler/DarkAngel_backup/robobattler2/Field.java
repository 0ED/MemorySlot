package robobattler2;

import	java.awt.Point;

/**
 バトルフィールドを管理するクラス。
 */
public class Field {
    // 定数
    public static final int  SIZE = 9;  // マス目の数

    // フィールド
    private int[][]  area = new int[SIZE][SIZE];
      /* 各マス目の所有者を管理する配列、値は、
	 0: 所有者なし
	 1: 第1ロボ
	 2: 第2ロボ
      */

    /**
       所有者を参照する
       @param x x座標
       @param y y座標
       @return 所有者の値
    */
    int getOwner(int x, int y) {
	return area[x][y];
    }

    /**
       所有者を設定する
       @param x x座標
       @param y y座標
       @param side ロボのID、第1ロボは0、第2ロボは1であることに注意
       @return 相手の所有だった場合1、それ以外の場合0
     */
    int setOwner(int x, int y, int side) {
	side++;  // ロボのIDを内部の所有者表現の数値にするために1足しておく
	
	int prevOwner = area[x][y];  // 前の所有者を覚えておく
	area[x][y] = side;  // 所有者を書き換える
	if (prevOwner != 0 && prevOwner != side) {  // 前の所有者が対戦相手かチェック
	    return 1;
	} else {
	    return 0;
	}
    }

    /**
       所有地を数える
       @param side ロボのID、第1ロボは0、第2ロボは1であることに注意
       @return 所有地の数
     */
    int count(int side) {
	side++;  // ロボのIDを内部の所有者表現の数値にするために1足しておく

	int count = 0;
	for (int i = 0; i < SIZE; i++) {
	    for (int j = 0; j < SIZE; j++) {
		if (area[i][j] == side)  count++;
	    }
	}
	return count;
    }

    /**
       勝敗チェックを行う
       @return 勝者のロボのID、第1ロボは0、第2ロボは1であることに注意。引き分けの場合は-1
     */
    int checkResult() {
	int count[] = new int[2];
	for (int i = 0; i < 2; i++) {
	    count[i] = count(i);
	}

	int winner = -1;
	if (count[0] > count[1])  winner = 0;
	if (count[0] < count[1])  winner = 1;
	return winner;
    }

    /**
       バトルフィールド内かチェックする
       @param x x座標
       @param y y座標
       @return バトルフィールド内の場合true、外の場合false
     */
    static boolean isInField(int x, int y) {
	if (0 <= x && x < SIZE && 0 <= y && y < SIZE) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
       バトルフィールド内かチェックする
       @param point チェックしたい座標を表すPointクラスオブジェクト
       @return バトルフィールド内の場合true、外の場合false
     */
    static boolean isInField(Point point) {
	return isInField(point.x, point.y);
    }
}


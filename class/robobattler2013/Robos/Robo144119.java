/* 144119 市岡　将
 * クラス名: Robo144119
 * プログラムの説明・アピールポイント:
 * まずはじめに申し上げておきますと、
 * このRobo144119は特段目立ったアルゴリズムというわけでもないですし、
 * 実際かなり弱いです。
 * 具体的に申しますと、基本的には、クラス内部で戦況を把握するための、
 * マップを表す2次元配列を用意し、その戦況を得られる情報から随意更新し、
 * その戦況を元に、
 * * なるべく敵のマスと空白のマスを奪う
 * * 自陣にはなるべく被らない
 * ようなマスを探索して、そこに撃ちこむようにしています。
 * サーチの誤差は考慮していません。せっかくのパワーサーチも用いてません。
 * ただ、LINEミサイルだけが曲者で、まず敵の撃ったLINEミサイルはその向きをサーチする術が無かったため
 * 無視しています。自分の撃ったLINEミサイルに関しても、これを考慮するとプログラムが複雑になること、
 * また、実際に組んで戦わせても逆に自分の撃ったLINEミサイルの情報を反映させた場合の方が弱くなってしまったことから
 * こちらも無視しています。
 * こんなプログラムですがアピールポイントといたしましては、
 * 1. コメントをなるべく入れたこと
 *    よいプログラムはコメントが無くても読みやすいものであるという説もありますが、
 *    今回は読む人がより分かりやすいように各処理の内容や、
 *    読んでて少しコメントがあった方が分かり易いと思った場所には補助的に入れるようにしました。
 *    変数の意味や各処理の内容は /* で始まる複数行コメント形式で、
 *    補助的なコメントは// で始まる単行コメントで、
 *    書くようにしました。
 * 2. forecastedFieldに対してミサイルを撃つという処理をshootToForecastedField()というメソッドとして
 *    抽出することによって、全体の行数を抑えたこと
 *    これに関しては、最初はupdateForecastedFieldAboutEnemy()とupdateForecastedFieldAboutMe()とに
 *    今のshootToForecastedField()に相当する処理が重複していたのですが、コードが重複するのは基本的に悪なので
 *    shootToForecastedField()として抽出して共通化しました。
 * 3. 各変数名/メソッド名に気を配ったこと
 *    例えばですが、forecastedFieldは「予想されるフィールド」という意味で付けたのですが、
 *    この「予想される」という語について最初はexpectという語を使おうと思ったのですが、
 *    expectは「（確信を持って）予想する」と、今回のように誤差を含んだりLINEミサイルを考慮しなかったりの
 *    場合、「確信を持って」とは言えないと判断したのでよりニュアンスの近いforecastという語を使うようにしました。
 *    また、各メソッド名は「動詞 + 目的語」の形に統一しました。
 *    他には、Javaプログラムの慣習に従い、基本的にはlowerCamelCaseで、
 *    定数的性質を持つ変数はUPPER_SNAKE_CASEで命名しました。
 * 4. LINEミサイルの撃ち方を工夫したこと
 *   詳細はコードの方のコメントを見て頂きたいのですが、例えばマス目をなるべく稼げるよう中央付近に、
 *   縦と横に交互に撃つことで効率的に、2回毎に場所をずらして前に撃った範囲と被らないように、
 *   などと考えました。
 *
 * また、これはプログラムとは直接関係ないですが、
 * 開発時は何度も同じ長ったらしいコンパイルコマンドを撃たなくて済むようMakefileを作って
 * makeのみでコンパイル出来るようにしたりもしました。
 * 以上、長くなりましたがドキュメントは以上になります。以下、コード本体です。凡そ200行強ほどです
 */

import robobattler2.*;
import java.awt.Point;

public class Robo144119 extends Robo  {

    private static final int FIELD_WIDTH = robobattler2.Field.SIZE; // 9
    private static final int FIELD_HEIGHT = robobattler2.Field.SIZE; // 9

    /* 発射場所計算のための各マスの価値
     * このへんの値はうまく調整すると強くなる
     * ここでは取り敢えず経験的に強かった時の値を入れてる
     */
    private static final int EMPTY_AREA = 2;
    private static final int ENEMY_AREA = 3;
    private static final int MY_AREA = -1;

    /* 「外堀」の幅 */
    private static final int OUTER_MOAT_WIDTH = 2;

    /* サーチなどで大方予想される(forecasted)フィールドの状況
     * サーチなどの使用上ある程度の誤差が含まれる
     * 9x9のマップを幅2の「外堀」で囲ったもの
     */
    private int[][] forecastedField = new int[FIELD_WIDTH+OUTER_MOAT_WIDTH*2][FIELD_HEIGHT+OUTER_MOAT_WIDTH*2];

    /* コンストラクタ。予想フィールドを初期化 */
    public Robo144119() {
        initForecastedField();
    }

    /* forecastedFieldの初期化 */
    void initForecastedField() {

        /* 外堀の内側 */
        for (int x = OUTER_MOAT_WIDTH; x <= OUTER_MOAT_WIDTH + FIELD_WIDTH; x++) {
            for (int y = OUTER_MOAT_WIDTH; y <= OUTER_MOAT_WIDTH + FIELD_HEIGHT; y++) {
                forecastedField[x][y] = EMPTY_AREA;
            }
        }

        /* 外堀
         * 計算の関係上外堀をMY_AREAで初期化
         * 何故MY_AREAで初期化するかというと、
         * 1. 外堀はフィールド外扱いなので出来ればここに被らないように撃って欲しい
         * 2. ところで、自陣のマスも出来れば被って撃たないようにして欲しい
         * ということは外堀は自陣扱いにすれば良いのでは、ということでMY_AREAの値を持たせることに
         */
        for (int i = 0; i < OUTER_MOAT_WIDTH; i++) {
            for (int j = 0; j < robobattler2.Field.SIZE; j++) {
                forecastedField[i][j] = MY_AREA; // 左
                forecastedField[j][i] = MY_AREA; // 上
                forecastedField[FIELD_WIDTH + OUTER_MOAT_WIDTH - i][j] = MY_AREA; // 右
                forecastedField[j][FIELD_HEIGHT + OUTER_MOAT_WIDTH - i] = MY_AREA; // 下

            }
        }
    }

    private int lineMissileCount = 0; // LINEミサイルを何発撃ったか
    private int actionCount = 0; // 何回action()したか
    protected void action(robobattler2.Missile missile) {

        // 1ラウンドで16回actionメソッドが呼ばれる。
        // なので、1ラウンド終わったらforecastedFieldを初期化
        if (actionCount >= 16) {
            initForecastedField();
        }

        /* 前ターンの敵の行動をサーチしてforecastedFieldを更新する */
        updateForecastedFieldAboutEnemy();

        Point target = new Point(); // ミサイルの発射先

        /* LINEミサイルとそれ以外で処理を分ける */
        /* LINEミサイルの場合、フィールド外にはみ出さず、なおかつマス目が稼げる中央付近に撃つ */
        if (missile.getType() == robobattler2.Missile.MISSILE_TYPE_LINE) {
            /* 縦撃ち・横撃ちを繰り返す */
            if (lineMissileCount % 2 == 0) {
                missile.setHorizontal();
            }else{
                missile.setVertical();
            }

            /* 場所は、9x9マスの中央近傍9マスに以下のように番号を振ると
             * 1 2 3
             * 4 5 6
             * 7 8 9
             * 1回目:1に横撃ち 2回目:1に縦撃ち
             * 3回目:5に横撃ち 4回目:5に縦撃ち
             * となる。
             * 1, 2回目と3, 4回目で場所をずらしているのは、
             * 同じ場所に撃つとせっかくのミサイルの範囲が被ってもったいないから
             */
            target.setLocation(FIELD_WIDTH/2 + lineMissileCount/2, FIELD_HEIGHT/2 + lineMissileCount/2);

            lineMissileCount++;
            if (lineMissileCount >= 4) {
                /* 4回を周期として回す。Robo.javaを覗いてみたところ、1ラウンドにLINEミサイルは4発だったから */
                lineMissileCount = 0;
            }

        } else { // LINEミサイル以外ならdecideFirePointメソッドに任せる
            target = decideFirePoint();
        }

        /* 撃つ */
        fire(target.x, target.y);
        /* 撃ったミサイルの情報をforecastedFieldに反映させる */
        updateForecastedFieldAboutMe(target, missile);

        actionCount++;
    }

    /* LINEミサイル以外の場合の時の発射先の計算 */
    private Point decideFirePoint() {
        // 最も稼げるマスのスコア、座標
        int maxScore = Integer.MIN_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE; // 座標としてはありえない値で初期化

        // for分の中がごちゃごちゃしてるのは外堀分の補正
        for (int x = OUTER_MOAT_WIDTH; x < FIELD_WIDTH + OUTER_MOAT_WIDTH; x++) {
            for (int y = OUTER_MOAT_WIDTH; y < FIELD_HEIGHT + OUTER_MOAT_WIDTH; y++) {
                // ポイントが一番でかいところにブチ込むので、
                // 計算してそれまでの最大値maxScoreを上回ったらそこがでかい
                int scoreTmp = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1 ; j++) {
                        scoreTmp += forecastedField[x+i][y+j];
                    }
                }

                if (maxScore < scoreTmp) {
                    maxScore = scoreTmp;
                    maxX = x - OUTER_MOAT_WIDTH; // 左の外堀分補正
                    maxY = y - OUTER_MOAT_WIDTH; // 上の外堀分補正
                }
            }
        }

        Point result = new Point(maxX, maxY);
        return result;
    }

    /* 敵の行動をサーチして、その情報を元にforecastedFieldを更新 */
    private void updateForecastedFieldAboutEnemy() {
        int type = searchType();
        Point point = searchTarget(); // 誤差を含む

        // これは、自分が先行だった時の第1回目だった場合
        if (point == null) {
            return; // 何もしない
        }
        shootToForecastedField(point, type, ENEMY_AREA);

    }

    /* 自分の行動を元に、forecastedFieldを更新 */
    private void updateForecastedFieldAboutMe(Point point, robobattler2.Missile missile) {
        shootToForecastedField(point, missile.getType(), MY_AREA);
    }

    /* shootToForecastedField()
     * forecastedFieldに対して、bulletAttr(MY_AREAもしくはENEMY_AREA)で
     * タイプがtypeのミサイルをpointに撃ちこむ
     * LINEミサイルの時は無視したほうが強かった、またLINEミサイルを考慮すると
     * 向きを指定しなければならなかったりとメソッドの使い勝手も悪くなったので
     * LINEミサイルに関しては無視
     */
    private void shootToForecastedField(Point point, int type, int bulletAttr) {
        int x = point.x + OUTER_MOAT_WIDTH; // 外堀分補正
        int y = point.y + OUTER_MOAT_WIDTH; // 同じく

        // 外堀のお陰でIndexOutOfBoundsExceptionを考えなくてもいい
        switch (type) {
            case robobattler2.Missile.MISSILE_TYPE_LINE:
                // LINEは無視
                break;
            case robobattler2.Missile.MISSILE_TYPE_PLUS: // Plus型
                forecastedField[x][y] = bulletAttr;
                forecastedField[x+1][y] = bulletAttr;
                forecastedField[x-1][y] = bulletAttr;
                forecastedField[x][y+1] = bulletAttr;
                forecastedField[x][y-1] = bulletAttr;
                break;
            case robobattler2.Missile.MISSILE_TYPE_X: // X型
                forecastedField[x][y] = bulletAttr;
                forecastedField[x+1][y+1] = bulletAttr;
                forecastedField[x-1][y-1] = bulletAttr;
                forecastedField[x-1][y+1] = bulletAttr;
                forecastedField[x+1][y-1] = bulletAttr;
                break;
            case robobattler2.Missile.MISSILE_TYPE_BIGPLUS: // BigPlus型
                for (int i = 1; i <= 2; i++) { // 縦横幅2で爆撃するので
                    forecastedField[x][y+i] = bulletAttr;
                    forecastedField[x][y-i] = bulletAttr;
                    forecastedField[x+i][y] = bulletAttr;
                    forecastedField[x-i][y] = bulletAttr;
                }
                break;
            case robobattler2.Missile.MISSILE_TYPE_BIGX: // BigX型
                for (int i = 1; i <= 2; i++) { // 縦横幅2で爆撃するので
                    forecastedField[x+i][y+i] = bulletAttr;
                    forecastedField[x-i][y-i] = bulletAttr;
                    forecastedField[x-i][y+i] = bulletAttr;
                    forecastedField[x+i][y-i] = bulletAttr;
                }
                break;
            default:
                // ここに到達することは無い。何もしない
                break;
        }
    }
}


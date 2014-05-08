
package robobattler2;

import java.awt.*;
import javax.swing.*;

/**
 バトルフィールドの状態を表示するためのGUI部品
 */
class FieldPanel extends JPanel {
    // 定数
    private static final int  AREA_SIZE = 40;  // マス目の大きさ（ピクセル数）
    static final int  SIZE = AREA_SIZE * Field.SIZE;  // 表示に必要な大きさ＝全体を表示するのに要するピクセル数
    private static Color[]  colors = {Color.WHITE, Color.RED, Color.BLUE};
    
    // フィールド
    private BattleManager  manager;
    
    
    /**
     コンストラクタ
     @param manager 戦闘を管理するBattleManagerクラスオブジェクト
     */
    FieldPanel(BattleManager manager) {
        this.manager = manager;
        setBackground(Color.WHITE);  // 部品の背景色を白に設定
    }
    
    /**
     再描画を行うメソッド。JPanel#paintComponentをオーバーライドしている。
     @param g  グラフィクスコンテキスト。自動的に渡される。
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // 親クラスの描画処理メソッドを呼ぶ。これがないと背景色が白にならない。
        
        // 先に各マス目を所有者の色で塗りつぶす
        Field field = manager.getField();
        for (int x = 0; x < Field.SIZE; x++) {
            for (int y = 0; y < Field.SIZE; y++) {
                g.setColor(colors[field.getOwner(x, y)]);
                g.fillRect(AREA_SIZE * x, AREA_SIZE * y, AREA_SIZE, AREA_SIZE);
            }
        }
        
        // 枠を描く
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, SIZE, SIZE);
        
        // 罫線を描く
        for (int x = 0; x < SIZE; x += AREA_SIZE) {
            g.drawLine(x, 0, x, AREA_SIZE * Field.SIZE);
        }
        for (int y = 0; y < SIZE; y += AREA_SIZE) {
            g.drawLine(0, y, AREA_SIZE * Field.SIZE, y);
        }
    }
}

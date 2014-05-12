package robobattler2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RoboBattler2 implements Runnable {
    /* フィールド */
    private BattleManager	manager;
    // GUI部品
    private FieldPanel  fieldPanel;
    private JButton  manualStartButton;
    private JButton  autoStartButton;
    private JLabel[]  scoreLabels = new JLabel[2];

    // 自動実行用
    private Thread  thread;
    private static final int  INTERVAL_TIME = 200;

    /**
       コンストラクタ
       @param roboNames 対戦するロボのクラス名2つの入った配列
    */
    RoboBattler2(String[] roboNames) {
	manager = new BattleManager(this, roboNames);  // ゲーム進行を管理するオブジェクト

	// ウィンドウを開く
	JFrame frame = new JFrame();
	frame.setTitle("Robo Battler 2");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(640, 480);
	frame.getContentPane().setLayout(null);

	// ゲームフィールド
	fieldPanel = new FieldPanel(manager);
	fieldPanel.setBounds((640 - FieldPanel.SIZE) / 2, (480 - FieldPanel.SIZE) / 2, FieldPanel.SIZE + 1, FieldPanel.SIZE + 1);
	frame.getContentPane().add(fieldPanel);
	
	// 得点表示ラベル
	scoreLabels[0] = new JLabel("0");
	scoreLabels[0].setBounds(10, 10, 50, 50);
	frame.getContentPane().add(scoreLabels[0]);
	scoreLabels[1] = new JLabel("0");
	scoreLabels[1].setBounds(580, 10, 50, 50);
	frame.getContentPane().add(scoreLabels[1]);

	// 自動戦闘開始ボタン（Step10で完成させる）
	autoStartButton = new JButton("auto start");
	autoStartButton.setBounds(500, 380, 120, 30);
	autoStartButton.setFocusable(false);
	autoStartButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    manager.startBattle();
		    manualStartButton.setEnabled(false);
		    autoStartButton.setEnabled(false);
		    fieldPanel.repaint();
		    start();
		}
	    });
	frame.getContentPane().add(autoStartButton);

	// マニュアル戦闘開始ボタン
	manualStartButton = new JButton("manual start");
	manualStartButton.setBounds(500, 420, 120, 30);
	manualStartButton.setFocusable(false);
	manualStartButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    manager.startBattle();  // 戦闘を開始する
		    manualStartButton.setEnabled(false);  // このボタンを無効化する
		    fieldPanel.repaint();  // フィールドの再描画を要求する
		}
	    });
	frame.getContentPane().add(manualStartButton);

	// キーイベント処理。何かキーを押すと1手ずつ進む。
	frame.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		    System.out.println("key typed: " + e.getKeyChar());  // 押されたキーを表示する（デバッグ用）
		    manager.step();
		    fieldPanel.repaint();
		}
	    });

	frame.setVisible(true);
    }

    /**
       得点を表示する
       @param id 得点を設定するロボのID、引き分けの場合は0か1以外の数値（得点は無視される）
       @param score 点数
    */
    void setScore(int id, int score) {
	if (id == 0 || id == 1) {
	    scoreLabels[id].setText(Integer.toString(score));
	}
	manualStartButton.setEnabled(true);
	autoStartButton.setEnabled(true);
	stop();
	fieldPanel.repaint();
    }

    /**
       自動実行を開始する
     */
    void start() {
	thread = new Thread(this);
	thread.start();
    }

    /**
       自動実行時の繰り返し処理
     */
    public void run() {
	Thread thisThread = Thread.currentThread();
	while (thisThread == thread) {
	    // 現在のスレッドが活きている限り繰り返す
	    try {
		manager.step();  // 1手進める
		fieldPanel.repaint();  // 表示を更新する
		Thread.sleep(INTERVAL_TIME);  // INTERVAL_TIMEだけ休む
	    } catch (InterruptedException ie) {
		// 割り込みが入ったら繰り返しを終了する
		thread = null;
		break;
	    }
	}
    }

    /**
       自動実行を停止する
     */
    void stop() {
	thread = null;  // threadの値を書き換えておけばrunメソッドのwhile文の判定がfalseになり、自動実行が停止する。
    }

    /**
       メインメソッド
       @param args 対戦させる2体のロボのクラス名を指定する
    */
    public static void main(String[] args) {
	if (args.length < 2) {
	    System.err.println("Please tell me the names of fighters.");
	} else {
	    RoboBattler2 app = new RoboBattler2(args);
	}
    }
}

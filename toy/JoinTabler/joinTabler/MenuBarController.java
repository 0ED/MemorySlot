package joinTabler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JMenuItem;
import javax.swing.JFrame;

public class MenuBarController
	implements ActionListener
{
	private MenuBarView view;

	/**
	 * Viewから、ActionListenerを設定する。
	 * @param memorySlot MemorySlotクラス
	 * @param view MenuBarViewクラス
	 */
	public MenuBarController(MenuBarView aView)
	{
		this.view = aView;
		this.view.setListener(this);
	}

	/**
	 * メニューアイテムを押したときに呼び出され、それぞれクリックしたとき、反応が起きる。
	 * ルール説明を押すと、ルールを説明するフレームが出てくる。
	 * リセットを押すとスタート画面に戻り、終了を押すとゲームが終わり、ウィンドウが閉じる。
	 * @param anEvent クリック情報
	 */
	public void actionPerformed(ActionEvent anEvent)
	{
		if(anEvent.getSource() instanceof JMenuItem) {
			JMenuItem anItem = (JMenuItem) anEvent.getSource();
			if(anItem.getText().equals("環境設定"))
			{
				JFrame aWindow = new JFrame();
				aWindow.setTitle("環境設定");
				aWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				aWindow.setSize(Const.WIN_SETTING_WIDTH, Const.WIN_SETTING_HEIGHT); //メニューバーと上フレームの分の25px
				aWindow.setLocation(Const.WIN_SETTING_X, Const.WIN_SETTING_Y); //メニューバーと上フレームの分の25px
				aWindow.setResizable(false); //サイズ調整できないように
				
				SettingModel aModel = new SettingModel();
				SettingView aView = new SettingView(aModel);
				SettingController aController = new SettingController(aModel,aView);

				aWindow.getContentPane().add(aView);
				aWindow.setVisible(true);
				
			}
		}
	}
}

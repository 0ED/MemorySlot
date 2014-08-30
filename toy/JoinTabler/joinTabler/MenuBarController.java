package joinTabler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
				
			}
		}
	}
}

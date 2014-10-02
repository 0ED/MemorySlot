package joinTabler;

import joinTabler.JoinTablerModel;
import joinTabler.JoinTablerView;
import joinTabler.JoinTablerController;
import javax.swing.JFrame;

public class Example
{
	public static void main(String[] args) 
	{

		JFrame aWindow = new JFrame();
        aWindow.setTitle("おたべ");
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT+25);
        aWindow.setLocation(Const.WIN_X, Const.WIN_Y);
        aWindow.setResizable(false); //サイズ調整できないように

		JoinTablerModel aModel = new JoinTablerModel();
        JoinTablerView aView = new JoinTablerView(aModel);
        JoinTablerController aController = new JoinTablerController(aModel,aView);
		aController.setWindow(aWindow);

		MenuBarView aMenuBarView = new MenuBarView();
		MenuBarController aMenuBarController = new MenuBarController(aMenuBarView);
        aWindow.setJMenuBar(aMenuBarView);

		aWindow.getContentPane().add(aView);
        aWindow.setVisible(true);
	}
}

package joinTabler;

import joinTabler.Const;
import joinTabler.FileDroper;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.dnd.DropTarget;

public class MainFrame extends JFrame
{
    /**
     * 
     */
    public MainFrame() 
	{
        this.setTitle("おたべ");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT+44); //メニューバーと上フレームの分の44px
        this.setLocation(Const.WIN_X, Const.WIN_Y+44); //メニューバーと上フレームの分の44px
        //this.setResizable(false); //サイズ調整できないように
        this.getContentPane().setLayout(null); 
		this.createDragArea();
		this.createJoinButton();
        //this.setJMenuBar(menuBarView);
    }
	
	public void createDragArea()
	{
		JPanel aDropArea = new JPanel();
		aDropArea.setLocation(
			Const.WIN_WIDTH/2-Const.DRAG_AREA_WIDTH/2,
			Const.WIN_HEIGHT/2-Const.DRAG_AREA_HEIGHT/2
		);
		aDropArea.setSize(Const.DRAG_AREA_WIDTH,Const.DRAG_AREA_HEIGHT);
		aDropArea.setBackground(new Color(255, 255, 255));
		new DropTarget(aDropArea,new FileDroper());
        this.getContentPane().add(aDropArea);
	}

	public void createJoinButton()
	{
		JButton aButton = new JButton("JOIN");
		aButton.setLocation(
			Const.WIN_WIDTH/2-Const.BUTTON_WIDTH/2,
			Const.WIN_HEIGHT/2+Const.DRAG_AREA_HEIGHT/2+10
		);
		aButton.setSize(Const.BUTTON_WIDTH,Const.BUTTON_HEIGHT);
		aButton.setBackground(new Color(0, 255, 255));
        this.getContentPane().add(aButton);
	}
}

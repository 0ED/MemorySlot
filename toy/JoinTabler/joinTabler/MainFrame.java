package joinTabler;

import joinTabler.Const;
import joinTabler.FileDroper;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.dnd.DropTarget;
import javax.swing.BoxLayout;

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
		JPanel aPanel = new JPanel();
		aPanel.add(new JLabel("ダメよーダメダメ!"));
		aPanel.add(new JLabel("ダメよーダメダメ!"));
		aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.PAGE_AXIS));
		JScrollPane aScrollPane = new JScrollPane(aPanel);
		aScrollPane.setLocation(
			Const.WIN_WIDTH/2-Const.DRAG_AREA_WIDTH/2,
			Const.WIN_HEIGHT/2-Const.DRAG_AREA_HEIGHT/2
		);
		aScrollPane.setSize(Const.DRAG_AREA_WIDTH,Const.DRAG_AREA_HEIGHT);
		aScrollPane.setBackground(new Color(255, 255, 255));


		FileDroper aFileDroper = new FileDroper();
		System.out.println(aPanel);
		aFileDroper.setParentPanel(aPanel,aScrollPane,this);

		new DropTarget(aScrollPane,aFileDroper);
        this.getContentPane().add(aScrollPane);

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

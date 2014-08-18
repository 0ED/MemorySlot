package joinTabler;

import joinTabler.Const;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements DragInterface 
{
    /**
     * 
     */
    public MainFrame() 
	{
        this.setTitle("おたべ");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Const.WIDTH, Const.HEIGHT+44); //メニューバーと上フレームの分の44px
        this.setLocation(Const.X, Const.Y+44); //メニューバーと上フレームの分の44px
        this.setResizable(false); //サイズ調整できないように
        //this.getContentPane().setLayout(null); 
		this.createDragArea();
        //this.setJMenuBar(menuBarView);
    }
	
	public void createDragArea()
	{
		JTextArea aTextArea = new JTextArea();
        this.getContentPane().add(aTextArea);
	}

}

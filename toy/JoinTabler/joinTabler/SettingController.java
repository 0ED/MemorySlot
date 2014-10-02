package joinTabler;

import joinTabler.SettingModel;
import joinTabler.SettingView;

import java.io.File;
import java.util.List;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.FileDialog;
import javax.swing.table.DefaultTableModel;

public class SettingController
	extends mvc.Controller
	implements ActionListener, WindowListener
{
	private JFrame window;

    public SettingController(SettingModel aModel, SettingView aView)
	{
		super();
		this.model = aModel;
		this.view = aView;
		this.window = null;
		this.setListener();
    }

	public void setWindow(JFrame aWindow)
	{
		this.window = aWindow;
	}
	
	public SettingModel getModel()
	{
		return (SettingModel) (this.model);
	}
	
	public SettingView getView()
	{
		return (SettingView) (this.view);
	}


	public void setListener()
	{
		for(JButton aButton : this.getView().getSaveButtons()) { aButton.addActionListener(this); }
		for(JButton aButton : this.getView().getAddButtons()) { aButton.addActionListener(this); }
	}
    
    /**
     * JoinボタンまたはResetボタンを押したとき、受け取る。
     * @param anEvent クリック情報
     */
    public void actionPerformed(ActionEvent anEvent)
	{
        JButton inButton = (JButton) anEvent.getSource();
		List<JButton> saveButtons = this.getView().getSaveButtons();
		List<JButton> addButtons = this.getView().getAddButtons();
		List<DefaultTableModel> tableModels = this.getModel().getTableModels();

		for(int index = 0; index < tableModels.size(); index++)
		{
			/* SAVE */
			if(inButton == saveButtons.get(index))
			{
				this.getModel().saveToDestinationFile();
			}
			/* ADD */
			else if (inButton == addButtons.get(index))
			{
				String[] strs = {"",""};
				tableModels.get(index).addRow(strs);
			}
		}
    }

	public void windowClosing(WindowEvent e) {}
	public void windowClosed(WindowEvent e)
	{
		this.getModel().saveToDestinationFile();
	}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

}

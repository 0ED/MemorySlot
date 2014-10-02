package joinTabler;

import joinTabler.Const;
import joinTabler.SettingController;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Graphics;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class SettingView extends mvc.View
{
	private JTabbedPane tabbedPane;
	private JPanel[] panels;
	private JTable tableArea;
	private JScrollPane scrollArea;
	private List<JButton> saveButtons;
	private List<JButton> addButtons;

    /**
     * 
     */
    public SettingView(SettingModel aModel)
	{
		super(aModel);
		this.saveButtons = new ArrayList<JButton>();
		this.addButtons = new ArrayList<JButton>();
		
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.setLocation(
				aModel.centerX - aModel.tabbedWidth/2,
				aModel.centerY - aModel.tabbedHeight/2
		);
		this.tabbedPane.setSize(aModel.tabbedWidth, aModel.tabbedHeight);
		this.setLayout(null);
		this.createTable(aModel);
		this.add(this.tabbedPane);
    }

	/*
	 * 
	 */
	private void createTable(SettingModel aModel)
	{
		List<DefaultTableModel> tableModels = this.getModel().getTableModels();
		this.panels = new JPanel[tableModels.size()];

		for(int index = 0; index < tableModels.size(); index++)
		{
			this.panels[index] = new JPanel();
			this.panels[index].setLayout(null);
			this.panels[index].setLocation(0,0);
			this.panels[index].setSize(aModel.tabbedWidth, aModel.tabbedHeight);

			this.tableArea = new JTable(tableModels.get(index));
			Font aFont = new Font(Font.MONOSPACED,Font.PLAIN,16);
			this.tableArea.getTableHeader().setFont(aFont);
			this.tableArea.setFont(aFont);
			this.tableArea.setBackground(new Color(255, 255, 255));
			this.tableArea.setGridColor(new Color(180,180,180));

			this.scrollArea = new JScrollPane(this.tableArea);
			this.scrollArea.setLocation(0,0);
			this.scrollArea.setSize(aModel.scrollWidth-20,aModel.scrollHeight);
			this.panels[index].add(this.scrollArea);

			// Here!!
			JButton aSaveButton = new JButton("Save");
			JButton anAddButton = new JButton("Add");
			aSaveButton.setLocation(
				aModel.tabbedWidth/2 - aModel.buttonWidth - 5 - 12,
				aModel.tabbedHeight/2 + aModel.scrollHeight/2 - 40
			);
			anAddButton.setLocation(
				aModel.tabbedWidth/2  + 5 - 12,
				aModel.tabbedHeight/2 + aModel.scrollHeight/2 - 40
			);
			aSaveButton.setSize(aModel.buttonWidth,aModel.buttonHeight);
			anAddButton.setSize(aModel.buttonWidth,aModel.buttonHeight);
			this.panels[index].add(aSaveButton);
			this.panels[index].add(anAddButton);
			this.tabbedPane.addTab("tab"+index,this.panels[index]); // Name of Tab
			this.saveButtons.add(aSaveButton);
			this.addButtons.add(anAddButton);
		}
	}


	public JTable getTableArea()
	{
		return this.tableArea;
	}

	public JScrollPane getScrollArea()
	{
		return this.scrollArea;
	}

	public List<JButton> getSaveButtons()
	{
		return this.saveButtons;
	}

	public List<JButton> getAddButtons()
	{
		return this.addButtons;
	}

	public SettingModel getModel()
	{
		return (SettingModel) (this.model);
	}

	public void paintComponent(Graphics aGraphics)
	{
		int width = this.getWidth();
		int height = this.getHeight();
		super.paintComponent(aGraphics);
		aGraphics.setColor(new Color(0,180,0));	
		aGraphics.fillRect(0, 0, width, height);
	}
}

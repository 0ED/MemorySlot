package joinTabler;

import mvc.View;
import joinTabler.Const;
import joinTabler.JoinTablerModel;
import joinTabler.JoinTablerController;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Graphics;
import javax.swing.ScrollPaneLayout;

public class JoinTablerView extends View
{
	private JPanel dragArea;
	private JScrollPane scrollArea;
	private JButton joinButton;
	private JButton resetButton;
	private int buttonWidth;
	private int buttonHeight;
	private int scrollWidth;
	private int scrollHeight;
	private int centerWidth;
	private int centerHeight;

    /**
     * 
     */
    public JoinTablerView(JoinTablerModel aModel)
	{
		super(aModel);
		this.setLayout(null);
		this.joinButton = null;
		this.resetButton = null;
		this.setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT);
		this.buttonWidth = (int)(this.getWidth()*0.45);
		this.buttonHeight = (int)(this.getHeight()*0.11);
		this.scrollWidth = (int)(this.getWidth()*0.8);
		this.scrollHeight = (int)(this.getHeight()*0.7);
		this.centerWidth = this.getWidth()/2;
		this.centerHeight = this.getHeight()/2;
		this.createDragArea();
		this.createButtons();
    }

	private void createDragArea()
	{
		this.dragArea = new JPanel();
		this.dragArea.setBackground(new Color(255, 255, 255));
		this.dragArea.setLayout(new BoxLayout(this.dragArea, BoxLayout.PAGE_AXIS));
		this.scrollArea = new JScrollPane(dragArea);
		this.scrollArea.setLocation(
			this.centerWidth - this.scrollWidth/2,
			this.centerHeight - this.scrollHeight/2 - 30
		);
		this.scrollArea.setSize(this.scrollWidth,this.scrollHeight);
        this.add(this.scrollArea);
	}

	private void createButtons()
	{
		this.joinButton = new JButton("Join");
		this.resetButton = new JButton("Reset");
		

		this.joinButton.setLocation(
			this.centerWidth - this.buttonWidth - 5,
			this.centerHeight + this.scrollHeight/2 - 20
		);
		this.resetButton.setLocation(
			this.centerWidth + 5,
			this.centerHeight + this.scrollHeight/2 - 20
		);
		this.joinButton.setSize(buttonWidth,buttonHeight);
		this.resetButton.setSize(buttonWidth,buttonHeight);

        this.add(this.joinButton);
        this.add(this.resetButton);
	}


	public JPanel getDragArea()
	{
		return this.dragArea;
	}

	public JScrollPane getScrollArea()
	{
		return this.scrollArea;
	}

	public JButton getJoinButton()
	{
		return this.joinButton;
	}

	public JButton getResetButton()
	{
		return this.resetButton;
	}
	public void paintComponent(Graphics aGraphics)
	{
		int width = this.getWidth();
		int height = this.getHeight();
		super.paintComponent(aGraphics);
		int num = 200;
		aGraphics.setColor(new Color(180,0,0));	
		aGraphics.fillRect(0, 0, width, height);
	}
}

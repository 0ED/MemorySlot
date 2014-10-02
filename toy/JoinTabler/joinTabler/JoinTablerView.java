package joinTabler;

import joinTabler.Const;
import joinTabler.JoinTablerModel;
import joinTabler.JoinTablerController;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.net.URL;

public class JoinTablerView extends mvc.View
{
	private JPanel dragArea;
	private JScrollPane scrollArea;
	private JButton joinButton;
	private JButton resetButton;
	protected JLabel dropImageLabel;

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
		this.createDragArea();
		this.createButtons();
    }

	private void createDragArea()
	{
		JoinTablerModel aModel = this.getModel();

		ImageIcon anIcon = new ImageIcon(aModel.dropImageURLBefore);
		this.dropImageLabel = new JLabel(anIcon);
		this.dropImageLabel.setLocation(aModel.dropImageX, aModel.dropImageY);
		this.dropImageLabel.setSize(anIcon.getIconWidth(), anIcon.getIconHeight());

		this.dragArea = new JPanel();
		this.dragArea.setBackground(new Color(255, 255, 255));
		this.dragArea.setLayout(null);
		this.dragArea.add(this.dropImageLabel);

		this.scrollArea = new JScrollPane(dragArea);
		this.scrollArea.setLocation(
			aModel.centerX - aModel.scrollWidth/2,
			aModel.centerY - aModel.scrollHeight/2 - 30
		);
		this.scrollArea.setBackground(new Color(255, 255, 255));
		this.scrollArea.setSize(aModel.scrollWidth,aModel.scrollHeight);
        this.add(this.scrollArea);
	}

	private void createButtons()
	{
		JoinTablerModel aModel = this.getModel();

		this.joinButton = new JButton("Join");
		this.resetButton = new JButton("Reset");
		this.joinButton.setLocation(
			aModel.centerX - aModel.buttonWidth - 5,
			aModel.centerY + aModel.scrollHeight/2 - 20
		);
		this.resetButton.setLocation(
			aModel.centerX + 5,
			aModel.centerY + aModel.scrollHeight/2 - 20
		);
		this.joinButton.setSize(aModel.buttonWidth,aModel.buttonHeight);
		this.resetButton.setSize(aModel.buttonWidth,aModel.buttonHeight);

        this.add(this.joinButton);
        this.add(this.resetButton);
	}

	public JoinTablerModel getModel()
	{
		return (JoinTablerModel) (this.model);
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
		super.paintComponent(aGraphics);

		int width = this.getWidth();
		int height = this.getHeight();
		aGraphics.setColor(new Color(180,0,0));	
		aGraphics.fillRect(0, 0, width, height);
	}
}

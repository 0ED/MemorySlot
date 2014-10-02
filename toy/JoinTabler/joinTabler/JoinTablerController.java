package joinTabler;

import joinTabler.JoinTablerModel;
import joinTabler.JoinTablerView;

import java.io.File;
import java.util.List;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FileDialog;
import javax.swing.BoxLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;

public class JoinTablerController
	extends mvc.Controller
	implements ActionListener,DropTargetListener
{
	private JFrame window;

    public JoinTablerController(JoinTablerModel aModel, JoinTablerView aView)
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
	
	public JoinTablerModel getModel()
	{
		return (JoinTablerModel) (this.model);
	}
	
	public JoinTablerView getView()
	{
		return (JoinTablerView) (this.view);
	}


	public void setListener()
	{
		this.getView().getJoinButton().addActionListener(this);
		this.getView().getResetButton().addActionListener(this);
		new DropTarget(this.getView().getDragArea(),this); //Here!!
	}
    
    /**
     * JoinボタンまたはResetボタンを押したとき、受け取る。
     * @param anEvent クリック情報
     */
    public void actionPerformed(ActionEvent anEvent)
	{
        JButton aButton = (JButton) anEvent.getSource();
        if(aButton.getText().equals("Join"))
		{
			JFrame aWindow = new JFrame();
			FileDialog aFileDialog = new FileDialog(aWindow,"Choose a file", FileDialog.SAVE);
			aFileDialog.setVisible(true);
			String aFilePath = aFileDialog.getDirectory()+aFileDialog.getFile()+".xls";
			this.getModel().joinTable(aFilePath);
        }
        else if(aButton.getText().equals("Reset")) 
		{
			this.getModel().clearFiles();
			this.getView().getDragArea().removeAll();
			this.changeDropAreaIcon(this.getModel().dropImageURLBefore);
			this.getView().getDragArea().repaint();
		}
    }

	public void drop(DropTargetDropEvent anEvent) 
	{
		Transferable aTrans = anEvent.getTransferable();
		if (aTrans.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) //ファイルでないとき
		{
			anEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			try 
			{

				@SuppressWarnings("unchecked")
				List<File> files = (List<File>)aTrans.getTransferData(DataFlavor.javaFileListFlavor);

				for (File aFile : files) {
					if (! aFile.getName().endsWith(".xls")) // Excelファイルでないとき
					{
						System.out.println("Don't XLS File: "+aFile.getName()+" Nothing!");
						this.changeDropAreaIcon(this.getModel().dropImageURLBefore);
						return;
					}
					else {
						System.out.println("It's XLS File: "+aFile.getName());
					}
				}

				JPanel aDragArea = this.getView().getDragArea();
				aDragArea.remove(this.getView().dropImageLabel);
				aDragArea.repaint();
				aDragArea.setLayout(new BoxLayout(aDragArea, BoxLayout.PAGE_AXIS));

				for (File aFile : files) 
				{
					String aFileName = aFile.getName();
					this.getModel().setFile(aFile); //XLSファイルを登録する
					this.getView().getDragArea().add(new JLabel(aFile.getName()));
				}
				this.window.setVisible(true); //これで更新する
			}
			catch (UnsupportedFlavorException e) {
				e.printStackTrace();			
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void changeDropAreaIcon(URL aURL)
	{
		if (!this.getModel().files.isEmpty()) { return; } //あればなにもしない
		JoinTablerModel aModel = this.getModel();
		JoinTablerView aView = this.getView();
		JPanel aDragArea = aView.getDragArea();
		aDragArea.remove(aView.dropImageLabel);
		
		ImageIcon anIcon = new ImageIcon(aURL);
		aView.dropImageLabel = new JLabel(anIcon);
		aView.dropImageLabel.setLocation(aModel.dropImageX,aModel.dropImageY);
		aView.dropImageLabel.setSize(anIcon.getIconWidth(),anIcon.getIconHeight());
		aDragArea.add(aView.dropImageLabel);
		aDragArea.repaint();
	}

	public void dragEnter(DropTargetDragEvent anEvent)
	{
		this.changeDropAreaIcon(this.getModel().dropImageURLAfter);
	}

	public void dragExit(DropTargetEvent anEvent)
	{
		this.changeDropAreaIcon(this.getModel().dropImageURLBefore);
	}

	public void dragOver(DropTargetDragEvent anEvent) {}
	public void dropActionChanged(DropTargetDragEvent anEvent) {}
}

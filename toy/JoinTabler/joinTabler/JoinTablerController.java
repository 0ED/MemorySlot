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
			//SAVEしたいとき
			FileDialog aFileDialog = new FileDialog(aWindow,"Choose a file", FileDialog.SAVE);
			aFileDialog.setVisible(true);
			String aFilePath = aFileDialog.getDirectory()+aFileDialog.getFile()+".xls";
			this.getModel().joinTable(aFilePath);
        }
        else if(aButton.getText().equals("Reset")) 
		{
			this.getModel().clearFiles();
			this.getView().getDragArea().removeAll();
			this.getView().getDragArea().repaint();
		}
		//System.out.println("コンポーネント数"+this.getView().getJoinButton().getComponentCount());
    }

	public void drop(DropTargetDropEvent anEvent) 
	{
		Transferable aTrans = anEvent.getTransferable();
		if (aTrans.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) 
		{
			anEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			try 
			{
				@SuppressWarnings("unchecked")
				List<File> files = (List<File>)aTrans.getTransferData(DataFlavor.javaFileListFlavor);
				for (File aFile : files) 
				{
					this.getModel().setFile(aFile); //XLSファイルを登録する
					this.getView().getDragArea().add(new JLabel(aFile.getName()));
				}
				//this.getView().repaint();
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

	public void dragEnter(DropTargetDragEvent anEvent) {}
	public void dragExit(DropTargetEvent anEvent) {}
	public void dragOver(DropTargetDragEvent anEvent) {}
	public void dropActionChanged(DropTargetDragEvent anEvent) {}
}

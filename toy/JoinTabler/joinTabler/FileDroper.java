package joinTabler;

import java.io.File;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.io.IOException;

public class FileDroper extends DropTarget
{
	private JPanel dragArea;
	private JScrollPane scrollArea;
	private JFrame frame;

	public FileDroper()
	{
		this.dragArea = null;
		this.scrollArea = null;
	}

	public void setParentPanel(JPanel aPanel, JScrollPane aScrollPane, JFrame aFrame)
	{
		this.dragArea = aPanel;
		this.scrollArea = aScrollPane;
		this.frame = aFrame;
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
				System.out.println(this.dragArea);
				for (File aFile : files) 
				{
					JLabel aLabel = new JLabel(aFile.getName());
					this.dragArea.add(aLabel);
					aLabel.repaint();
					this.frame.repaint();
					this.dragArea.repaint();
					this.scrollArea.repaint();
					System.out.println(this.dragArea.getComponentCount());
					System.out.println(aFile.getName());
				}
			}
			catch (UnsupportedFlavorException e) {
				e.printStackTrace();			
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

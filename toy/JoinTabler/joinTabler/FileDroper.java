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
import java.io.IOException;

public class FileDroper extends DropTarget
{
	private JPanel dropArea;

	public FileDroper()
	{
		this.dropArea = null;
	}

	public void setDropArea(JPanel aDropArea)
	{
		this.dropArea = aDrogArea;
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
				List<File> fileList = (List<File>)aTrans.getTransferData(DataFlavor.javaFileListFlavor);
				for (File file : fileList) 
				{
					System.out.println(file.getAbsolutePath());
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

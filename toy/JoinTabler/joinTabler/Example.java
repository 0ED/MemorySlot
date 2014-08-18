package joinTabler;

import joinTabler.MainFrame;
import joinTabler.JoinTabler;
import joinTabler.PropertiesLoader;

public class Example
{
	public static void main(String[] args) 
	{
        PropertiesLoader aProperty = new PropertiesLoader();
        aProperty.loadDestinationFile();

		JoinTabler aJoinTabler = new JoinTabler();
		aJoinTabler.showTable();

        MainFrame aFrame = new MainFrame();
        aFrame.setVisible(true);
	}
}

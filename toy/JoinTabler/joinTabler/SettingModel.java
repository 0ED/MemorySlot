package joinTabler;

import joinTabler.Const;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

public class SettingModel extends mvc.Model
{
	private BufferedReader reader;
	private BufferedWriter writer;
	private Pattern pattern_info;
	private Pattern pattern_cell_info;
	private List<DefaultTableModel> tableModels;
	private File propertyFile;
	protected int width;
	protected int height;
	protected int buttonWidth;
	protected int buttonHeight;
	protected int scrollWidth;
	protected int scrollHeight;
	protected int tabbedWidth;
	protected int tabbedHeight;
	protected int centerX;
	protected int centerY;

	/*
	 *
	 */
    public SettingModel() 
	{
		this.width = Const.WIN_SETTING_WIDTH;
		this.height = Const.WIN_SETTING_HEIGHT;
		this.buttonWidth = (int)(this.width*0.45);
		this.buttonHeight = (int)(this.height*0.07);
		this.scrollWidth = (int)(this.width*1);
		this.scrollHeight = (int)(this.height*0.7);
		this.tabbedWidth = (int)(this.width*1);
		this.tabbedHeight = (int)(this.height*0.9);
		this.centerX = this.width/2;
		this.centerY = this.height/2;
		this.tableModels = new ArrayList<DefaultTableModel>();
		this.propertyFile = new File("C:\"+Const.PROPERTY_FILE);
		this.pattern_info = Pattern.compile("([A-Z]+\\d+)=([A-Z]+\\d+)");
		this.pattern_cell_info = Pattern.compile("([A-Z]+\\d+)");

		this.loadDestinationFile();
    }
	
	/*
	 *
	 */
	private void loadDestinationFile()
	{
		String line = null;
		InputStream inStream = null;
		String[] columnNames = {"入力先","出力先"};
		DefaultTableModel aTableModel = null;
		BufferedWriter aWriter = null;
		Matcher matcher_info = null;
		boolean isEmptyTable = true;
 
 		//LogFile
		try {
			this.reader = new BufferedReader(new FileReader(this.propertyFile));
		}
		catch(FileNotFoundException anException)
		{
			//ファイルを作る
			try {
				aWriter = new BufferedWriter(new FileWriter(this.propertyFile));
				aWriter.write("");
				aWriter.flush();
				aWriter.close();
			}
			catch(IOException e) { }

			try {
				this.reader = new BufferedReader(new FileReader(this.propertyFile));
			}
			catch(FileNotFoundException e) {}
		}

		//Table
		aTableModel = new DefaultTableModel(columnNames,0);

		try { line = reader.readLine(); }
		catch(IOException e) { this.showErrorDialog("メニューの中の環境設定を設定し直してください．"); }
		while(line != null)
		{
			matcher_info = this.pattern_info.matcher(line);
			if (matcher_info.find())
			{
				String[] strs = new String[2];
				strs[0] = matcher_info.group(1);
				strs[1] = matcher_info.group(2);
				aTableModel.addRow(strs);
			}
			else if (line.equals("end"))
			{
				if (aTableModel.getRowCount() == 0) 
				{
					String[] strs = {"",""};
					aTableModel.addRow(strs);
				}
				isEmptyTable = false;
				this.tableModels.add(aTableModel);
				aTableModel = new DefaultTableModel(columnNames,0);

			}
			
			try { line = this.reader.readLine(); }
			catch(IOException e) { this.showErrorDialog("メニューの中の環境設定を設定し直してください．"); }
		}
		try { this.reader.close(); }
		catch(IOException e) { this.showErrorDialog("メニューの中の環境設定を設定し直してください．"); }


		if (isEmptyTable)
		{
			for(int index = 0; index < 2; index++)
			{
				aTableModel = new DefaultTableModel(columnNames,0);
				String[] strs = {"",""};
				aTableModel.addRow(strs);
				this.tableModels.add(aTableModel);
			}
		}
	}

	/*
	 *
	 */
	public void saveToDestinationFile()
	{
		try {
			this.writer = new BufferedWriter(new FileWriter(this.propertyFile));
		}
		catch(IOException e) {}

		Matcher matcher_left_info = null;
		Matcher matcher_right_info = null;

		for(int index = 0; index < this.tableModels.size(); index++)
		{
			DefaultTableModel aTableModel = this.tableModels.get(index);
			try
			{
				for(int row = 0; row < aTableModel.getRowCount(); row++)
				{
					String left = (String) aTableModel.getValueAt(row,0);
					String right = (String) aTableModel.getValueAt(row,1);

					matcher_left_info = this.pattern_cell_info.matcher(left);
					matcher_right_info = this.pattern_cell_info.matcher(right);
					if (matcher_left_info.find() && matcher_right_info.find()) //両方
					{
						System.out.println(left+"="+right);
						this.writer.write(left+"="+right);
						this.writer.newLine();
					}
					else if (matcher_left_info.find() || matcher_right_info.find()) //どちらか
					{
						this.showErrorDialog("テーブルの中に入力先，出力先が一対一になるように直してください．");
						return;	
					}
				}
				this.writer.write("end");
				this.writer.newLine();
				this.writer.newLine();
			}
			catch(IOException anException) {}
		}

		try {
			this.writer.flush();
			this.writer.close();
		}
		catch(IOException anException) {}
	}

	public List<DefaultTableModel> getTableModels()
	{
		return this.tableModels;
	}

	/*
	 *
	 */
	private void showErrorDialog(String aString)
	{
		JOptionPane.showMessageDialog(null, aString,"おたべさん, 想定内のエラー!",JOptionPane.PLAIN_MESSAGE);
	}
}

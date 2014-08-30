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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.WritableWorkbook;
import jxl.write.biff.RowsExceededException;

public class JoinTablerModel extends mvc.Model
{
	private List<File> files;
	private BufferedReader reader;
	private Pattern pattern_info;
	private Pattern pattern_end;
	private Workbook inWorkbook;
	private WritableWorkbook outWorkbook;

    public JoinTablerModel() 
	{
		
		this.files = new ArrayList<File>();
		this.pattern_info = Pattern.compile("([A-Z]+\\d+)=([A-Z]+\\d+)");
		this.pattern_end = Pattern.compile("end");
		this.loadDestinationFile();
    }
	
	public void setFile(File aFile)
	{
		this.files.add(aFile);
	}

	public void clearFiles()
	{
		this.files.clear();
	}

	private void loadDestinationFile()
	{
		InputStream inStream = null;
		inStream = this.getClass().getClassLoader().getResourceAsStream(Const.PROPERTY_FILE);
		if (inStream != null)
		{
			this.reader = new BufferedReader(new InputStreamReader(inStream));
		}
	}

	/*
	 *
	 */
	private Sheet getInputSheet(File inFile, WorkbookSettings aWorkbookSettings)
	{
		Sheet inSheet = null;

		try {
			this.inWorkbook = Workbook.getWorkbook(inFile, aWorkbookSettings);
		}
		catch (BiffException e) 
		{
			this.showErrorDialog("XLSX形式の可能性があります．");
			//e.printStackTrace();
		} 
		catch (IOException e) 
		{
			this.showErrorDialog("入力ファイル（"+inFile.getName()+"）の読み込み失敗．Resetボタンを押してください．");
			//e.printStackTrace();
		}

		try {
			inSheet = this.inWorkbook.getSheet(0);
		}
		catch (IndexOutOfBoundsException e) 
		{
			this.showErrorDialog("入力ファイル（"+inFile.getName()+"）が空っぽのようです．");
		} 
		return inSheet;
	}


	/*
	 *
	 */
	private WritableSheet getOutputSheet(String outFilePath, WorkbookSettings aWorkbookSettings)
	{
		File outFile = null;
		WritableSheet outSheet = null;

		try 
		{
			outFile = new File(outFilePath);
		}
		catch (NullPointerException e) 
		{
			this.showErrorDialog("出力ファイルを出力できない．出力ファイルの名前を変更してください．");
		}

		try 
		{
			this.outWorkbook = Workbook.createWorkbook(outFile,aWorkbookSettings);
		}
		catch (IOException e) 
		{
			this.showErrorDialog("出力ファイルを出力できない．出力ファイルの名前を変更してください．");
		}
		outSheet = this.outWorkbook.createSheet("おたべ",0);

		return outSheet;
	}


	public void joinTable(String outFilePath)
	{
		File inFile = null;
		Sheet inSheet = null;
		WritableSheet outSheet = null;
		WorkbookSettings aWorkbookSettings = null;

		aWorkbookSettings = new WorkbookSettings();
		aWorkbookSettings.setLocale(new Locale("ja","JP"));
		aWorkbookSettings.setEncoding("Cp1252");
		aWorkbookSettings.setWriteAccess("something");
		aWorkbookSettings.setGCDisabled(true);

		Iterator<File> anIterator = this.files.iterator();
		inFile = anIterator.next();
		outSheet = this.getOutputSheet(outFilePath,aWorkbookSettings);
		inSheet = this.getInputSheet(inFile,aWorkbookSettings);


		String line = null;
		try {
			line = reader.readLine();
		}
		catch (IOException e) {
			this.showErrorDialog("メニューの中の環境設定を設定し直してください．");
		} 

		while(line != null)
		{
			if (! anIterator.hasNext()) { break; }

			Matcher matcher_info = this.pattern_info.matcher(line);
			if (matcher_info.find())
			{
				Cell inCell = null;
				try {
					inCell = inSheet.getCell(matcher_info.group(1));
				}
				catch(ArrayIndexOutOfBoundsException e){
					this.showErrorDialog("空のセルにアクセスしています．メニューの中の環境設定を開いて，設定し直してね．");
					return;
				}
				System.out.println("入力\t"+" 列:"+inCell.getColumn()+" 行:"+inCell.getRow()+" 中身:"+inCell.getContents());

				Cell outCell = outSheet.getCell(matcher_info.group(2));
				Label aWritableCell = new Label(outCell.getColumn(),outCell.getRow(),inCell.getContents());

				try {
					outSheet.addCell(aWritableCell);
				}
				catch (WriteException e) {
					//e.printStackTrace();
				} 
				outCell = outSheet.getCell(matcher_info.group(2));
				System.out.println("出力\t"+" 列:"+outCell.getColumn()+" 行:"+outCell.getRow()+" 中身:"+outCell.getContents());
			}
			else if (line.equals("end")) {
				this.inWorkbook.close();
				inFile = anIterator.next();
				inSheet = this.getInputSheet(inFile,aWorkbookSettings);
			}

			try {
				line = reader.readLine();
			}
			catch (IOException e) {
				this.showErrorDialog("メニューの中の環境設定を設定し直してください．");
			} 
		}


		try {
			this.outWorkbook.write();
		}
		catch (IOException e) {
			//e.printStackTrace();
		}

		try {
			this.outWorkbook.close();
		}
		catch (WriteException e) {
			//e.printStackTrace();
		}
		catch (IOException e) {
			//e.printStackTrace();
		}

	}

	private void showErrorDialog(String aString)
	{
		JOptionPane.showMessageDialog(null, aString,"おたべさん, 想定内のエラー!",JOptionPane.PLAIN_MESSAGE);
	}
}

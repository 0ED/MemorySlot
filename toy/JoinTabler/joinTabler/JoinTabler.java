package joinTabler;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class JoinTabler 
{
	public void showTable() 
	{
		System.out.println("Excelブックを読み込みます.");

		Workbook workbook = null;
		try {
			WorkbookSettings settings = new WorkbookSettings();
			// System.gc()「ガベージコレクション」の実行をOFFに設定
			settings.setGCDisabled(true);
			workbook = Workbook.getWorkbook(new File("input/input.xls"), settings);
		} 
		catch (BiffException e) {
			e.printStackTrace();
			return;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println("Excelシートを読み込みます.");
			Sheet sheet = workbook.getSheet(0);
			if (sheet == null) {
				System.err.println("指定のExcelシート読込に失敗しました.");
				return;
			}

			Cell cell = sheet.getCell(0, 0);
			System.out.println("A列1行の内容は [" + cell.getContents() + "] です.");
		} finally {
			workbook.close();
		}
	}

}

package joinTabler;

import joinTabler.Const;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.lang.ClassLoader;

public class PropertiesLoader
{
	/*
	 *
	 */
	private Properties props;

	/*
	 *
	 */
	public PropertiesLoader()
	{
		this.props = new Properties();
	}

	/*
	 *
	 */
	public void loadDestinationFile()
	{
		InputStream inStream = null;
		try 
		{
			inStream = this.getClass().getClassLoader().getResourceAsStream(Const.PROPERTY_FILE);
			System.out.println(inStream); //デバッグ
			this.props.load(inStream);	
		}
		catch(IOException e) {
			e.printStackTrace(); //入力ストリームからの読み込み中にエラーが発生した場合
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace(); //入力ストリームに不正なUnicodeエスケープシーケンスが含まれる場合
		}
	}
}

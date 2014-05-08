q/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習10
 提出日： 12/13
 ファイル名： FileReadWrite_144704.java
 プログラムの動作説明：
    プログラム実行時に"java FileReadWrite_144704 ファイル名1 ファイル名2 ファイル名3 
    を入力すると
    ファイル名1は入力ファイル（文字コードはUTF-8）
    ファイル名2は出力ファイル（文字コードはEUC_JP）
    ファイル名3は出力ファイル（文字コードはSJIS）
    ができ、ファイル2はファイル1の奇数行、ファイル3はファイル2の偶数行が格納される
 実行結果：
 ===================================
 Task $ java FileReadWrite_144704 serohikino_goshu_utf8.txt EUC_JP.txt SJIS.txt
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 ===================================
 */
import java.io.*;

class FileReadWrite_144704 {
    public static void main(String[] args) throws IOException {
        
        BufferedReader in = null;
        BufferedWriter outOdd = null;
        BufferedWriter outEven = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]),"UTF-8") );
            //FileInputStreamでファイルの生のデータを読む
            //そのファイルをInputStreamReaderで文字コードを変え、inに入れる
            
            outOdd = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]),"EUC_JP") );
            outEven = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[2]),"SJIS") );
            
            String str;
            int count=1; //1行目からファイルは始まる
            while ( (str = in.readLine()) != null ) {
                if(count % 2 == 0) outEven.write(str); //偶数
                else outOdd.write(str); //奇数
                outEven.newLine();
                outOdd.newLine();
                count++;
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
            // do nothing
        } finally {
            in.close();
            outEven.close();
            outOdd.close();
        }
    }
}

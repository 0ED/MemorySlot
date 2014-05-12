/*
 ファイル書き込みプログラム
    out.writeはファイルに書き込む処理
    out.newLineはファイルの新しいラインに動く
    FileWriter(args[0])はファイルに描く
*/

import java.io.*;
class FileWriterExample {
    public static void main(String[] args) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(args[0])); //溜めてから書き込む
            for (int i = 0; i < 100; i++) {
                out.write(i + "は"); 
                if (i % 2 == 0) out.write("2で割り切れる ");
                if (i % 3 == 0) out.write("3で割り切れる ");
                if (i % 5 == 0) out.write("5で割り切れる ");
                out.newLine();
            }
        } catch (IOException ioe) {
            // do nothing
        } finally {
            if (out != null) out.close();
        }
    }
}
/*
 ファイル書き込みプログラム
    out.writeはファイルに書き込む処理
    out.newLineはファイルの新しいラインに動く
    FileWriter(args[0])はファイルに描く
*/

import java.io.*;
class FileWriterExample {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream( new FileOutputStream(args[0]) ); //溜めてから書き込む
            for (int i = 0; i < 100; i++) {
                out.writeInt(i);
            }
        } catch (IOException ioe) {
            // do nothing
        } finally {
            if (out != null) out.close();
        }
        
        int[] data = new int[100];
        DataInputStream in = null;
        try {
            in = new DataInputStream( new FileInputStream(args[0]) ); //溜めてから書き込む
            for (int i = 0; i < 100; i++) {
                data[i] = in.readInt();
                System.out.println(data[i]);
            }
        } catch (IOException ioe) {
            // do nothing
        } finally {
            if (out != null) out.close();
        }
    }
}
import java.io.*;

class FileReaderExample {
    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(args[0]) );
            String str;
            int count=0;
            while ( (str = in.readLine()) != null ) { //1行分読む
                System.out.println(count + ": " + str);
                count++;
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
            // do nothing
        } finally {
            if (in != null) in.close();
        }
    }
}

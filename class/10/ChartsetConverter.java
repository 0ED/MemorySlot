import java.io.*;

class ChartsetConvert {
    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
//            in = new BufferedReader(new FileReader(args[0]) );
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), “SJIS”));
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

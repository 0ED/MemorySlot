import java.io.*;

class RubyCutter {
    public static void main(String[] args) throws IOException{
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new BufferedWriter(new FileWriter(args[1]));
            String str;
            while( (str = in.readLine()) != null ) {
                str = str.replaceAll("《.*?》", "");
                str = str.replaceAll("｜","");
                out.write(str);
                out.newLine();
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        } finally {
            in.close();
            out.close();
        }
    }
}
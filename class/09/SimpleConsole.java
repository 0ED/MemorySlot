import java.io.*;

public class SimpleConsole{
    private static final SimpleConsole instance = new SimpleConsole();

    private BufferedReader in;

    private SimpleConsole(){
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public static BufferedReader console(){
        return instance.in;
    }

    public static void main(String[] args) throws IOException{
        String line;
        System.out.println("quitで終了．入力した文字列をそのまま出力するプログラム．");
        System.out.print("> ");
        while((line = SimpleConsole.console().readLine()) != null){
            if(line.trim().equals("quit")){
                break;
            }
            System.out.println(">> " + line);
            System.out.print("> ");
        }
    }
}
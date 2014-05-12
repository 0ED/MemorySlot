import java.io.*;
   
public class Example0803{
   public static void main(String arg[]) throws IOException, NumberFormatException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("整数を入力してください。");

      String str = br.readLine();
      int num = Integer.parseInt(str);
   
      System.out.println("入力された数値は"+num+"です。");
   }
}
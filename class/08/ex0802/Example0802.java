import java.io.*;

public class Example0802{
   public static void main(String arg[]){

      try{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         System.out.println("整数を入力してください。");

         String str2 = br.readLine();
         int num = Integer.parseInt(str2);

         System.out.println("入力された数値は"+num+"です。");
      }
      catch(NumberFormatException e){
      	  System.out.println("例外" + e +"が起きました。");
      }
      catch(IOException e){
      	  System.out.println("例外" + e +"が起きました。");
      }
      finally{
      	  System.out.println("最後にこの文を表示します");
      }
   }
}
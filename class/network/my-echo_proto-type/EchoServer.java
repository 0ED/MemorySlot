/**
 
 プログラムの説明：
    サーバの機能を持ったプログラム。
    具体的には、クライアント側の入力の文字に対してサーバ側がその文字を返す。
*/



import java.net.*;
import java.io.*;

class EchoServer {
    
    ServerSocket server;
    void listen() {
        
        //フィールド
        //Socket[] socket = new Socket[1000];
        //EchoClientHandler handler = null;
        Socket socket;
        EchoClientHandler[] handler = new EchoClientHandler[100];
        int count=0;
        
        try{
            //ポートを起動し、サーバを立ち上げる
            server = new ServerSocket(18080);
            System.out.println("Echoサーバをポート18080で起動しました。");
            
            while(true) {
                //クライアントが接続してくるまで、待機
                socket = server.accept();
                System.out.println("クライアントが接続してきました.");
                handler[count] = new EchoClientHandler(socket);
                handler[count].start();
                count++;
            }
            
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            handler[count].close();
        }
    }
    
    public static void main (String[] args) {
        EchoServer echo = new EchoServer();
        echo.listen();
    }
}
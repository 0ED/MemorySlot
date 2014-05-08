/**
 
 プログラムの説明：
    サーバの機能を持ったプログラム。
    具体的には、クライアント側の入力の文字に対してサーバ側がその文字を返す。
*/



import java.net.*;
import java.io.*;

class ChatServer {
    
    ServerSocket server;
    void listen() {
        
        //フィールド
        Socket socket;
        ChatClient handler = null;
        int count=0;
        
        try{
            //ポートを起動し、サーバを立ち上げる
            server = new ServerSocket(18080);
            System.out.println("Echoサーバをポート18080で起動しました。");
            
            while(true) {
                //クライアントが接続してくるまで、待機
                socket = server.accept();
                System.out.println("クライアントが接続してきました.");
                handler = new ChatClient(socket,count);
                handler.start();
                count++;
            }
            
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            handler.close();
        }
    }
    
    public static void main (String[] args) {
        ChatServer echo = new ChatServer();
        echo.listen();
    }
}
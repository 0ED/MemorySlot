/* 詳細はChatClientにて */
import java.net.*;
import java.io.*;

class ChatServer {
    
    ServerSocket server;
    void listen() {
        
        //フィールド
        Socket socket;
        ChatClient handler = null;
        
        try{
            //ポートを起動し、サーバを立ち上げる
            server = new ServerSocket(18080);
            System.out.println("Echoサーバをポート18080で起動しました。");
            
            while(true) {
                //クライアントが接続してくるまで、待機
                socket = server.accept();
                System.out.println("クライアントが接続してきました.");
                handler = new ChatClient(socket);
                handler.start();
            }
            
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            handler.close();
        }
    }
    
    public static void main (String[] args) {
        ChatServer chat = new ChatServer();
        chat.listen();
    }
}
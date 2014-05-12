import java.net.*;
import java.io.*;

class EchoServer{
    ServerSocket server;

    void listen(){
        Socket socket = null;
        EchoClientHandler handler = null;
        try{
            server = new ServerSocket(18080);
            System.out.println("Echoサーバをポート18080で起動しました．");
            while(true){
                socket = server.accept();
                System.out.println("クライアントが接続してきました．");

                handler = new EchoClientHandler(socket);
                handler.start();
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        EchoServer echo = new EchoServer();
        echo.listen();
    }
}
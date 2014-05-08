import java.io.*;
import java.net.*;

public class EchoClientHandler extends Thread{
    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    EchoClientHandler(Socket socket){
        this.socket = socket;

        InetAddress address = socket.getInetAddress();
        System.out.println(address);
    }

    public void run(){
        try{
            open();
            String message = receive();
            send(message);
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }

    /**
     * クライアントとのデータのやり取りを行うストリームを開くメソッド．
     */
    void open() throws IOException{
        InputStream socketIn = socket.getInputStream();
        OutputStream socketOut = socket.getOutputStream();
        in = new BufferedReader(new InputStreamReader(socketIn)); 
        out = new BufferedWriter(new OutputStreamWriter(socketOut));
    }

    /**
     * クライアントからデータを受け取るメソッド．
     */
    String receive() throws IOException{
        String line = in.readLine();
        return line;
    }

    /**
     * クライアントへデータを送るメソッド．
     */
    void send(String message) throws IOException{
        out.write(message);
        out.write("\r\n");
    }

    /**
     * クライアントとの接続を閉じるメソッド．
     */
    void close(){
        if(in != null){
            try{ in.close(); } catch(IOException e){ }
        }
        if(out != null){
            try{ out.close(); } catch(IOException e){ }
        }
        if(socket != null){
            try{ socket.close(); } catch(IOException e){ }
        }
    }
}

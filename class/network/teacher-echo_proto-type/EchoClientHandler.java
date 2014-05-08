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
            // open を行う．
            open();

            while(true){
                // データを受け取る．
                String line = receive();
                if(line.equals("")){
                    break;
                }
                // データを送り返す．
                send(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }

    void open() throws IOException{
        InputStream socketIn =
            socket.getInputStream();
        OutputStream socketOut =
            socket.getOutputStream();
        in = new BufferedReader(
            new InputStreamReader(socketIn)
        );
        out = new BufferedWriter(
            new OutputStreamWriter(socketOut)
        );
    }

    String receive() throws IOException{
        String line = in.readLine();
        return line;
    }

    void send(String message) throws IOException{
        System.out.println(message);
        out.write(message);
        out.write("\r\n");
        out.flush();
    }

    void close(){
        if(in != null){
            try{
                in.close();
            } catch(IOException e){ }
        }
        if(out != null){
            try{
                out.close();
            } catch(IOException e){ }
        }
        if(socket != null){
            try{
                socket.close();
            } catch(IOException e){ }
        }
    }
}
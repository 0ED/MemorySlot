import java.io.*;
import java.net.*;
class EchoClientHandler extends Thread{
    Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    
    
    EchoClientHandler(Socket socket) {
        this.socket = socket;
        
        //IPアドレスを取得して出力
        InetAddress address = socket.getInetAddress();
        System.out.println(address);
    }
    
    
    public void run(){
        try{
            open();
            while(true) {
                String line = receive();
                if(line.equals("")) break; //何も入力せずenterを押したら
                send(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }
    
    
    void open() throws IOException{
        
        //クライアント→サーバ側（データ転送）
        InputStream socketIn = socket.getInputStream(); //通信ソケットから受信するバイトストリームを取得
        in = new BufferedReader(new InputStreamReader(socketIn));
        //InputStreamReaderによって、一文字ずつ読み込んだ「バイトストリーム（socketIn）」から「文字ストリーム」に変換
        //BufferedReaderによって、一文字ずつ読み込まれた「文字ストリーム」をまとめて読み込む
        
        //サーバ側→クライアント（データ転送）
        OutputStream socketOut = socket.getOutputStream();
        out = new BufferedWriter(new OutputStreamWriter(socketOut));
    }
    
    String receive() throws IOException {
        String line = in.readLine();
        return line;
    }
    
    void send(String line) throws IOException {
        System.out.println("サーバ: "+line);
        out.write("> "+line);
        out.write("\r\n");
        out.flush();
    }
    
    void close() {
        if(in != null) try {in.close();} catch(IOException e) {}
        if(out != null) try{out.close();} catch(IOException e) {}
        if(socket != null) try{socket.close();} catch(IOException e) {}
    }
}
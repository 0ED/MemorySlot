import java.io.*;
import java.net.*;
class ChatClient extends Thread{
    Socket socket;
    private int count;
    private BufferedReader in;
    private static BufferedWriter[] out = new BufferedWriter[100];
    
    
    ChatClient(Socket socket, int count) {
        this.socket = socket;
        this.count = count;
        
        //IPアドレスを取得して出力
        InetAddress address = socket.getInetAddress();
        System.out.println(address);
    }
    
    
    public void run(){
        try{
            open();
            while(true) {
                String line = receive();
                
                if(line.equals("quit")) {
                    break; //何も入力しなかった場合とユーザが抜けた場合
                }
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
        out[count] = new BufferedWriter(new OutputStreamWriter(socketOut));
    }
    
    String receive() throws IOException {
        String line = in.readLine(); 
        return line;
    }
    
    void send(String line) throws IOException {
        System.out.println("usrs"+count+"> "+line); //サーバ側に表示
        int i=0;
        while(out[i] != null) {
            if(i != count) { //自分のソケットでないとき
                out[i].write("usrs"+count+"> "+line);
                out[i].write("\r\n");
                out[i].flush();
            }
            i++;
        }
    }
    
    void close() {
        int i=0;
    
        if(in != null) try {in.close();} catch(IOException e) {}
        if(out[count] != null) try{out[count].close();} catch(IOException e) {}
        if(socket != null) try{socket.close();} catch(IOException e) {}
    }
}
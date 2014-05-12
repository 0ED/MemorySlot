/*
 学生証番号：144704
 氏名：高橋 右
 
 補足：
    12回目のPDFに、最初pdf,src,binを出すように指示されており、
    次に更新されたときにはsrc,binを出すように指示されていた。
    しかし、不安であったため、144704.PDFを一応出しておきます。
 
 プログラムの動作説明：
    複数クライアントでチャットを行うチャットクライアントサーバプログラム。
    まずサーバを起動し、その後別のターミナルコマンドで「telnet localhost(IPアドレス) 18080」
    を入力し、サーバに接続。（これがクライアントとなる）
    いくつかクライアントを用意すると、自分以外のクライアントに
    自分が入力した文字列を表示させることができる。また、クライアントquitを入力すると
    正常に終了することができる。
 
 プログラムの構成：
    ChatClient.java //クライアント
    ChatServer.java //サーバ
 
 このプログラムの特徴：
    ここは144704.pdfのPDFにて
*/
import java.io.*;
import java.net.*;
import java.util.*;
class ChatClient extends Thread{
    Socket socket;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private static List listOut = new ArrayList();
    
    ChatClient(Socket socket) {
        this.socket = socket;
        
        //IPアドレスを取得してサーバ側に出力
        InetAddress address = socket.getInetAddress();
        System.out.println("IP address -> "+address);
    }
    
    
    public void run(){
        try{
            open();
            printList(); //チャットしているユーザを出力（コメントアウトしてもらってもかまいません）
            while(true) {
                String line = receive();
                
                if(line.equals("quit")) {
                    listOut.remove(out); //自分の出力フォーマットをリスト中から削除
                    printList(); //チャットしているユーザを出力（コメントアウトしてもらってもかまいません）
                    break;
                }
                send(line);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }
    
    
    
    /* 
        チャットしているユーザがどれだけいてて、
        自分のユーザがどれかを出力するメソッド（ここはオリジナル）
    */
    void printList() throws IOException{
        /*
            リストを削除すると、リストの容量を削減するため、
            1つ分リストを詰める。これがArrayListの特徴であるが、
            たとえば、
            「0.ゴリラ、1.ラッパ、2.パリラ」とあった場合、ラッパを削除すると
            「0.ゴリラ、1.パリラ」となる。
            そのため、1のラッパが、パリラに変更されてしまう。このままだと、
            ゴリラからすれば、ラッパとパリラの区別が出来なくなるので、
            telnetでクライアントが増えたとき、
            quitでクライアントが減ったとき、
            誰がサーバに繋いでいるのかを表示する。
         */
        int index = listOut.indexOf(out); //自分の番号
        
        for(int i = 0; i < listOut.size(); i++) {
            BufferedWriter outs = (BufferedWriter)listOut.get(i);
            outs.write("--- user=0~"+(listOut.size()-1)+" ");
            if(index == i) outs.write("me="+index);
            else outs.write("me="+i);
            outs.write(" ---\r\n");
            outs.flush();
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
        listOut.add(out); //リストに自分の出力フォーマットを追加
    }
    
    
    
    String receive() throws IOException {
        String line = in.readLine(); 
        return line;
    }
    
    
    
    void send(String line) throws IOException {
        
        int index = listOut.indexOf(out); //自分のクライアントの配列番号
        System.out.println("user"+index+"> "+line); //サーバ側に表示
        
        for(int i = 0; i < listOut.size(); i++){
            BufferedWriter outs = (BufferedWriter)listOut.get(i);
            if(outs != out) { //他人への出力
                outs.write("user"+index+"> "+line);
                outs.write("\r\n");
                outs.flush();
            }
        }
    }
    
    
    void close() { //Null処理
        if(in != null) try {in.close();} catch(IOException e) {}
        if(out != null) try{out.close();} catch(IOException e) {}
        if(socket != null) try{socket.close();} catch(IOException e) {}
    }
}
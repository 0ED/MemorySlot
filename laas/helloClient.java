import java.rmi.*;

public class helloClient {
  public static void main(String args[]) {
    helloIf hello = null;
    try {
      // セキュリティマネージャの設定
      System.setSecurityManager(new RMISecurityManager());

      // レジストリサーバの検索
      hello = (helloIf)Naming.lookup("rmi://localhost/hello");

    } catch(Exception ex) {
      ex.printStackTrace();
    }

    try {
      // メッセージの取得
      System.out.println(hello.helloMsg());

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}

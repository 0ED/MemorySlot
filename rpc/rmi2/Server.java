import java.rmi.*;

public class Server {
  public static void main(String args[]) {
    HelloImpl hello = null;
    try {
      // セキュリティマネージャの設定
      System.setSecurityManager(new RMISecurityManager());

      // リモートオブジェクトの作成
      hello = new HelloImpl();

      // レジストリサーバに登録（再登録）
      Naming.bind("//localhost/HelloImpl", hello);
//    Naming.rebind("//localhost/hello", hello);

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
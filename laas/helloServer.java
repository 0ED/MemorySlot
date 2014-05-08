import java.rmi.*;

public class helloServer {
  public static void main(String args[]) {
    helloObj hello = null;
    try {
      // セキュリティマネージャの設定
      System.setSecurityManager(new RMISecurityManager());

      // リモートオブジェクトの作成
      hello = new helloObj();

      // レジストリサーバに登録（再登録）
      Naming.bind("//localhost/hello", hello);
//    Naming.rebind("//localhost/hello", hello);

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}

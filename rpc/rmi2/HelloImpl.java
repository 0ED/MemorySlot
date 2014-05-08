import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    /**
     * 注記：スーパークラスのUnicastRemoteObjectのコンストラクタにて
     * RemoteExceptionがスローされるので、本コンストラクタにおいて
     * これをcatchするかthrowするかが必要である。本実装においては、
     * throwを選択している。
     */
    public HelloImpl() throws RemoteException {
    }
    
    public String sayHello() {
        return "Hello, RMI World!";
    }
    
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            Hello hello = new HelloImpl();
            Naming.rebind("HelloObject", hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

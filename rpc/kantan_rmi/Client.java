import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class Client {
    public static void main(String[] args) {
        KantanHello hello = null;
        try {
            System.setSecurityManager(new RMISecurityManager());
            hello = (KantanHello) Naming.lookup("rmi://localhost/KantanHello");
            System.out.println(hello.hello("HelloWorld!"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

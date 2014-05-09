import java.rmi.RMISecurityManager;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        Hello hello;
        try {
            System.setSecurityManager(new RMISecurityManager());

            hello = (Hello)Naming.lookup("rmi://localhost:8000/rmisample");

            System.out.println(hello.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KantanHelloImpl extends UnicastRemoteObject implements KantanHello {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            KantanHelloImpl impl = new KantanHelloImpl();
            Naming.rebind("KantanHello", impl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected KantanHelloImpl() throws RemoteException {
        super();
    }

    public String hello(String arg) throws RemoteException {
        return "こんにちは[" + arg + "]";
    }
}

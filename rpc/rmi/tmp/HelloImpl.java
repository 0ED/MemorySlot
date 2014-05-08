import java.rmi.Remote;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    
    public HelloImpl() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        return "Hello world!";
    }

    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            HelloImpl obj = new HelloImpl();
            StringBuffer url = new StringBuffer();
            url.append("rmi://");
            url.append(args[0]);
            url.append("/HelloServer");
            Naming.rebind(new String(url), obj);
            System.out.println("HelloServer bound in registry");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

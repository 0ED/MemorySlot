import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KantanHello extends Remote {
    String hello(String arg) throws RemoteException;
}

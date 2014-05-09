import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMISample extends Remote {
	public String getMessage() throws RemoteException;
}

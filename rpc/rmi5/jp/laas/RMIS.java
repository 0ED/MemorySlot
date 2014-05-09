import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIS extends Remote {
	public String getMessage() throws RemoteException;
}

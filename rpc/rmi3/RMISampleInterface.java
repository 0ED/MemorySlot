import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMISampleInterface extends Remote {
	public String getMessage() throws RemoteException;
}

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class RMISample extends UnicastRemoteObject implements RMISampleInterface {
	public RMISample() throws RemoteException {
		super();
	}

	public String getMessage() {
		return "こんにちは!! こちらはサーバーです。";
	}
}
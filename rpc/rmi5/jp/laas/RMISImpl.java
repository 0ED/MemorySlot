import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISImpl extends UnicastRemoteObject implements RMIS
{
	public RMISImpl() throws RemoteException {
		super();
	}

	public String getMessage() {
		return "こんにちは!! こちらはサーバーです。";
	}
}

import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMISampleImpl extends UnicastRemoteObject implements RMISample {

	public RMISampleImpl() throws RemoteException {
		super();
		// 事情で UnicastRemoteObeject を拡張できない場合は以下
		// UnicastRemoteObject.exportObject(this);
	}

	public String getMessage() {
		return "こんにちは!! こちらはサーバーです。";
	}

}

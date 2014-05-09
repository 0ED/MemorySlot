import java.net.*;
import java.rmi.*;

public class RMISampleServer 
{
	public static void main(String[] args) 
	{
		try {
			RMISampleImpl rsi = new RMISampleImpl();
			Naming.rebind("rmi://localhost:8000/rmisample", rsi);
			System.out.println("RMISample Server ready.");
		}

		catch (RemoteException re) {
			System.out.println("RemoteException: " + re);
		}

		catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		}

	}
}

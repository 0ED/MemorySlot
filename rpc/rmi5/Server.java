import java.net.*;
import java.rmi.*;

public class Server 
{
	public static void main(String[] args) 
	{
		try {
			RMISImpl aSample = new RMISImpl();
			Naming.rebind("rmi://localhost:8000/rmixxx", aSample);
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

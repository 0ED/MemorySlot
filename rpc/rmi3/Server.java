import java.net.*;
import java.rmi.*;

public class Server 
{
	public static void main(String[] args) 
	{
		try {
			RMISample aSample = new RMISample();
			Naming.rebind("rmi://localhost:8000/rmisample", aSample);
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
import java.net.*;
import java.rmi.*;
import java.util.Map;
import java.util.HashMap;

public class Server 
{
	public static void main(String[] args) 
	{
		try {
			RMISImpl aSample = new RMISImpl();
			//Map aMap = HashMap();
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

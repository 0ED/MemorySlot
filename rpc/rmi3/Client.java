
import java.net.*;
import java.rmi.*;

public class Client 
{
	public static void main(String[] args) 
	{
		try {
			Object obj = Naming.lookup(args[0]);
			RMISample aSample = (RMISample) obj;
			System.out.println(aSample.getMessage());
		}

		catch (RemoteException anException) {
			System.out.println("RemoteException: " + anException);
		}

		catch (MalformedURLException anException) {
			System.out.println("MalformedURLException: " + anException);
		}

		catch (NotBoundException ne) {
			System.out.println("NotBoundException: " + ne);
		}
	}
}

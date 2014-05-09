
import java.net.*;
import java.rmi.*;

public class RMISampleClient 
{
	public static void main(String[] args) 
	{
		try {
			Object obj = Naming.lookup(args[0]);
			RMISample message = (RMISample) obj;
			String msg = message.getMessage();
			System.out.println(msg);
		}

		catch (RemoteException re) {
			System.out.println("RemoteException: " + re);
		}

		catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		}

		catch (NotBoundException ne) {
			System.out.println("NotBoundException: " + ne);
		}
	}
}

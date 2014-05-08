import java.rmi.Remote;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class Client
{
	Client()
	{
		/*
		 * Security.
		 */
		System.setSecurityManager(new RMISecurityManager());

		/*
		 * To connect Server.
		 */
		Remote aRemote = null;
		try {
			aRemote = Naming.lookup("LaasServer");
		}
		catch(MalformedURLException anException) {
			System.out.println("Error in lookup() " + anException.toString()); 
		}
		catch(NotBoundException anException) {
			System.out.println("Error in lookup() " + anException.toString()); 
		}
		catch(RemoteException anException) {
			System.out.println("Connect violation " + anException.toString()); 
		}

		LaasServer aServer = null;
		if (aRemote instanceof LaasServer) { aServer = (LaasServer)aRemote; }

		try { 
			System.out.println("Number is: " + aServer.getTotalRunningYardage("Rudds")); 
		}
		catch (RemoteException anException) { 
			System.out.println("Error in invocation " + anException.toString()); 
		}
	}

	public static void main(String[] arguments) {
		Client aClient = new Client();
	}
}

import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

public class LaasServer extends UnicastRemoteObject implements LaasServerInterface
{
	LaasServer() throws RemoteException
	{
		super();
	}

	public int getTotalRunningYardage(String aStr) throws RemoteException
	{
		if(aStr.equals("Rudds")) { return 52; }
		else { return 18782; }
	}

	public int getTotalPassingYardage(String aStr) throws RemoteException

	{
		if(aStr.equals("Hisoka")) { return 44; }
		else { return 37564; }
	}

	public int getTotalTurnovers(String aStr) throws RemoteException

	{
		if(aStr.equals("PrisonBreak")) { return 66; }
		else { return 3; }
	}

	public static void main(String[] arguments)
	{
		System.setSecurityManager(new RMISecurityManager());

		LaasServer aServer = null;
		try {
			aServer = new LaasServer();
		}
		catch(RemoteException anException) {
			System.out.println("Connect violation " + anException.toString()); 
		}

		try {
			Naming.rebind("LaasServer", aServer);
		}
		catch(MalformedURLException anException) {
			System.out.println("rebind in main() " + anException.toString()); 
		}
		catch(RemoteException anException) {
			System.out.println("rebind in main() " + anException.toString()); 
		}
	}
}

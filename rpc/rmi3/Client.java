import java.net.*;
import java.rmi.*;

public class Client 
{
	public static void main(String[] args) 
	{
		try {
			Object obj = Naming.lookup(args[0]);
			LaasHashMap<String,Integer> aHashMap = (LaasHashMap) obj;
			aHashMap.put("rudds",52);
			System.out.println(aHashMap.get("rudds"));
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

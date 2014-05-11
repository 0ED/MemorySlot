package jp.laas.server;

import jp.laas.LaasHashMap;
import jp.laas.LaasHashMapImpl;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

public class Server 
{
	public static void main(String[] args) 
	{
		try {
			LaasHashMapImpl aHashMap = new LaasHashMapImpl();
			Naming.rebind("rmi://localhost:8000/rmisample", aHashMap);
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

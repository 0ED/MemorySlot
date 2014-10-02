package jp.laas.server;

import jp.laas.LaasHashMap;
import jp.laas.LaasHashMapImpl;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.server.RemoteStub;
import java.rmi.server.UnicastRemoteObject;

public class Server 
{
	public static void main(String[] args) 
	{
		try {
			String nameAddress = args[0]+"/rmisample";
			LaasHashMapImpl aHashMap = new LaasHashMapImpl();
			RemoteStub stub = UnicastRemoteObject.exportObject(aHashMap);
			Naming.rebind(nameAddress, stub);
			System.out.println("RMISample Server ready.");

			System.out.println(nameAddress);
		}

		catch (RemoteException re) {
			System.out.println("RemoteException: " + re);
		}

		catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		}

	}
}

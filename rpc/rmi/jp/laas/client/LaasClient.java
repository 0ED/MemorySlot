package jp.laas.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import jp.laas.LaasHashMap;

public class LaasClient 
{
	public static void main(String[] args) 
	{
		try {
			String nameAddress = args[0]+"/rmisample";
			LaasHashMap<String,Integer> aHashMap = (LaasHashMap) Naming.lookup(nameAddress);
			System.out.println(nameAddress);
			aHashMap.setConstructorByLaas(20);
			aHashMap.put("rudds",52);
			System.out.println(aHashMap.get("rudds"));
		}
		catch (RemoteException anException) { }
		catch (MalformedURLException anException) { }
		catch (NotBoundException anException) { }
	}
}

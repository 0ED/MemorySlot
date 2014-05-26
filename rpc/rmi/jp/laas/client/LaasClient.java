package jp.laas.client;

import jp.laas.LaasHashMap;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LaasClient 
{
	public static void main(String[] args) 
	{
		LaasHashMap<String,Integer> aHashMap = null;
		try {
			aHashMap = (LaasHashMap) Naming.lookup(args[0]);
			aHashMap.setConstructorByLaas(20);
			aHashMap.put("rudds",52);
			System.out.println(aHashMap.get("rudds") + " = aHashMap.get(\"rudds\")");
		}
		catch (RemoteException anException) {
			System.out.println("RemoteException: " + anException);
		}
		catch (MalformedURLException anException) {
			System.out.println("MalformedURLException: " + anException);
		}
		catch (NotBoundException anException) {
			System.out.println("NotBoundException: " + anException);
		}
	}
}

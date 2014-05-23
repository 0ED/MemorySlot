package jp.laas;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.Map;
import java.util.Collection;


public interface LaasHashMap<K,V>
	extends Remote 
{
	/*
	 * コンストラクタ
	 */
	public void setConstructorByLaas(int initialCapacity, float loadFactor) throws RemoteException;

	public void setConstructorByLaas(int initialCapacity) 
		throws RemoteException;

	public void setConstructorByLaas(Map<? extends K, ? extends V> m) 
		throws RemoteException;

	public void setConstructorByLaas() 
		throws RemoteException;

	public void clear() 
		throws RemoteException;

	public boolean containsKey(Object key)
		throws RemoteException;

	public boolean containsValue(Object value) 
		throws RemoteException;
	
	public Set<Map.Entry<K,V>> entrySet()
		throws RemoteException;

	public V get(Object key) 
		throws RemoteException;
		
	public boolean isEmpty() 
		throws RemoteException;

	public Set<K> keySet() 
		throws RemoteException;

	public V put(K key, V value) 
		throws RemoteException;

	public void putAll(Map<? extends K, ? extends V> m) 
		throws RemoteException;

	public V remove(Object key) 
		throws RemoteException;
	
	public int size() 
		throws RemoteException;

	public Collection<V> values()
		throws RemoteException;
}

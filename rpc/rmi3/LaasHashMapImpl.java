import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Collection;

public class LaasHashMapImpl<K,V>
	extends UnicastRemoteObject 
	implements LaasHashMap<K,V>
{
	private Map _hashMap;

	public LaasHashMapImpl(int initialCapacity, float loadFactor) 
		throws RemoteException 
	{
		super();
		this._hashMap = new HashMap(initialCapacity, loadFactor);
	}

	public LaasHashMapImpl(int initialCapacity) 
		throws RemoteException 
	{
		super();
		this._hashMap = new HashMap(initialCapacity);
	}
	
	public LaasHashMapImpl(Map<? extends K, ? extends V> m) 
		throws RemoteException 
	{
		super();
		this._hashMap = new HashMap(m);
	}

	public LaasHashMapImpl() 
		throws RemoteException 
	{
		super();
		this._hashMap = new HashMap();
	}

	public void clear() 
		throws RemoteException 
	{
		this._hashMap.clear();
	}

	public boolean containsKey(Object key)
		throws RemoteException 
	{
		return this._hashMap.containsKey(key);
	}

	public void containsValue(Object value) 
		throws RemoteException 
	{
		this._hashMap.containsValue(value);
	}
	public Set<Map.Entry<K,V>> entrySet()
		throws RemoteException 
	{
		return this._hashMap.entrySet();
	}

	public V get(Object key) 
		throws RemoteException 
	{
		return this._hashMap.get(key);
	}
	public boolean isEmpty() 
		throws RemoteException 
	{
		return this._hashMap.isEmpty();
	}
	public Set<K> keySet() 
		throws RemoteException 
	{
		return this._hashMap.keySet();
	}

	public V put(K key, V value) 
		throws RemoteException 
	{
		return this._hashMap.put(key,value);
	}

	public void putAll(Map<? extends K, ? extends V> m) 
		throws RemoteException 
	{
		this._hashMap.putAll(m);
	}

	public V remove(Object key) 
		throws RemoteException 
	{
		return this._hashMap.remove(key);
	}
	
	public int size() 
		throws RemoteException 
	{
		return this._hashMap.size();
	}
	public Collection<V> values() 
		throws RemoteException 
	{
		return this._hashMap.values();
	}
}

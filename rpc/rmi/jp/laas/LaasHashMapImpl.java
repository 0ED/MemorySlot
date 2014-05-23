package jp.laas;
import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Collection;
import java.rmi.server.RemoteStub;
import java.rmi.server.RemoteObject;
import java.io.Serializable; 

public class LaasHashMapImpl<K,V>
	implements Serializable,LaasHashMap<K,V>
{
	private Map<K,V> _hashMap;
	
	/*
	 * コンストラクタ
	 */
	public LaasHashMapImpl()
		throws RemoteException
	{
		super();
		this._hashMap = new HashMap<K,V>();
	}
	
	public void setConstructorByLaas(int initialCapacity, float loadFactor)
		throws RemoteException
	{
		this._hashMap = new HashMap<K,V>(initialCapacity, loadFactor);
	}
	
	public void setConstructorByLaas(int initialCapacity)
		throws RemoteException
	{
		this._hashMap = new HashMap<K,V>(initialCapacity);
	}
	
	public void setConstructorByLaas(Map<? extends K, ? extends V> m)
		throws RemoteException
	{
		this._hashMap = new HashMap<K,V>(m);
	}
	
	public void setConstructorByLaas()
		throws RemoteException
	{
		this._hashMap = new HashMap<K,V>();
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
	
	public boolean containsValue(Object value)
		throws RemoteException
	{
		return this._hashMap.containsValue(value);
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
	
    /**
     * Compares two remote objects for equality.
     * Returns a boolean that indicates whether this remote object is
     * equivalent to the specified Object. This method is used when a
     * remote object is stored in a hashtable.
     * If the specified Object is not itself an instance of RemoteObject,
     * then this method delegates by returning the result of invoking the
     * <code>equals</code> method of its parameter with this remote object
     * as the argument.
     * @param   obj     the Object to compare with
     * @return  true if these Objects are equal; false otherwise.
     * @see             java.util.Hashtable
     */
	public boolean equals(Object object) 
	{
		if (object instanceof LaasHashMapImpl) {
			return (object == this);
		}
		if (object instanceof RemoteStub) {
			try {
				RemoteStub ourStub = (RemoteStub) RemoteObject.toStub(this);
				return ourStub.equals(object);
			}
			catch(NoSuchObjectException e) {
			}
		}
		return false;
	}

	/**
	 * Returns a hashcode for a remote object.  Two remote object stubs
	 * that refer to the same remote object will have the same hash code
	 * (in order to support remote objects as keys in hash tables).
	 *
	 * @see java.util.Hashtable
	 */
	public int hashCode() 
	{
		try {
			Remote ourStub = RemoteObject.toStub(this);
			return ourStub.hashCode();
		}
		catch(NoSuchObjectException e) {}
		return super.hashCode(); 
	}
}


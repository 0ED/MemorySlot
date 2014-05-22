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
	extends HashMap<K,V>
	implements Serializable,LaasHashMap<K,V>
{

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


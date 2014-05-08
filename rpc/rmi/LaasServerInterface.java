import java.rmi.*;

public interface LaasServerInterface extends Remote
{
	int getTotalRunningYardage(String aStr) throws RemoteException;
	int getTotalPassingYardage(String aStr) throws RemoteException;
	int getTotalTurnovers(String aStr) throws RemoteException;
}

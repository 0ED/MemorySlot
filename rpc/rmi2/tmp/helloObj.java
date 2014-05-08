import java.rmi.*;
import java.rmi.server.*;

public class helloObj extends UnicastRemoteObject implements helloIf {
  public helloObj() throws RemoteException {
  }

  public String helloMsg() throws RemoteException {
    return "Hello World!";
  }
}

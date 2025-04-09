package ACT14.servidor.InterfacesRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {

    String sendMessage (Object message) throws RemoteException;

    String receiveMessage (Object message) throws RemoteException;

}

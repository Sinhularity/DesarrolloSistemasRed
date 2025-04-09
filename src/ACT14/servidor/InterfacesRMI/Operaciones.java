package ACT14.servidor.InterfacesRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {

    void sendMessageToClient (Object message) throws RemoteException;

    String receiveMessageFromClient () throws RemoteException;

    void sendMessageToServer (Object message) throws RemoteException;

    String receiveMessageFromServer () throws RemoteException;

}

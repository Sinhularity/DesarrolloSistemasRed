package ACT16.InterfazRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface InterfazRemota extends Remote {
    void addName (String name) throws RemoteException;
    HashMap<Integer,String> getClientList () throws RemoteException;
    void removeName (String name) throws RemoteException;
}
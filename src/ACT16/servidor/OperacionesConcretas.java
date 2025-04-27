package ACT16.servidor;

import ACT16.InterfazRMI.InterfazRemota;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class OperacionesConcretas  extends UnicastRemoteObject implements InterfazRemota {
    private final HashMap<Integer, String> clientNames = new HashMap<>();

    public OperacionesConcretas() throws RemoteException {
    }

    @Override
    public void addName(String name) throws RemoteException {
        int index = clientNames.size() + 1;
        clientNames.put(index, name);
    }

    @Override
    public HashMap<Integer,String> getClientList() throws RemoteException {
        return clientNames;
    }

    @Override
    public void removeName(String name) throws RemoteException {
        if (clientNames.isEmpty()) {
            System.out.println("No hay nombres en la lista.");
        } else if (!clientNames.containsValue(name)) {
            System.out.println("No hay contactos con ese nombre.");
        } else {
            Integer keyToRemove = null;
            // Get the key associated with the name
            for (var entry : clientNames.entrySet()) {
                if (entry.getValue().equals(name)) {
                    keyToRemove = entry.getKey();
                    break;
                }
            }
            if (keyToRemove != null) {
                clientNames.remove(keyToRemove);
                System.out.println( keyToRemove + ".- Nombre eliminado: " + name);
            }

        }
    }
}

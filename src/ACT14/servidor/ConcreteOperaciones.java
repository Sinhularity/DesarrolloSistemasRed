package ACT14.servidor;

import ACT14.servidor.InterfacesRMI.Operaciones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConcreteOperaciones extends UnicastRemoteObject implements Operaciones {

    protected ConcreteOperaciones() throws RemoteException {
    }

    @Override
    public String sendMessage(Object message) {
        return "Message sent: " + message;
    }

    @Override
    public String receiveMessage(Object message) {
        return "Message received: " + message;
    }
}

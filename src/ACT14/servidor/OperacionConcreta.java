package ACT14.servidor;

import ACT14.servidor.InterfacesRMI.Operaciones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OperacionConcreta extends UnicastRemoteObject implements Operaciones {


    private final ConcurrentLinkedQueue<String> fromClient = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<String> fromServer = new ConcurrentLinkedQueue<>();

    public OperacionConcreta() throws RemoteException {
    }

    // Servidor → Cliente
    @Override
    public void sendMessageToClient(Object message) throws RemoteException {
        fromServer.offer(message.toString());
    }

    // Cliente → Servidor
    @Override
    public void sendMessageToServer(Object message) throws RemoteException {
        fromClient.offer(message.toString());
    }

    // Servidor <- Cliente
    @Override
    public String receiveMessageFromClient() throws RemoteException {
        return fromClient.poll();
    }

    // Cliente ← Servidor
    @Override
    public String receiveMessageFromServer() throws RemoteException {
        return fromServer.poll();
    }

}

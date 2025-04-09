package ACT13.cliente.interfaces_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {

    double sumar(double a, double b) throws RemoteException;

    double restar(double a, double b) throws RemoteException;

    void asignar (double a) throws RemoteException;

    double recuperar () throws RemoteException;
}

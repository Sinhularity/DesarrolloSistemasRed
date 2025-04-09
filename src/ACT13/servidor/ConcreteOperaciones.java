package ACT13.servidor;

import ACT13.servidor.interfaces_rmi.Operaciones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConcreteOperaciones extends UnicastRemoteObject implements Operaciones {
    private double valor;

    public ConcreteOperaciones() throws RemoteException {    }

    /*
    // Marvelous!
    // Outstanding!
    // Brilliant!
    // Remarkable!
    // Magnificent!
    // Superb!
    // Wonderful!
    // Exceptional!
    // Excellent!
    // Fantastic!
    // Incredible!
    // Awesome!
    // Amazing!
    // Great!
    */

    @Override
    public double sumar(double a, double b) throws RemoteException {
        return (a + b) + valor;
    }

    @Override
    public double restar(double a, double b) throws RemoteException {
        return (a - b) + valor;
    }

    @Override
    public void asignar(double a) throws RemoteException {
        this.valor = a;
    }

    @Override
    public double recuperar () throws RemoteException {
        return valor;
    }

}

package ACT13.servidor;

import ACT13.servidor.interfaces_rmi.Operaciones;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMIServidor250408 {

    public static void main(String[] args) {

        try {

            // Crear registro RMI
            Registry registry = LocateRegistry.createRegistry(2099);

            // Crear el objeto remoto
            Operaciones servicio = new ConcreteOperaciones();

            // Registrar el servicio en el registro RMI
            registry.rebind("Operaciones servidor", servicio);

            System.out.println("Servidor RMI iniciado y esperando conexiones...");

        } catch (Exception e) {
            Logger.getLogger(RMIServidor250408.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}

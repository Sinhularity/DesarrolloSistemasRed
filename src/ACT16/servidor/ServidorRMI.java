package ACT16.servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            OperacionesConcretas operaciones = new OperacionesConcretas();
            Registry registry = LocateRegistry.createRegistry(2079);
            registry.rebind("PersonService", operaciones);
            System.out.println("Servidor RMI iniciado en el puerto " + 2079);
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

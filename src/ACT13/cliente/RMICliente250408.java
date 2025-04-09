package ACT13.cliente;

import ACT13.cliente.interfaces_rmi.Operaciones;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMICliente250408 {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2099);

            // Crear el objeto remoto
            Operaciones servicio = (Operaciones) registry.lookup("Operaciones servidor");

            // Realizar operaciones
            double resultadoSuma = servicio.sumar(5, 3);
            double resultadoResta = servicio.restar(5, 3);

            // Mostrar resultados
            System.out.printf("Suma: %.2f \n", resultadoSuma);
            System.out.printf("Resta: %.2f \n", resultadoResta);

            // Asignar y recuperar valor
            servicio.asignar(10);
            System.out.printf("Valor asignado:", servicio.recuperar());
            System.out.println("Sumando: " + servicio.sumar(5, 3));
            System.out.println("Restando: " + servicio.restar(5, 3));

        } catch (Exception e) {
            Logger.getLogger(RMICliente250408.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

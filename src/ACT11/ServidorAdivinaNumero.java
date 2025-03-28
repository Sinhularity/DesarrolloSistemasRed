package ACT11;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorAdivinaNumero {

    private ServerSocket servidor;
    private Vector<ClienteAdivinaNumero> clienteAdivinaNumeros;
    private int numeroAdivinar;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public ServidorAdivinaNumero () {
        try {
            servidor = new ServerSocket(777);
            System.out.println("Servidor iniciado en el puerto 777");
            numeroAdivinar = (int) (Math.random() * 100) + 1;
            System.out.println("Número secreto generado: " + numeroAdivinar);
            Socket socket = servidor.accept();
            System.out.println("Conexión establecida con el cliente: " + socket.getInetAddress());

            while (true) {
                try {

                    int ClientAttemptNumber = entrada.readInt();
                    getClientAttempt(ClientAttemptNumber);

                } catch (IOException e) {
                    System.out.println("Error al leer el número del cliente.");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getClientAttempt (int num) {
        try {
            if (num == numeroAdivinar) {
                salida.writeObject("¡Correcto!");
                salida.flush();
            } else if (numeroAdivinar > num) {
                salida.writeObject("Muy alto");
                salida.flush();
            } else {
                salida.writeObject("Muy bajo");
                salida.flush();
            }
        } catch (IOException e) {
            System.out.println("Error al enviar el mensaje al cliente.");
        }
    }

    public static void main(String[] args) {
        new ServidorAdivinaNumero();
    }
}

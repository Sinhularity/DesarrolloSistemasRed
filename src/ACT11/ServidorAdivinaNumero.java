package ACT11;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorAdivinaNumero {

    private ServerSocket servidor;
    private int numeroAdivinar;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public ServidorAdivinaNumero () {
        try {
            servidor = new ServerSocket(777);
            numeroAdivinar = (int) (Math.random() * 100) + 1;
            Socket socket = servidor.accept();
            System.out.println("Servidor iniciado en el puerto 777");
            System.out.println("Número secreto generado: " + numeroAdivinar);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                try {
                    Object ClientAttemptNumber = entrada.readObject();
                    System.out.println("Intento del cliente: " + ClientAttemptNumber);
                    if ((ClientAttemptNumber != null)) {
                        getClientAttempt((Integer) ClientAttemptNumber);
                    }
                } catch (IOException e) {
                    Logger.getLogger(ServidorAdivinaNumero.class.getName()).log(Level.SEVERE, null, e);
                    break;
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getClientAttempt (int num) {
        try {
            if (num == numeroAdivinar) {
                salida.writeObject("¡Correcto!");
            } else if (numeroAdivinar > num) {
                salida.writeObject("Muy bajo");
            } else {
                salida.writeObject("Muy alto");
            }
            salida.flush();
        } catch (IOException e) {
            System.out.println("Error al enviar el mensaje al cliente.");
        }
    }

    public static void main(String[] args) {
        new ServidorAdivinaNumero();
    }
}

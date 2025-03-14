package ACT08;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Socket250313 {
    public static void main(String[] args) {
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(777);

            System.out.println("Esperando conexión de un cliente...");
            Socket socket = servidor.accept();
            System.out.printf("Conexión con un cliente establecida. %s\n", socket.getInetAddress().toString());

            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

            while (true) {
                try {
                    String msg = entrada.readObject().toString();
                    System.out.println(msg);

                    System.out.println("Escribe un mensaje: ");
                    salida.writeObject(sc.nextLine());
                    salida.flush();

                } catch (ClassNotFoundException e) {
                    socket.close();
                }
            }

        } catch (IOException e) {
            System.out.println("No fue posible iniciar el servidor. " + e.getMessage());
        }
    }
}

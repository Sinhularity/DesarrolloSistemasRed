package ACT08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {

            Socket conexion = new Socket("148.226.203.189", 777);
            System.out.println("Conexión con el servidor establecida.");

//            ObjectInputStream entrada = new ObjectInputStream(conexion.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(conexion.getOutputStream());
//            Scanner sc = new Scanner(System.in);

            salida.writeObject("Hola desde el cliente...");
            salida.flush();

//            while (true) {
//                try {
//                    System.out.println("Escribe un mensaje: ");
//                    salida.writeObject(sc.nextLine());
//                    salida.flush();
//
//                    String msg = entrada.readObject().toString();
//                    System.out.println(msg);
//                } catch (Exception e) {
//                    conexion.close();
//                }
//            }
//                conexion.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

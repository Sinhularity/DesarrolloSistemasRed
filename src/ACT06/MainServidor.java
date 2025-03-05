package ACT06;

import java.io.IOException;

public class MainServidor {
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        System.out.println("Waiting for client...");
        servidor.startServer();
    }
}

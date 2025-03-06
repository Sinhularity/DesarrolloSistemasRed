package ACT06;

import java.io.IOException;

public class MainServidor {
    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.startServer();
    }
}

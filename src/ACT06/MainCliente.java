package ACT06;

import java.io.IOException;

public class MainCliente {
    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        System.out.println("Client initialized...");
        cliente.startClient();
    }
}
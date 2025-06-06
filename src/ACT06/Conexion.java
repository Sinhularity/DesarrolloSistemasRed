package ACT06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
    // Class to be used by Cliente and Servidor
    // Get the connection data

    protected String serverMessage = "";
    protected ServerSocket serverSocket;
    protected Socket clientSocket;
    protected DataOutputStream serverOutput;
    protected DataInputStream serverInput;

    public Conexion (String type) throws IOException {
        int PORT = 2099;
        if (type.equalsIgnoreCase("server")) {
            // Create a server socket in the port 2099
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server connected to:");
            System.out.println(serverSocket.getLocalPort() + " "+
                    serverSocket.getLocalSocketAddress());
            // Wait for a client to connect
            clientSocket = serverSocket.accept();
        } else {
            // Create a socket to connect to the server
            String HOST = "localhost";
            clientSocket = new Socket(HOST, PORT);
            System.out.println("Client connected to:");
            System.out.println(clientSocket.getLocalPort() + " "+
                    clientSocket.getLocalSocketAddress());
        }
    }


}

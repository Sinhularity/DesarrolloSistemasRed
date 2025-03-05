package ACT06;

import java.io.*;

public class Servidor extends Conexion{

    // To use the sockets and Conexion class attributes
    public Servidor () throws IOException {
        super("server");
    }

    public void startServer () {
        try {
            System.out.println("Server initialized...");
            // Create the connection from a client

            serverInput = new DataInputStream(
                    new BufferedInputStream(clientSocket.getInputStream()));

            serverOutput = new DataOutputStream(clientSocket.getOutputStream());

            while (!serverMessage.equals("exit")) {
                try {
                    serverMessage = serverInput.readUTF();
                    System.out.println("Client says: "+serverMessage);

                    serverOutput.writeUTF("Message received");
                    serverOutput.flush();
                } catch (IOException e) {
                    System.out.println("There is a problem with input and output...");
                }
            }

            System.out.println("Closing connection...");
            serverInput.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("There is a problem with input and output...");
        }
    }
}

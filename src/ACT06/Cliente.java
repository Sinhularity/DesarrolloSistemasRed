package ACT06;

import java.io.*;

public class Cliente extends Conexion {

    public Cliente() throws IOException {
        super("client");
    }

    public void startClient () {
        try {
            // Get the server data stream to send data to the server.
            serverOutput = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            serverInput = new DataInputStream(
                    new BufferedInputStream(clientSocket.getInputStream()));

            while (!serverMessage.equals("exit")) {
                try {
                    System.out.println("Enter a message: ");
                    serverMessage = br.readLine();
                    // Send the message to the server.
                    serverOutput.writeUTF(serverMessage);
                    // To make sure the data is sent to the server.
                    serverOutput.flush();

                    // Get the server response.
                    String serverResponse = serverInput.readUTF();
                    System.out.println("Server says: "+serverResponse);
                } catch (IOException e) {
                    System.out.println("No se ha podido enviar el mensaje...");
                }
            }

            try {
                serverOutput.close();
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("No se ha podido cerrar la conexión...");
            }

        } catch (IOException e) {
            System.out.println("No se ha podido enviar el mensaje...");
        }
    }
}

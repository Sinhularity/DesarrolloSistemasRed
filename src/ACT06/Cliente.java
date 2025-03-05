package ACT06;

import java.io.*;

public class Cliente extends Conexion {

    private DataInputStream clientInput;

    public Cliente() throws IOException {
        super("client");
    }

    public void startClient () {
        try {
            // Get the server data stream
            serverOutput = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (!serverMessage.equals("exit")) {
                try {
                    System.out.println("Enter a message: ");
                    serverMessage = br.readLine();
                    serverOutput.writeUTF(serverMessage);
                    serverOutput.flush();
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

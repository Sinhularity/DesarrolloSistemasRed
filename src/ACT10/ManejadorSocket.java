package ACT10;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManejadorSocket extends Thread{

    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private JTextArea jTextArea;

    public ManejadorSocket(Socket socket, JTextArea jTextArea){
        try {
            this.socket = socket;
            salida = new ObjectOutputStream( socket.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socket.getInputStream());
            this.jTextArea = jTextArea;
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run(){
        while(true){
            try {
                String combination = entrada.readObject().toString();
                String result = Incognita.evaluate(combination);
                jTextArea.append(socket.getInetAddress() + " " + result);

                if (!result.isEmpty()) {
                    sendMessage(result);
                }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendMessage(String combination){
        try {
            salida.writeObject(combination);
            salida.flush();
//            jTextArea.append(socket.getInetAddress() + " - Correctos: " + Incognita.coincidentNumbers(combination)  +
//                    ", Posicion correcta: " + Incognita.coincidentPositionNumbers(combination) + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return socket.getInetAddress().toString();
    }
}

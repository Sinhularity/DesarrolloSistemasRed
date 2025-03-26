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
            String message;
            try {
                message = entrada.readObject().toString();
                if (message.startsWith("Combinación:")) {
                    String combination = message.substring(12);
                    String result = Incognita.evaluate(combination);
                    jTextArea.append(socket.getInetAddress()+" - "+result+"\n");
                    if (!result.isEmpty()) {
                        sendMessage("Resultado:" + result);
                    }
                } else if (message.startsWith("Resultado:")) {
                    jTextArea.append(socket.getInetAddress()+" - "+message.substring(10)+"\n");
                } else {
                    jTextArea.append(socket.getInetAddress()+" - "+message+"\n");
                    socket.close();
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
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return socket.getInetAddress().toString();
    }
}

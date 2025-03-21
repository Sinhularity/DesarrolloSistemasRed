package ACT09;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


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
                Object object = entrada.readObject();
                jTextArea.append(socket.getInetAddress() + " - " + object + "\n");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void enviarMensaje(String mensaje){
        try {
            salida.writeObject(mensaje);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

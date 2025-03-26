package ACT09;

import javax.swing.*;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends JFrame {
    private Socket socket;
    private JTextField Mensaje; // Prompt
    private JButton Enviar;
    private JTextArea Resultados;
    private JScrollPane JScrollMensajes;
    private JPanel MainFrame;
    private ManejadorSocket conexion;
    String ip;

    // Generamos una IP en una variable y la agregamos al cuerpo 127.0.0.x
    public String IPAleatoria() {
        Random random = new Random();
        int hostPort = random.nextInt(256);
        return "127.0.0." + hostPort;
    }

    private void initComponents() {
        setContentPane(MainFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cliente");

        // Área de mensajes
        Resultados.setColumns(20);
        Resultados.setRows(5);
        Resultados.setEditable(false);
        JScrollMensajes.setViewportView(Resultados);

        Enviar.addActionListener(e -> sendMessage());

        pack();
        setVisible(true);
    }


    //Constructor que inicia conexión con el server
    public Cliente() {
        initComponents();

        try {
            ip = IPAleatoria();
            socket = new Socket(ip, 777);
            System.out.println("Conectado al servidor: " + socket.getInetAddress());

            conexion = new ManejadorSocket(socket, Resultados);
            conexion.start();
            sendIP();

        } catch (Exception e) {
            Resultados.append("Error al conectar con el servidor.\n");
            e.printStackTrace();
        }
    }

    // Se envia como primer mensaje la dirección IP del cliente
    private void sendIP () {
        try {
            conexion.enviarMensaje(ip);
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMessage() {
        try {
            String msg = Mensaje.getText().trim();
            if (!msg.isEmpty()) {
                // Utiliza ManejadorSocket para enviar mensajes
                conexion.enviarMensaje(ip+" dice "+msg);
                Mensaje.setText("");
            } else {
                Resultados.append("No se puede enviar un mensaje vacío.\n");
            }
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        // Realiza la creación de la interfaz de forma segura (similar a al synchronized, pero sin bloquear la UI)
        // Evita problemas de concurrencia y bloqueos en la interfaz gráfica
        SwingUtilities.invokeLater(Cliente::new);
    }
}
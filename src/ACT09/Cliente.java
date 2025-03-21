package ACT09;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Cliente extends JFrame{

    private JTextField Mensaje;
    private JButton Enviar;
    private JTextArea Mensajes;
    private JScrollPane JScrollMensajes;
    private JPanel MainFrame;


    private ManejadorSocket conexion;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public String IPAleatoria () {
            Random random = new Random();
            String ip = random.nextInt(256) + "." + random.nextInt(256) + "." +
                    random.nextInt(256) + "." + random.nextInt(256);
            return ip;
    }

    public Cliente () {
        initComponents();

        try {
            Socket socket = new Socket(IPAleatoria(), 777);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());


            new Thread(conexion).start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void initComponents() {
        setContentPane(MainFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JScrollMensajes.setViewportView(Mensajes);

        Enviar.addActionListener(e -> {
            sendMenssage();
        });

        pack();
        setVisible(true);
    }

    public void sendMenssage () {
        try {
            String msg = Mensaje.getText();
            if (!msg.isEmpty()) {
                salida.writeObject(msg);
                salida.flush();
                Mensaje.setText("");
            }
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}

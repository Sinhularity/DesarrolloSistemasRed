package ACT11;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteAdivinaNumero extends JFrame{
    private JTextField Prompt;
    private JButton enviarButton;
    private JTextArea Resultados;
    private JScrollPane ResultadosScroll;
    private JPanel MainFrame;

    ObjectOutputStream salida;
    ObjectInputStream entrada;
    private int numberAttempt;

    public void initComponents ( ) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainFrame);
        this.setResizable(false);
        this.setVisible(true);

        ResultadosScroll.setViewportView(Resultados);
        Resultados.setEditable(false);


        enviarButton.addActionListener(e -> {
            getAttempt();
            sendAttempt(numberAttempt);
        });

        this.pack();
        this.setVisible(true);
    }

    public void getAttempt () {
        if (!Prompt.getText().isEmpty()) {
            numberAttempt = Integer.parseInt(Prompt.getText());
        } else {
            numberAttempt = 1;
        }
    }

    public ClienteAdivinaNumero () {
        initComponents();
        try {
            Socket socket = new Socket("localhost", 777);
            System.out.println("Conexión establecida con el servidor.");
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            while (true) {
                try {
                    Object object = entrada.readObject();
                    Resultados.append(object + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAttempt (int num) {
        try {
            salida.writeInt(num);
            salida.flush();
        } catch (Exception e) {
            System.out.println("Error al enviar el número al servidor.");
        }
    }

    public void getServerResponse () {

    }

    public static void main(String[] args) {
        new ClienteAdivinaNumero();
    }

}

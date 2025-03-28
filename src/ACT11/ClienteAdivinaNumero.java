package ACT11;

import ACT10.ManejadorSocket;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteAdivinaNumero extends JFrame{
    private JTextField Prompt;
    private JButton enviarButton;
    private JTextArea Resultados;
    private JScrollPane ResultadosScroll;
    private JPanel MainFrame;


    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private int numberAttempt;

    public void initComponents ( ) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainFrame);
        this.setResizable(false);

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
            System.out.println("Intento: " + numberAttempt);
        } else {
            numberAttempt = 1;
        }
    }

    public void sendAttempt (int num) {
        try {
            System.out.println("Enviando intento al servidor: " + num);
            salida.writeObject(num);
            salida.flush();
        } catch (Exception e) {
            Logger.getLogger(ClienteAdivinaNumero.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void receiveMessage () {
        new Thread( () -> {
            try {
                while (true) {
                    Object object = entrada.readObject();
                    if (object != null) {
                        if (object.equals("¡Correcto!")) {
                            Resultados.append(object + "\n");
                            enviarButton.setEnabled(false);
                            Prompt.setEnabled(false);
                        } else {
                            Prompt.setText("");
                            Resultados.append(object + "\n");
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(ClienteAdivinaNumero.class.getName()).log(Level.SEVERE, null, e);
            }
        }).start();
    }

    public ClienteAdivinaNumero () {
        initComponents();
        try {
            Socket socket = new Socket("localhost", 777);
            System.out.println("Conexión establecida con el servidor.");

            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

           receiveMessage();
        } catch (Exception e) {
            Logger.getLogger(ClienteAdivinaNumero.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        new ClienteAdivinaNumero();
    }

}

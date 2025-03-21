package ACT09;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import  ACT09.ManejadorSocket;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends JFrame{
    private JTextField Prompt;
    private JButton Enviar;
    private JPanel MainFrame;
    private JList<String> Puertos;
    private JTextArea Mensajes;
    private JScrollPane JScrollMensajes;
    private JScrollPane JScrollPuertos;


    private DefaultListModel<String> ListaPuertos = new DefaultListModel<>();
    private ArrayList<ManejadorSocket> conexiones = new ArrayList<>();

    public Servidor() {
       initComponents();
       new Thread(this::initServer).start();
    }

    public void initServer() {
        try {

            ServerSocket servidor = new ServerSocket(777);
            System.out.println("Servidor iniciado en el puerto 777");

            while(true) {

                System.out.println("Esperando conexión \n");
                Socket cliente = servidor.accept();

                SwingUtilities.invokeLater(() -> ListaPuertos.addElement(cliente.getInetAddress().toString()));

                ManejadorSocket conexion = new ManejadorSocket(cliente, Mensajes);
                conexiones.add(conexion);
                conexion.start();
            }

        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String [] args ) {
        new Servidor();
    }


    public void initComponents () {
        setContentPane(MainFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Mensajes.setColumns(20);
        Mensajes.setRows(5);
        Mensajes.setEditable(false);

        JScrollMensajes.setViewportView(Mensajes);

        Puertos.setModel(ListaPuertos);
        Puertos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPuertos.setViewportView(Puertos);


        Enviar.addActionListener(e -> sendMessage());

        pack();
        setVisible(true);
    }

    public void sendMessage () {
        String msg = Prompt.getText();
        if (!msg.isEmpty()) {
            for ()
        }


    }



}

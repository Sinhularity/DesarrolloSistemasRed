package ACT14.cliente;

import ACT14.servidor.InterfacesRMI.Operaciones;
import ACT14.servidor.ServidorGUI;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteGUI {
    Registry registry;
    Operaciones operaciones;
    JTextArea textArea;

    public ClienteGUI() {
        initComponents();
    }

    public void initComponents() {

        JFrame frame = new JFrame("Cliente RMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        JLabel label = new JLabel("Ingrese un mensaje:");
        panel.add(label);
        JTextField textField = new JTextField(20);
        panel.add(textField);
        JButton sendButton = new JButton("Enviar");
        panel.add(sendButton);

        JScrollPane scrollPane = new JScrollPane();
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        panel.add(scrollPane);

        try {
            registry = LocateRegistry.getRegistry(null, 2079);
            operaciones = (Operaciones) registry.lookup("servicios");
            System.out.println("Cliente RMI conectado al servidor en el puerto 2079");
        } catch (RemoteException | NotBoundException e) {
            Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, e);
        }

        sendButton.addActionListener(e -> {
            String message = textField.getText();
            if (!message.isEmpty()) {
                try {
                    operaciones.sendMessageToServer(message);
                    textField.setText("");
                } catch (RemoteException ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(() -> {
            while (true) {
                try {
                    String msg = operaciones.receiveMessageFromServer();
                    if (msg != null) {
                        textArea.append("Cliente: " + msg + "\n");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}
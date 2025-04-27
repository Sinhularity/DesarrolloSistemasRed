package ACT14.servidor;

import ACT14.servidor.InterfacesRMI.Operaciones;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorGUI {
    Registry registry;
    Operaciones operaciones;
    JTextArea textArea;

    public ServidorGUI() {
        initComponents();
    }

    public void initComponents() {
        JFrame frame = new JFrame("Servidor RMI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.setContentPane(panel);

        JLabel label = new JLabel("Responder mensaje:");
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
            operaciones = new OperacionConcreta();
            registry = LocateRegistry.createRegistry(2079);
            registry.rebind("servicios", operaciones);
            System.out.println("Servidor RMI iniciado en el puerto 2079");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        sendButton.addActionListener(e -> {
            String message = textField.getText();
            if (!message.isEmpty()) {
                try {
                    operaciones.sendMessageToClient(message);
                    textArea.append("Mensaje enviado: " + message + "\n");
                    textField.setText("");
                } catch (RemoteException ex) {
                    Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(() -> {
            while (true) {
                try {
                    String msg = operaciones.receiveMessageFromClient();
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
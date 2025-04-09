package ACT14.cliente;

import ACT14.servidor.ConcreteOperaciones;
import ACT14.servidor.InterfacesRMI.Operaciones;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
            registry = LocateRegistry.getRegistry(null,2079);
            Operaciones operaciones = (Operaciones) registry.lookup("servicios");
            System.out.println("Cliente RMI conectado al servidor en el puerto 2079");
        } catch (RemoteException | NotBoundException e) {
            Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        textField.addActionListener(e -> {
            String message = textField.getText();
            sendMessage(message);
            textField.setText("");
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        while (true) {
//            try {
//
//                if (message != null) {
//                    receiveMessage(message);
//                }
//            } catch (RemoteException e) {
//                Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
//            }
//        }
    }

    void sendMessage(String message) {
        try {
            if (message != null) {
                operaciones.sendMessage(message);
                System.out.println("Mensaje enviado: " + message);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    void receiveMessage(String message) {
        try {
            if (message != null) {
                String response = operaciones.receiveMessage(message);
                textArea.append(response + "\n");
            }
        } catch (RemoteException e) {
            Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    }

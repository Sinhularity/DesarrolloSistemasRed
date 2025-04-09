package ACT14.servidor;

import ACT14.servidor.InterfacesRMI.Operaciones;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorGUI {
    Registry registry;
    Operaciones operaciones;
    JTextArea textArea;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public ServidorGUI() {
        initComponents();
    }

    public void initComponents() {

        JFrame frame = new JFrame("Servidor RMI");
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
            operaciones = new ConcreteOperaciones();
            registry = LocateRegistry.createRegistry(2079);
            registry.rebind("servicios", operaciones);
            System.out.println("Servidor RMI iniciado en el puerto 2079");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        textField.addActionListener(e -> {
            String message = textField.getText();
            sendMessage(message);
            textField.setText("");
        });

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(() -> {
           while (true) {
               try {
                   String message = operaciones.receiveMessage(null);
               } catch (RemoteException e) {
                   throw new RuntimeException(e);
               }
           }
        });
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
            if (message != null) {
                SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
            }
    }

}

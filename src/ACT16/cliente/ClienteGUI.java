package ACT16.cliente;



import ACT16.InterfazRMI.InterfazRemota;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.logging.Logger;

public class ClienteGUI {
    Registry registry;
    InterfazRemota operaciones;

    HashMap<Integer,String> clientNames;
    JList<String> personasList;
    DefaultListModel<String> listModel;


    public ClienteGUI() {
        initComponents();
    }

    public void initComponents() {
        JFrame frame = new JFrame("Cliente RMI - Lista de Personas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Ingrese un nombre:");
        labelPanel.add(label);
        JTextField textField = new JTextField(20);
        labelPanel.add(textField);
        JButton button = new JButton("Agregar");
        labelPanel.add(button);
        panel.add(labelPanel);

        try {
            registry = LocateRegistry.getRegistry(null,2079);
            operaciones = (InterfazRemota) registry.lookup("PersonService");
            System.out.println("Cliente RMI conectado al servidor en el puerto 2079");
            clientNames = operaciones.getClientList();
            listModel = new DefaultListModel<>();
            loadClientList();
            personasList = new JList<>(listModel);
            personasList.setModel(listModel);
            personasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } catch (Exception e) {
            Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);

        }
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(personasList);
        panel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        JButton removeButton = new JButton("Eliminar seleccionado");
        JButton updateButton = new JButton("Actualizar");
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        panel.add(buttonPanel);

        button.addActionListener(e -> {
            String nombre = textField.getText();
            if (!nombre.isEmpty()) {
                try {
                    operaciones.addName(nombre);
                    listModel.addElement(nombre);
                    textField.setText("");
                } catch (Exception ex) {
                    Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
                }
            }
        });
        removeButton.addActionListener(e -> {
            try {
                String nombre = personasList.getSelectedValue();
                operaciones.removeName(nombre);
                listModel.removeElement(nombre);
            } catch (RemoteException ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        });
        updateButton.addActionListener(e -> {
            try {
                clientNames = operaciones.getClientList();
                listModel.clear();
                loadClientList();
            } catch (Exception ex) {
                Logger.getLogger(ClienteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack();
    }

    public void loadClientList() {
        if (clientNames != null) {
            for (String name : clientNames.values()) {
                listModel.addElement(name);
            }
        }
    }
}

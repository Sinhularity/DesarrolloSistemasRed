package ACT10;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame{
    private JPanel MainFrame;
    private JTextField Prompt;
    private JButton EnviarButton;
    private JTextArea Mensajes;
    private JScrollPane MensajesScroll;
    private JScrollPane PuertosScroll;
    private JList<ManejadorSocket> Puertos;

    private Incognita incognita;
    private DefaultListModel<ManejadorSocket> clients;

    public Servidor() {
        clients = new DefaultListModel<>();
        initComponents();
        new Thread(this::initServer).start();
    }

    private void initServer() {
        try {
            ServerSocket server = new ServerSocket(777);
            while (true) {
                Socket client = server.accept();
                ManejadorSocket socketManager = new ManejadorSocket(client, Mensajes);
                SwingUtilities.invokeLater(() -> clients.addElement(socketManager));
                socketManager.start();
            }
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    private void initComponents() {
        setContentPane(MainFrame);
        setTitle("🧠Mastermind Server🧠");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Puertos.setModel(clients);
        Puertos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PuertosScroll.setViewportView(Puertos);

        Mensajes.setColumns(20);
        Mensajes.setRows(5);
        Mensajes.setEditable(false);
        MensajesScroll.setViewportView(Mensajes);

        // Aseguramos que se muestre la ventana
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ACT10.Servidor::new);
    }
}

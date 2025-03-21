package ACT09;


import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


//Aquí manejamos el apartado de la interfaz gráfica
//Extiende de JFrame para no hacer una instancia de Frame
public class Servidor extends JFrame {
    private JTextField Prompt;
    private JButton Enviar;
    private JPanel MainFrame;
    private JList<String> Puertos;
    private JTextArea Mensajes;
    private JScrollPane JScrollMensajes;
    private JScrollPane JScrollPuertos;

    private DefaultListModel<String> ListaPuertos;
    private Vector<Socket> clientes;
    private Vector<ManejadorSocket> conexiones;

    // Atributos del servidor
    private ServerSocket servidor;
    private boolean activo = true;

    public Servidor() {
        clientes = new Vector<>();
        conexiones = new Vector<>();
        ListaPuertos = new DefaultListModel<>();

        initComponents();

        // Creamos un hilo en paralelo para inicializar el servidor
        // Permitiendo escuchar desde un hilo diferente al de la interfaz gráfica y clientes.
        // Si no se hace esto, la interfaz gráfica no se mostrará hasta que el servidor se cierre.
        new Thread(this::initServer).start();
    }

    public void initServer() {
        try {
            servidor = new ServerSocket(777);
            System.out.println("Servidor iniciado en el puerto 777");

            while (activo) {
                Socket cliente = servidor.accept();
                ManejadorSocket conexion = new ManejadorSocket(cliente, Mensajes);

                // Obtenemos la IP del cliente y la añadimos a la lista de puertos
                String ipCliente = cliente.getInetAddress().toString();

                // Permite actualizar la lista de puertos desde un hilo diferente al de la interfaz gráfica de forma segura
                // Evita que se escriba en la interfaz sin haber realizado las operaciones o los cambios
                SwingUtilities.invokeLater(() -> ListaPuertos.addElement(ipCliente));

                conexiones.add(conexion);
                clientes.add(cliente);
                // Iniciamos el hilo de la conexión para que el servidor pueda recibir mensajes
                conexion.start();
            }
        } catch (Exception ex) {
            if (activo) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initComponents() {
        setContentPane(MainFrame);
        setTitle("Servidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel de clientes conectados
        Puertos = new JList<>(ListaPuertos);
        Puertos.setModel(ListaPuertos);
        // Evitamos la selección múltiple de clientes
        Puertos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPuertos.setViewportView(Puertos);

        // Área de mensajes
        Mensajes.setColumns(20);
        Mensajes.setRows(5);
        Mensajes.setEditable(false);
        JScrollMensajes.setViewportView(Mensajes);

        // Eventos
        Enviar.addActionListener(e -> sendMessage());

        // Aseguramos que se muestre la ventana
        pack();
        setVisible(true);
    }

    private void sendMessage() {
        String msg = Prompt.getText();
        // Obtenemos el índice del cliente seleccionado
        int index = Puertos.getSelectedIndex();

        if (index == -1) {
            Mensajes.append("Seleccione un cliente para enviar el mensaje\n");
            return;
        }
        // Verificamos que el mensaje no esté vacío y que haya clientes conectados
        if (!msg.isEmpty() && !clientes.isEmpty()) {
            // Obtenemos el cliente seleccionado y enviamos el mensaje
            ManejadorSocket conexion = conexiones.get(index);
            conexion.enviarMensaje(msg);
        } else {
            Mensajes.append((msg.isEmpty()) ? "No se puede enviar un mensaje vacío\n" : "No hay clientes conectados\n");
        }
        Prompt.setText("");
    }

    public static void main(String[] args) {
        // Nos aseguramos que la interfaz gráfica se ejecute en el hilo de despacho de eventos (EDT)
        // Debido a que se actualiza la interfaz gráfica desde un hilo diferente al de la interfaz (main Thread)
        // Evitando problemas de concurrencia y bloqueos en la interfaz gráfica
        SwingUtilities.invokeLater(Servidor::new);
    }
}

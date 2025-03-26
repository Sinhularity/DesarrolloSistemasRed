package ACT10;

import javax.swing.*;
import java.net.Socket;
import java.util.Vector;

public class Cliente extends JFrame {
    private JPanel MainFrame;
    private JComboBox PosicionUno;
    private JComboBox PosicionDos;
    private JComboBox PosicionTres;
    private JComboBox PosicionCuatro;
    private JComboBox PosicionCinco;
    private JButton EnviarButton;
    private JTextArea Resultados;
    private JScrollPane ResultadosScroll;

    private Socket socket;
    private ManejadorSocket conexion;
    private Vector <JComboBox> posiciones;

    public Cliente() {
        try {
            socket = new Socket("localhost", 777);
            System.out.println("Conectado al servidor: " + socket.getInetAddress());

            conexion = new ManejadorSocket(socket, Resultados);
            conexion.start();
            initComponents();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void initComponents () {
        setTitle("☠️☠️☠️ Adivina la combinación ☠️☠️☠️");
        setContentPane(MainFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Resultados.setColumns(20);
        Resultados.setRows(5);
        Resultados.setEditable(false);
        ResultadosScroll.setViewportView(Resultados);

        posiciones = new Vector<>();
        posiciones.add(PosicionUno);
        posiciones.add(PosicionDos);
        posiciones.add(PosicionTres);
        posiciones.add(PosicionCuatro);
        posiciones.add(PosicionCinco);

        setComboBoxes();

        EnviarButton.addActionListener(e -> {
            String combination = getCombination();
            conexion.sendMessage(combination);
        });

        pack();
        setVisible(true);
    }

    void setComboBoxes () {
        for (JComboBox comboBox : posiciones) {
            for (int i = 0; i < 10; i++) {
                comboBox.addItem(i);
            }
        }
    }

    String getCombination () {
        String combination = "";
        for (JComboBox comboBox : posiciones) {
            if (comboBox != null) {
                combination = combination.concat(comboBox.getSelectedItem().toString());
            }
        }
        System.out.println(combination);
        return combination;
    }

    public static void main(String[] args) {
        new Cliente();
    }
}

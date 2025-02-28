package ACT05;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrafficLight extends JFrame{
    private JLabel TLFiveS;
    private JLabel TLSevenS;
    private JPanel contentPane;
    private final HashMap <Integer, String> tlStates = new HashMap<>();

    public TrafficLight () {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        tlStates.put(0,"Rojo");
        tlStates.put(1,"Amarillo");
        tlStates.put(2,"Verde");

        TLFiveS.setText(tlStates.get(0));
        TLSevenS.setText(tlStates.get(0));
    }

    public void changeLight (int miliseconds,JLabel label) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            System.out.println("Error on sleep");
        }
            new Thread(() ->{
                if (label.getText().equals(tlStates.get(0)) ) {
                    label.setText(tlStates.get(1));
                } else if (label.getText().equals(tlStates.get(1)) ) {
                    label.setText(tlStates.get(2));
                } else {
                    label.setText(tlStates.get(0));
                }
            }).start();
    }

    public void init () {
        setVisible(true);
        synchronized (this) {
            while (true) {
                changeLight(5000,TLFiveS);
                changeLight(7000,TLSevenS);
            }
        }
    }
}

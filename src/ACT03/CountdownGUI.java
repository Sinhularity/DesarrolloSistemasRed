package ACT03;

import javax.swing.*;
import java.awt.*;

public class CountdownGUI {

    private final JFrame frame = new JFrame();
    JLabel label = new JLabel("First Timer");
    private final String [] buttonState = {"Iniciar","Pausar","Continuar"};
    private final JLabel firstTimerLabel = new JLabel("First Timer",JLabel.CENTER);
    private JButton firstTimerButton = new JButton(buttonState[0]);
    private final JLabel secondTimerLabel = new JLabel("Second Timer",JLabel.CENTER);
    private JButton secondTimerButton = new JButton(buttonState[0]);


    public void initialize () {
        prepareTimer();
        frame.setVisible(true);
    }

    private void prepareTimer () {
        frame.setTitle("User GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(4,2));
        firstTimerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.getContentPane().add(firstTimerLabel);
        firstTimerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.getContentPane().add(firstTimerButton);
        secondTimerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.getContentPane().add(secondTimerLabel);
        secondTimerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.getContentPane().add(secondTimerButton);

        frame.getContentPane().

        firstTimerButton.addActionListener(e -> changeTimerEvent(firstTimerButton));
        secondTimerButton.addActionListener(e -> changeTimerEvent(secondTimerButton));
    }

    public void changeTimerEvent (JButton button) {
        Countdown tempCountdown = new Countdown();
        if (button.getText().equals(buttonState[0])) {
            tempCountdown.startTimer();
            button.setText(buttonState[1]);
        } else if (button.getText().equals(buttonState[1])) {
            tempCountdown.pauseTimer();
            button.setText(buttonState[2]);
        } else if (button.getText().equals(buttonState[2])) {
            tempCountdown.startTimer();
            button.setText(buttonState[1]);
        }
    }
}

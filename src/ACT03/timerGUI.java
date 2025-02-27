package ACT03;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class timerGUI {
    private final JLabel mainTimerLabel = new JLabel("Contador");

    private final String [] buttonState = {"Iniciar","Pausar","Continuar"};


    private final JFrame frame = new JFrame();
    GridBagConstraints gbc = new GridBagConstraints();
    Timer uiUpdater;

    private JButton buttonTimerOne = new JButton(buttonState[0]);
    private JButton buttonTimerTwo = new JButton(buttonState[0]);

    private final JLabel labelTimerOne = new JLabel("0", SwingConstants.CENTER);
    private final JLabel labelTimerTwo = new JLabel("0", SwingConstants.CENTER);

    // In order to keep track of the timers and their respective buttons
    private final Map <JButton, TimerUser> buttonTimerUserMap = new HashMap<>();

    public timerGUI() {
        // Main frame specifications
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainTimerLabel.setFont(new Font("Times new roman", Font.BOLD, 30));
        frame.add(mainTimerLabel, gbc);
        frame.add(new JLabel("")); // Otherwise the layout is not centered?

        buttonTimerUserMap.put(buttonTimerOne, new TimerUser());
        buttonTimerUserMap.put(buttonTimerTwo, new TimerUser());

        // UI Updater
        uiUpdater = new Timer(1, e -> {
            updateTimerLabel(labelTimerOne, buttonTimerUserMap.get(buttonTimerOne));
            updateTimerLabel(labelTimerTwo, buttonTimerUserMap.get(buttonTimerTwo));
        });
        uiUpdater.start();

        // Centering the components
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(buttonTimerOne, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(labelTimerOne, gbc);
        buttonTimerOne.addActionListener(e -> {
            updateButtonState(buttonTimerOne);
        });

        frame.add(buttonTimerTwo);
        frame.add(labelTimerTwo);
        buttonTimerTwo.addActionListener(e -> {
            updateButtonState(buttonTimerTwo);
        });
    }

    public void updateButtonState (JButton button) {
        TimerUser timerUser = buttonTimerUserMap.get(button);
        if (button.getText().equals(buttonState[0])) { // Iniciar
            timerUser.start();
            button.setText(buttonState[1]);
        } else if (button.getText().equals(buttonState[1])) { // Pausar
            button.setText(buttonState[2]);
            timerUser.stopTimer();
        } else { // Continuar
            TimerUser tempTimer = new TimerUser();
            tempTimer.setAmountTime(timerUser.getAmountTime());
            buttonTimerUserMap.put(button, tempTimer);
            tempTimer.start();
            button.setText(buttonState[1]);
        }
    }

    public void updateTimerLabel (JLabel label, TimerUser timerUser) {
        SwingUtilities.invokeLater(() -> {
            label.setText(String.valueOf(timerUser.getAmountTime()));
        });
    }

    public void initialize () {
        frame.setVisible(true);
    }
}

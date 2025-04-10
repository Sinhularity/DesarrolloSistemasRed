package ACT04;

import javax.swing.*;

import static ACT04.Runner.completionOrder;

public class Race extends JFrame {
    private JPanel contentPane;
    private JLabel title;
    private JProgressBar firstRunner;
    private JProgressBar secondRunner;
    private JProgressBar thirdRunner;
    private JButton iniciarCarreraButton;

    public Race() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Carrera de Caballos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        firstRunner.setStringPainted(true);
        secondRunner.setStringPainted(true);
        thirdRunner.setStringPainted(true);

        iniciarCarreraButton.addActionListener(e -> {
            iniciarCarreraButton.setEnabled(false);
            initRace();
            completionOrder.clear();
        });
    }

    public void init() {
        setVisible(true);
    }

    public void initRace() {
        new Runner(firstRunner,"Caballo 1",iniciarCarreraButton).beginRace();
        new Runner(secondRunner,"Caballo 2",iniciarCarreraButton).beginRace();
        new Runner(thirdRunner,"Caballo 3",iniciarCarreraButton).beginRace();
    }
}
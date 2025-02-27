package ACT04;

import javax.swing.*;
import static ACT04.Runner.completionOrder;

public class RacePopUp extends JFrame {
    private JLabel Resultados;
    private JLabel firstPlace;
    private JLabel secondPlace;
    private JLabel thirdPlace;
    private JButton OKButton;
    private JPanel contentPane;

    public RacePopUp(JButton iniciarCarreraButton) {
        setContentPane(contentPane);
        setTitle("Resultados");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        firstPlace.setText(completionOrder.get(0));
        secondPlace.setText(completionOrder.get(1));
        thirdPlace.setText(completionOrder.get(2));

        OKButton.addActionListener(e -> {
            iniciarCarreraButton.setEnabled(true);
            dispose();
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                iniciarCarreraButton.setEnabled(true);
            }
        });
    }

    public void init() {
        setVisible(true);
    }
}

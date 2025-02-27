package ACT04;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Runner extends Thread {
    private JProgressBar progressBar;
    private int runnerProgress;
    private String name;
    protected static final List<String> completionOrder = Collections.synchronizedList(new ArrayList<>());
    private JButton iniciarCarreraButton;

    public Runner(JProgressBar progressBar,String name) {
        this.progressBar = progressBar;
        this.runnerProgress = 0;
        this.name = name;
    }

    public Runner(JProgressBar progressBar,String name,JButton iniciarCarreraButton) {
        this.progressBar = progressBar;
        this.runnerProgress = 0;
        this.name = name;
        this.iniciarCarreraButton = iniciarCarreraButton;
    }

    public void beginRace () {
        start();
    }

    @Override
    public void run() {
       while (runnerProgress < 1000) {
              runnerProgress += (int) (Math.random() * 25 + 10);
              progressBar.setValue(runnerProgress);
              try {
                sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
       }
        synchronized (completionOrder) {
            completionOrder.add(name);
            System.out.println(name + " ha terminado la carrera.");

            if (completionOrder.size() == 3) { // Solo mostrar cuando los 3 hayan terminado
                RacePopUp racePopUp = new RacePopUp(iniciarCarreraButton);
                racePopUp.init();
            }
        }
    }
}

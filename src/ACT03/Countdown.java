package ACT03;

public class Countdown extends Thread {
    private int amountTime;
    private boolean isRunning = false;

    public Countdown() {
        this.amountTime = 0;
    }

    public int getAmountTime() {
        return amountTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public synchronized void startTimer () {
        isRunning = true;
    }

    @Override
    public void run() {
        if (isRunning) {
            notifyAll();
            while (isRunning) {
                amountTime++;
            }
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("A problem occurred while waiting for the timer to start.");
            }
        }
    }

    public synchronized void pauseTimer () {
        isRunning = false;
    }
}

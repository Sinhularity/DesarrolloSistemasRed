package ACT03;

public class TimerUser extends Thread {
    private int amountTime;
    private boolean isRunning = true;

    public TimerUser() {
        this.amountTime = 0;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted...");
                break;
            }
            synchronized (this) {
                amountTime++;
            }
        }
    }

    public int getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(int amountTime) {
        this.amountTime = amountTime;
    }

    public void stopTimer() {
        isRunning = false;
    }
}

package Act01;

public class NumerosThread extends Thread {
    @Override
    public void run() {
        countDown();
    }

    public void countDown () {
        for (int j = 1; j < 6; j++) {
            long randomSleep = Math.round((long) (Math.random()*2000));
            try {
                Thread.sleep(randomSleep);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo:" +e.getCause());
            }
            System.out.println(j);
        }
    }
}
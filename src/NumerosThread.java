public class NumerosThread extends Thread {
    @Override
    public void run() {
        countDown();
    }

    public void countDown () {
        for (int j = 1; j < 6; j++) {
            long randomSpleep = Math.round((long) (Math.random()*2000));
            try {
                Thread.sleep(randomSpleep);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo:" +e);
            }
            System.out.println(j);
        }
    }
}

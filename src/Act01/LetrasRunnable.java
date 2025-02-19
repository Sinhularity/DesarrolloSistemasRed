package Act01;

public class LetrasRunnable implements Runnable{

    @Override
    public void run() {
        letters();
    }

    public void letters() {
        char [] letters = {'A', 'B', 'C', 'D', 'E'};
        for (char letter : letters) {
            long randomSpleep = Math.round((long) (Math.random()*2000));
            try {
                Thread.sleep(randomSpleep);
            } catch (InterruptedException e) {
                System.out.println("Error en el hilo:" +e);
            }
            System.out.println(letter);
        }
    }
}

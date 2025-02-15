public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando hilos");
        NumerosThread num = new NumerosThread();
        num.start(); // Porque subclases puede sobre escribir el método run ...

        // 13/02/2024 - S22017021
        // Para poder crear un nuevo hilo principal, se debe crear un objeto de tipo Thread
        Thread letter = new Thread(new LetrasRunnable());
        letter.start();

        // System.out.println("Hilos finalizados"); -> Se ejecuta antes de que los hilos hayan terminado

        NumerosThread num2 = new NumerosThread();
        num2.start();
        LetrasRunnable letter2 = new LetrasRunnable();
        letter2.run(); // Utilizamos directamente el método run


    }
}

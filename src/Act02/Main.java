package Act02;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(); //
        printer.start();
        new Thread () {
            @Override
            public void run() {
                printer.notifyPrintEven();
            }
        }.start();
    }
}

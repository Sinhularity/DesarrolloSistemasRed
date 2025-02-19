package Act02;

import java.util.Scanner;

public class Printer extends Thread {
    private int page;

    public void setPage(int page) {
        this.page = page;
    }

    public synchronized void printOnlyEven () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an even number: ");
        setPage(sc.nextInt());
        for (int i = 1; i <= page; i++) {
            if (i % 2 == 0) {
                System.out.println("Printing page: "+i);
            } else {
                System.out.println("The method {"+Thread.currentThread().getName()+"} now is waiting...");
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("There is a problem with the waiting thread");
                }
            }
            notify();
        }
    }

    public synchronized void notifyPrintEven () {
        for (int i = 1; i <= page; i++) {
            if (i % 2 != 0) {
                System.out.println("Resuming printing...");
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("There is a problem with the waiting thread");
                }
            }
        }
    }

    @Override
    public void run() {
        printOnlyEven();
    }
}

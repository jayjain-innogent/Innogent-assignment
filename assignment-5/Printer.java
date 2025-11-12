
class PrintEvenOdd {
    int value = 1;

    public void printerEven(int n) throws InterruptedException {
        synchronized (this) {
            try {
                if (n <= 0) return;
                while (value <= n) {
                    try {
                        if (value % 2 == 0) {
                            System.out.println("Even: " + value);

                            value++;
                            notify();
                        } else {
                            wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void printerOdd(int n) throws InterruptedException {
        synchronized (this) {
            try {
                if (n <= 0) return;
                while (value <= n) {

                    if (value % 2 == 1) {
                        System.out.println("Odd: " + value);
                        value++;
                        notify();
                    } else {
                        wait();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

public class Printer {
    public static void main(String[] args) throws InterruptedException {

        PrintEvenOdd print = new PrintEvenOdd();
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10;

        if (n<=0){
            throw new IllegalArgumentException("Number must be greater than 0.");
        }

        Thread t1 = new Thread(() -> {
            try {
                print.printerEven(n);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                print.printerOdd(n);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
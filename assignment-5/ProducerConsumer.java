import java.util.*;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        PC pc = new PC();
        int cap = args.length > 0 ? Integer.parseInt(args[0]) : 5;

        Thread t1 = new Thread(() -> {
            try {
                pc.producer(cap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                pc.consumer(cap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class PC {
    LinkedList<Integer> buffer = new LinkedList<>();
    int value = 0;

    public void producer(int cap) throws InterruptedException {
        try {
            while (true) {
                synchronized (this) {

                    while (buffer.size() >= cap) {
                        wait();
                    }
                    while (buffer.size() < cap) {
                        System.out.println("Producing: " + value);
                        buffer.add(value++);
                    }
                    System.out.println("Buffer is full .... notifying consumer");
                    notifyAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(500);
    }

    public void consumer(int cap) throws InterruptedException {
        try {
            while (true) {
                synchronized (this) {

                    while (buffer.size() < cap) {
                        wait();
                    }
                    while (!buffer.isEmpty()) {
                        int val = buffer.removeFirst();
                        System.out.println("Consumed: " + val);
                    }
                    System.out.println("Buffer is empty .... notifying producer");
                    notifyAll();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(500);
    }
}

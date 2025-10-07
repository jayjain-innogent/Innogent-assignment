import java.util.*;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        PC pc = new PC();

        Thread t1 = new Thread(() -> {
            try {
                pc.producer(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                pc.consumer();
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
                        System.out.println("Buffer is full, producer is waiting...");
                        wait();
                    }
                    System.out.println("Produced: " + value);
                    buffer.add(value++);
                    notify();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumer() throws InterruptedException {
        try {
            while (true) {
                synchronized (this) {

                    while (buffer.size() == 0) {
                        System.out.println("Buffer is empty, consumer is waiting...");
                        wait();
                    }
                    int current_value = buffer.get(0);
                    System.out.println("Consumed: " + current_value);
                    buffer.remove(0);
                    notify();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

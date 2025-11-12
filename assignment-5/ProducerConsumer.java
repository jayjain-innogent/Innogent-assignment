import java.util.*;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {

        PC pc = new PC();

        // Parse user input or set default capacity = 5
        final int cap = (args.length > 0) ? Integer.parseInt(args[0]) : 5;

        // Validate capacity
        if (cap <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0.");

        // Producer thread
        Thread t1 = new Thread(() -> {
            try {
                pc.producer(cap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread t2 = new Thread(() -> {
            try {
                pc.consumer(cap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        t1.join();
        t2.join();
    }
}

class PC {
    LinkedList<Integer> buffer = new LinkedList<>();
    int value = 0;
    int production_Limit = 5; // total number of production cycles

    // Producer logic
    public void producer(int cap) throws InterruptedException {
        try {
            while (true) {
                synchronized (this) {

                    // Stop production after limit
                    if (production_Limit == 0) {
                        notifyAll();
                        break;
                    }

                    // Wait if buffer is full
                    while (buffer.size() >= cap) {
                        wait();
                    }

                    // Produce items until buffer is full
                    while (buffer.size() < cap) {
                        System.out.println("Producing: " + value);
                        buffer.add(value++);
                    }

                    System.out.println("Buffer is full .... notifying consumer");
                    notifyAll();
                    production_Limit--;

                    Thread.sleep(200); // simulate delay
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Consumer logic
    public void consumer(int cap) throws InterruptedException {
        try {
            while (true) {
                synchronized (this) {

                    // Exit when all production done and buffer empty
                    if (production_Limit == 0 && buffer.isEmpty()) {
                        notifyAll();
                        break;
                    }

                    // Wait until buffer is full
                    while (buffer.size() < cap) {
                        if (production_Limit == 0 && buffer.isEmpty()) {
                            notifyAll();
                            return;
                        }
                        wait();
                    }

                    // Consume all items in buffer
                    while (!buffer.isEmpty()) {
                        int val = buffer.removeFirst();
                        System.out.println("Consumed: " + val);
                    }

                    System.out.println("Buffer is empty .... notifying producer");
                    notifyAll();

                    Thread.sleep(200); // simulate delay
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

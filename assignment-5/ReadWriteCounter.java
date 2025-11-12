import java.util.concurrent.locks.*;

public class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true); // fair lock
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        try {
            writeLock.lock();
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        try {
            readLock.lock();
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = () -> {
            for (int i = 0; i < 5; i++) {
                int value = counter.getCount();
                System.out.println(Thread.currentThread().getName() + " read: " + value);
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
                System.out.println(Thread.currentThread().getName() + " incremented");
                try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Thread reader1 = new Thread(readTask, "Reader 1");
        Thread reader2 = new Thread(readTask, "Reader 2");
        Thread writer1 = new Thread(writeTask, "Writer 1");
        Thread writer2 = new Thread(writeTask, "Writer 2");

        reader1.start();
        writer1.start();
        reader2.start();
        writer2.start();

        // Wait for all to finish
        reader1.join();
        reader2.join();
        writer1.join();
        writer2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}

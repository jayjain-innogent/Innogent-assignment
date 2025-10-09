
import java.util.concurrent.locks.*;

public class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readlock = lock.readLock();
    private final Lock writelock = lock.writeLock();

    public void increment() {
        writelock.lock();
        try {
            count++;
        } finally {
            writelock.unlock();
        }
    }

    public int getCount() {
        readlock.lock();
        try {
            return count;
        } finally {
            readlock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
                }
            }
        };
        Runnable writeTask = new Runnable() {

            public void run() {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " increamented");
                }

            }
        };

        Thread read = new Thread(readTask);
        read.setName("Reader 1");
        Thread write = new Thread(writeTask);
        write.setName("Writer 1");

        Thread read1 = new Thread(readTask);
        read1.setName("Reader 2");
        Thread write1 = new Thread(writeTask);
        write1.setName("Writer 2");

        read.start();
        write.start();
        read1.start();
        write1.start();

    }

}

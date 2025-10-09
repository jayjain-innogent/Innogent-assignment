import java.util.concurrent.locks.*;

public class PreventDeadLock {

    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            lock1.lock();
            System.out.println(Thread.currentThread().getName() + " acquired lock1");

            try {
                Thread.sleep(50);
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + " acquired lock2");

                // critical section
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing locks");
                lock2.unlock();
                lock1.unlock();
                System.out.println("");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
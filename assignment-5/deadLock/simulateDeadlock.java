import java.util.concurrent.locks.*;

public class simulateDeadlock {

    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            lock1.lock();
            System.out.println("" + Thread.currentThread().getName() + " acquired lock1");
            try {
                Thread.sleep(50);
                lock2.lock();
                System.out.println("" + Thread.currentThread().getName() + " acquired lock2");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("" + Thread.currentThread().getName() + " releasing lock");
                lock2.unlock();
                lock1.unlock();
            }
        };
        Runnable task2 = () -> {
            lock2.lock();
            System.out.println("" + Thread.currentThread().getName() + " acquired lock2");
            try {
                Thread.sleep(50);
                lock1.lock();
                System.out.println("" + Thread.currentThread().getName() + " acquired lock1");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("" + Thread.currentThread().getName() + " releasing lock");
                lock1.unlock();
                lock2.unlock();
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
    }

}

import java.util.concurrent.CountDownLatch;

public class ThreadCoordinationExample {

    public static void main(String args[]) throws InterruptedException {

        // Number of tasks to wait for

        CountDownLatch latch = new CountDownLatch(3);

        InitTask dbTask = new InitTask("Database", 2000, latch);
        InitTask cacheTask = new InitTask("Cache", 1500, latch);
        InitTask serviceTask = new InitTask("Service", 1000, latch);

        new Thread(() -> dbTask.execute()).start();
        new Thread(() -> cacheTask.execute()).start();
        new Thread(() -> serviceTask.execute()).start();

        latch.await(); // Main thread waits here until count reaches zero
        System.out.println("\n" + Thread.currentThread().getName() + "task Completed");

    }

}

class InitTask {
    String name;
    int duration;
    CountDownLatch latch;

    InitTask(String name, int duration, CountDownLatch latch) {
        this.name = name;
        this.duration = duration;
        this.latch = latch;
    }

    public void execute() {
        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(duration);
            System.out.println("");

            System.out.println(name + " initialization completed.");
            System.out.println("Remaining tasks: " + (latch.getCount() - 1));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
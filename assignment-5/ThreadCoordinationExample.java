import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

        // Wait until all tasks finish or timeout (5 seconds)
        if (!latch.await(5, TimeUnit.SECONDS)) {
            System.out.println("\nTimeout reached — some tasks didn’t finish!");
        } else {
            System.out.println("\n" + Thread.currentThread().getName() + " task Completed");
        }
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
            // synchronized to prevent console output overlap
            synchronized (System.out) {
                System.out.println(name + " initialization started.");
            }

            Thread.sleep(duration);

            synchronized (System.out) {
                System.out.println(name + " initialization completed.");
                System.out.println("Remaining tasks: " + (latch.getCount() - 1));
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(name + " was interrupted.");
        } finally {
            // reduce latch count
            latch.countDown();
        }
    }
}

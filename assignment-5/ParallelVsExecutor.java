import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelVsExecutor {
    public static void main(String[] args) throws Exception {
        int size = 1_000_000; // large number to see time difference

        // Using ExecutorService
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores); // 4 threads
        long startExec = System.currentTimeMillis();

        for (int i = 1; i <= size; i++) {
            int num = i;
            executor.submit(() -> {
                double sqrt = Math.sqrt(num); // simulate some computation
            });
        }
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);


        long endExec = System.currentTimeMillis();
        System.out.println("ExecutorService time: " + (endExec - startExec) + " ms");
        System.out.println("All Executor tasks finished.");

        // Using Parallel Stream
        long startParallel = System.currentTimeMillis();

        IntStream.rangeClosed(1, size)
                .parallel()
                .forEach(n -> {
                    double sqrt = Math.sqrt(n); // same computation
                });

        long endParallel = System.currentTimeMillis();
        System.out.println("Parallel Stream time: " + (endParallel - startParallel) + " ms");
    }
}

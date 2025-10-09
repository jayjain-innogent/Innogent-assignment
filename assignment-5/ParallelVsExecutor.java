import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ParallelVsExecutor {
    public static void main(String[] args) {
        int size = 1000000;

        long start1 = System.currentTimeMillis();
        IntStream.range(0, size).parallel().forEach(i -> Math.signum(i));
        long end1 = System.currentTimeMillis();
        System.out.println("Time taken by Parallel Stream: " + (end1 - start1) + " ms");

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        int chunkSize = size / threads;

        long start2 = System.currentTimeMillis();
        Future<?>[] futures = new Future[threads];
        for (int t = 0; t < threads; t++) {
            int start = t * chunkSize + 1;
            int end = (t == threads - 1) ? size : start + chunkSize - 1;

            futures[t] = executor.submit(() -> {
                for (int i = start; i <= end; i++) {
                    int square = i * i;
                }
            });
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long end2 = System.currentTimeMillis();
        executor.shutdown();
        System.out.println("Time taken by Executor service: " + (end2 - start2) + " ms");
    }

}
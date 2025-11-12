import java.io.*;
import java.util.concurrent.*;

public class LineCounterExecutor {

    public int countLines(String filePath) {
        // Validate file path
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Invalid file path provided.");
            return 0;
        }

        File file = new File(filePath);

        // Check file existence and permissions
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return 0;
        }
        if (!file.canRead()) {
            System.out.println("Cannot read file: " + filePath);
            return 0;
        }

        int lines = 0;

        // Read file and count lines
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }

            if (lines == 0)
                System.out.println(filePath + " is empty.");
            else
                System.out.println(filePath + ": " + lines + " lines");

        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }
        return lines;
    }

    public void executeCounting() {
        // Thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // File paths
        String file1 = "textfiles\\JavaBasics.txt";
        String file2 = "textfiles\\JavaCollections.txt";
        String file3 = "textfiles\\JavaExceptions.txt";
        String file4 = "textfiles\\JavaConcurrency.txt";

        // Tasks to count lines from each file
        Callable<Integer> task1 = () -> countLines(file1);
        Callable<Integer> task2 = () -> countLines(file2);
        Callable<Integer> task3 = () -> countLines(file3);
        Callable<Integer> task4 = () -> countLines(file4);

        try {
            // Submit tasks and get results
            Future<Integer> f1 = executor.submit(task1);
            Future<Integer> f2 = executor.submit(task2);
            Future<Integer> f3 = executor.submit(task3);
            Future<Integer> f4 = executor.submit(task4);

            // Sum total lines from all files
            int total = f1.get() + f2.get() + f3.get() + f4.get();
            System.out.println("Total lines across all files: " + total);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted.");
        } catch (ExecutionException e) {
            System.out.println("Error executing task: " + e.getMessage());
        } finally {
            // Close thread pool
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        new LineCounterExecutor().executeCounting();
    }
}

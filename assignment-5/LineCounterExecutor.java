import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.*;

public class LineCounterExecutor {

    // Method to count lines in a single file
    public int countLines(String filePath) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lines++;
            }
            System.out.println(filePath + ": " + lines + " lines");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void executeCounting() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Files Path
        String file1 = "textfiles\\JavaBasics.txt";
        String file2 = "textfiles\\JavaCollections.txt";
        String file3 = "textfiles\\JavaExceptions.txt";
        String file4 = "textfiles\\JavaConcurrency.txt";

        // Creating Callable objects
        Callable<Integer> countLinesTask1 = () -> countLines(file1);
        Callable<Integer> countLinesTask2 = () -> countLines(file2);
        Callable<Integer> countLinesTask3 = () -> countLines(file3);
        Callable<Integer> countLinesTask4 = () -> countLines(file4);

        try {
            Future<Integer> future1 = executor.submit(countLinesTask1);
            Future<Integer> future2 = executor.submit(countLinesTask2);
            Future<Integer> future3 = executor.submit(countLinesTask3);
            Future<Integer> future4 = executor.submit(countLinesTask4);

            // Get results and sum
            int totalLines = future1.get() + future2.get() + future3.get() + future4.get();
            System.out.println("Total lines across all files: " + totalLines);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        LineCounterExecutor lineCounter = new LineCounterExecutor();
        lineCounter.executeCounting();
    }
}

import java.util.*;

// This class tests the performance of ArrayList vs LinkedList
public class PerformanceTest {
    public static void main(String[] args) {
        // Define the sizes to test with: 10k, 50k, 100k
        int[] sizes = {10000, 50000, 100000};

        // Loop over each size and test both ArrayList and LinkedList
        for (int size : sizes) {
            System.out.println("------- Size: " + size + " -------");
            // Test performance of ArrayList
            testPerformance(new ArrayList<Integer>(), size, "ArrayList");
            // Test performance of LinkedList
            testPerformance(new LinkedList<Integer>(), size, "LinkedList");
            System.out.println(); // just for clean output spacing
        }
    }

    // This method performs insertion and deletion tests on the given List
    static void testPerformance(List<Integer> list, int size, String type) {
        long start, end;

        // Measure time taken to insert elements
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.add(i); // add element at the end
        }
        end = System.currentTimeMillis();
        System.out.println(type + " - Insertion time: " + (end - start) + " ms");

        // Measure time taken to delete all elements using an Iterator
        start = System.currentTimeMillis();
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            iter.next(); // move to next element
            iter.remove(); // remove current element
        }
        end = System.currentTimeMillis();
        System.out.println(type + " - Deletion time: " + (end - start) + " ms");
    }
}

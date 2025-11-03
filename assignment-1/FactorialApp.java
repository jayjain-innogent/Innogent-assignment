class FactorialCalculator {

    // Factorial calculation using a loop
    public static long factorialByLoop(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        long result = 1; // Local variable (stack)
        for (int i = 2; i <= n; i++) {
            result *= i; // Update local variable (stack)
        }
        return result; // Return value (stack)
    }

    // Factorial calculation using recursion
    public static long factorialByRecursion(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        if (n == 0 || n == 1) {
            return 1; // Base case
        }

        return n * factorialByRecursion(n - 1); // Recursive case
    }
}

public class FactorialApp {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 5;

        long factorialLoop = FactorialCalculator.factorialByLoop(n);
        System.out.println("Factorial of " + n + " using loop: " + factorialLoop);

        /*
         Memory Flow (for factorialByLoop):
         ---------------------------------
         - 'n', 'result', and 'i' are stored in the stack frame of the function.
         - Only one stack frame exists during iteration.
         - After the function returns, the result is stored in 'factorialLoop' in the main method's stack frame.
         - No objects are created, so heap memory is not used.
        */

        long factorialRecursion = FactorialCalculator.factorialByRecursion(n);
        System.out.println("Factorial of " + n + " using recursion: " + factorialRecursion);

        /*
         Memory Flow (for factorialByRecursion):
         ---------------------------------------
         - Each call to 'factorialByRecursion' creates a new stack frame.
         - For n = 5, there are 6 stack frames (for n = 5, 4, 3, 2, 1, 0).
         - Each frame stores its own local variable 'n'.
         - When the base case is reached (n == 0 or 1), recursion unwinds and returns results up the call stack.
         - No heap memory is used in this code.
         - Deep recursion can cause StackOverflowError if recursion depth exceeds JVM stack limit.
        */
    }
}

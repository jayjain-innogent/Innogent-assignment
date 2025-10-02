// Thrown when a student's marks are out of bounds (<0 or >100)
public class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

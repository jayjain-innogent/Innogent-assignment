import java.io.IOException;
import java.util.List;

// Defines what operations student management must provide (SOLID: Interface Segregation)
public interface StudentRepository {
    void addStudent(Student student) throws InvalidAgeException, InvalidMarksException;

    void deleteStudent(int studentId);

    List<Student> getAllStudents();

    void saveToFile(String filename) throws IOException;

    void loadFromFile(String filename) throws IOException;
}

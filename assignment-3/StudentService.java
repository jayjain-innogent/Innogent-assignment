import java.io.*;
import java.util.*;

// Handles all business logic, file I/O, and list operations for students
public class StudentService implements StudentRepository {
    private List<Student> students = new ArrayList<>();

    // Add a new student, validate business rules
    public void addStudent(Student student) throws InvalidAgeException, InvalidMarksException {
        if (student.getAge() > 20)
            throw new InvalidAgeException("Age > 20 is not allowed.");
        if (student.getMarks() < 0 || student.getMarks() > 100)
            throw new InvalidMarksException("Marks must be 0-100.");
        student.setStatus(student.getMarks() < 50 ? "Failed" : "Passed");
        students.add(student);
        updateRanks();
    }

    // Delete student by ID, update ranks
    public void deleteStudent(int studentId) {
        boolean found = false;
        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == studentId) {
                iter.remove();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with ID = " + studentId);
        }
        updateRanks();
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // Update student ranks based on highest marks
    private void updateRanks() {
        students.sort(Comparator.comparingInt(Student::getMarks).reversed());
        int rank = 1, prevMarks = -1;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getMarks() != prevMarks)
                rank = i + 1;
            s.setRank(rank);
            prevMarks = s.getMarks();
        }
    }

    // Save all students to a CSV file
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students)
                writer.write(s.toCsvString() + "\n");
        }
    }

    // Load all students from a CSV file
    public void loadFromFile(String filename) throws IOException {
        students.clear();
        File file = new File(filename);
        if (!file.exists())
            return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 8)
                    continue; // ignore malformed lines
                Student s = new Student(
                        Integer.parseInt(p[0]), p[1], Integer.parseInt(p[2]),
                        Integer.parseInt(p[3]), p[4], Integer.parseInt(p[5]));
                s.setStatus(p[6]);
                s.setRank(Integer.parseInt(p[7]));
                students.add(s);
            }
        }
    }
}

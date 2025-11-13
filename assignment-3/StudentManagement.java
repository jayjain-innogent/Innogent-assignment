import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {

        StudentService service = new StudentService();
        Scanner scanner = new Scanner(System.in);

        // Load previous data (if file exists)
        try {
            service.loadFromFile("students.csv");
            System.out.println("Previous data loaded successfully.");
        } catch (Exception e) {
            System.out.println("No saved data found. Starting fresh.");
        }

        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Show All Students");
            System.out.println("4. Save & Exit");
            System.out.print("Choose option: ");

            int choice = -1;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception ignored) {
                System.out.println("Invalid input! Enter numeric value.");
                continue;
            }

            try {
                if (choice == 1) {

                    // --- Important Input Section ---
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());

                    System.out.print("Name: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("ClassID: ");
                    int classId = Integer.parseInt(scanner.nextLine().trim());

                    System.out.print("Marks: ");
                    int marks = Integer.parseInt(scanner.nextLine().trim());

                    System.out.print("Gender: ");
                    String gender = scanner.nextLine().trim();

                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine().trim());

                    // Create student object
                    Student s = new Student(id, name, classId, marks, gender, age);
                    service.addStudent(s);

                    System.out.println("Student added successfully.");

                } else if (choice == 2) {

                    System.out.print("Enter Student ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());

                    boolean deleted = service.deleteStudent(id);

                    if (deleted) System.out.println("Student deleted.");
                    else System.out.println("Student not found.");

                } else if (choice == 3) {

                    // --- Table Header ---
                    System.out.printf(
                            "%-5s %-15s %-8s %-8s %-8s %-5s %-10s %-5s\n",
                            "ID", "Name", "ClassId", "Marks", "Gender", "Age", "Status", "Rank"
                    );

                    // --- Each Student Output ---
                    for (Student s : service.getAllStudents()) {
                        System.out.printf(
                                "%-5d %-15s %-8d %-8d %-8s %-5d %-10s %-5d\n",
                                s.getId(), s.getName(), s.getClassId(), s.getMarks(),
                                s.getGender(), s.getAge(), s.getStatus(), s.getRank()
                        );
                    }

                } else if (choice == 4) {

                    // --- Save on exit ---
                    service.saveToFile("students.csv");
                    System.out.println("Data saved. Goodbye!");

                    scanner.close();
                    break;

                } else {
                    System.out.println("Invalid option. Try again.");
                }

            } catch (InvalidAgeException | InvalidMarksException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid numeric input!");
            } catch (Exception ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
            }
        }
    }
}

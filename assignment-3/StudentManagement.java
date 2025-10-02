import java.util.Scanner;

// The main application entry point for Student Management System
public class StudentManagement {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner scanner = new Scanner(System.in);

        // Load student data if available
        try {
            service.loadFromFile("students.csv");
        } catch (Exception e) {
            System.out.println("No saved student data found. Starting fresh!");
        }

        // Simple menu loop
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Show All Students");
            System.out.println("4. Save & Exit");
            System.out.print("Choose option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception ignore) {
            }

            try {
                if (choice == 1) {
                    System.out.print("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("ClassID: ");
                    int classId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Marks: ");
                    int marks = Integer.parseInt(scanner.nextLine());
                    System.out.print("Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    Student s = new Student(id, name, classId, marks, gender, age);
                    service.addStudent(s);
                    System.out.println("Student added.");
                } else if (choice == 2) {
                    System.out.print("Enter ID to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    service.deleteStudent(id);
                } else if (choice == 3) {
                    System.out.printf("%-4s %-7s %-8s %-5s %-7s %-4s %-7s %-4s\n",
                            "ID", "Name", "ClassId", "Marks", "Gender", "Age", "Status", "Rank");
                    for (Student s : service.getAllStudents()) {
                        System.out.printf("%-4d %-7s %-8d %-5d %-7s %-4d %-7s %-4d\n",
                                s.getId(), s.getName(), s.getClassId(), s.getMarks(),
                                s.getGender(), s.getAge(), s.getStatus(), s.getRank());
                    }
                } else if (choice == 4) {
                    service.saveToFile("students.csv");
                    System.out.println("Data saved. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option.");
                }
            } catch (InvalidAgeException | InvalidMarksException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
            }
        }
    }
}

import java.util.*;

// Employee class represents an employee with id, name, department, and salary.
// It implements Comparable to define a default way to sort employees.
class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor to create an Employee object
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getter methods to access private fields
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    // This method defines how Employee objects are compared by default.
    // Sorting order: first by Department alphabetically,
    // if departments are same, then by Name alphabetically,
    // if names are also same, then by Salary ascending.
    @Override
    public int compareTo(Employee other) {
        int deptCmp = this.department.compareTo(other.department);
        if (deptCmp != 0)
            return deptCmp;

        int nameCmp = this.name.compareTo(other.name);
        if (nameCmp != 0)
            return nameCmp;

        // Salary ascending order
        return Double.compare(this.salary, other.salary);
    }

    // toString method to display Employee details in readable format
    @Override
    public String toString() {
        return "[ID: " + id + ", Name: " + name +
               ", Department: " + department + ", Salary: " + salary + "]";
    }
}

// Comparator class to sort Employees by salary in descending order
class SalaryDescendingComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee a, Employee b) {
        // Compare salaries but reverse order to get descending sort
        return Double.compare(b.getSalary(), a.getSalary());
    }
}

// Main class containing the program entry point (main method)
public class EmployeeSortMain {
    public static void main(String[] args) {
        // Creating a list to hold Employee objects
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice", "HR", 50000));
        employees.add(new Employee(102, "Bob", "IT", 80000));
        employees.add(new Employee(103, "Charlie", "HR", 60000));
        employees.add(new Employee(104, "David", "IT", 70000));
        employees.add(new Employee(105, "Eva", "Finance", 75000));
        employees.add(new Employee(106, "Arjun", "IT", 70000));

        // Sort the employees using the natural ordering defined in compareTo:
        // Department, then Name, then Salary ascending
        Collections.sort(employees);
        System.out.println("Sorted by Department → Name → Salary:");

        // Use an Iterator to loop through and print each employee
        Iterator<Employee> it1 = employees.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        // Now sort employees by salary descending using the Comparator
        employees.sort(new SalaryDescendingComparator());
        System.out.println("\nSorted by Salary (Descending):");

        // Use an Iterator again to print sorted employees
        Iterator<Employee> it2 = employees.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }
}

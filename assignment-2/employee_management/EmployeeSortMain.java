package employee_management;

import java.util.*;

public class EmployeeSortMain {
    public static void main(String[] args) {

        // Creating a list of Employee objects
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice", "HR", 50000.00));
        employees.add(new Employee(102, "Bob", "IT", 80000.00));
        employees.add(new Employee(103, "Charlie", "HR", 60000.00));
        employees.add(new Employee(104, "David", "IT", 70000.00));
        employees.add(new Employee(105, "Eva", "Finance", 75000.00));
        employees.add(new Employee(106, "Arjun", "IT", 70000.00));

        //Sort employees using their natural order (compareTo method in Employee)
        Collections.sort(employees);
        System.out.println("Sorted by Department → Name → Salary:");

        // Using Iterator to print list
        Iterator<Employee> it1 = employees.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        // Now sort using custom Comparator (Salary Descending)
//        employees.sort(new SalaryDescendingComparator());
        employees.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        System.out.println("\nSorted by Salary (Descending):");

        // Again print using Iterator
        Iterator<Employee> it2 = employees.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }
}

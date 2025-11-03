package employee_management;

public class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private String department;
    private Double salary;

    public Employee(int id, String name, String department, Double salary){
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee other) {
        int deptCmp = this.department.compareTo(other.department);
        if (deptCmp != 0) return deptCmp;

        int nameCmp = this.name.compareTo(other.name);
        if (nameCmp != 0) return nameCmp;

        return Double.compare(this.salary, other.salary);
    }


    @Override
    public String toString() { return "[ID: " + id + ", Name: "
            + name + ", Department: "
            + department + ", Salary: "
            + salary + "]"; }

}

// Represents a student in the system
public class Student {
    private int id;
    private String name;
    private int classId;
    private int marks;
    private String gender;
    private int age;
    private String status; // "Passed" or "Failed"
    private int rank;

    public Student(int id, String name, int classId, int marks, String gender, int age) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
        this.status = "Unknown";
        this.rank = -1;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClassId() {
        return classId;
    }

    public int getMarks() {
        return marks;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public int getRank() {
        return rank;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    // For saving as a CSV line
    public String toCsvString() {
        return id + "," + name + "," + classId + "," + marks + "," + gender + "," + age + "," + status + "," + rank;
    }
}

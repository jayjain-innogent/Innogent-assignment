// Represents the address info related to a student
public class Address {
    private int id;
    private int pinCode;
    private String city;
    private int studentId;

    public Address(int id, int pinCode, String city, int studentId) {
        this.id = id;
        this.pinCode = pinCode;
        this.city = city;
        this.studentId = studentId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public String getCity() {
        return city;
    }

    public int getStudentId() {
        return studentId;
    }

    public String toCsvString() {
        return id + "," + pinCode + "," + city + "," + studentId;
    }
}

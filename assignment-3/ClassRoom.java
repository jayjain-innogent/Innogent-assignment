// Represents a class/group in the school (like "A", "B", "C")
public class ClassRoom {
    private int id;
    private String name;

    public ClassRoom(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toCsvString() {
        return id + "," + name;
    }
}

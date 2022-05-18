package Model;

public class Lecturer {
    private Student[] prefs;
    private int id;
    private String name;
    private int capacity;
    public Lecturer(Student[] prefs, int id, String name) {
        this.prefs = prefs;
        this.id = id;
        this.name = name;
    }
}

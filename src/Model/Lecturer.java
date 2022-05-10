package Model;

public class Lecturer {
    private Student[] prefs;
    private long id;
    private String name;

    public Lecturer(Student[] prefs, long id, String name) {
        this.prefs = prefs;
        this.id = id;
        this.name = name;
    }
}

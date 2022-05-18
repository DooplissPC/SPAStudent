package Model;

/**
 *
 * @author rorys
 */
public class Project {
    private Student projectedPrefs[];
    private Lecturer lecturer;
    private int id;
    private int capacity;
    public Project(Lecturer lecturer, int id) {
        this.lecturer = lecturer;
        this.id = id;
    }

    public Student[] getProjectedPrefs() {
        return projectedPrefs;
    }

    public void setProjectedPrefs(Student[] projectedPrefs) {
        this.projectedPrefs = projectedPrefs;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public long getId() {
        return id;
    }
}

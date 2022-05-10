package Model;

/**
 *
 * @author rorys
 */
public class Student {
    private Project prefs[];
    private long id;
    private String name;

    public Student(Project prefs[], long id, String name){

    }

    public Project[] getPrefs() {
        return prefs;
    }

    public void setPrefs(Project[] prefList) {
        this.prefs = prefList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

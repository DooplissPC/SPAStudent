package Model;

/**
 *
 * @author rorys
 */
public class Student {
    private Project prefList[];
    private long id;
    private String name;

    public Student(long id, String name){

    }

    public Project[] getPrefList() {
        return prefList;
    }

    public void setPrefList(Project[] prefList) {
        this.prefList = prefList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

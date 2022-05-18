package Model;

/**
 *
 * @author rorys
 */
public class Student {
    private class PreferenceNode{
        Project project;
        Project nextProject;
        Project nextLecturerProject;
        Project prevProject;
        Project prevLecturerProject;
        public PreferenceNode(Project _project){
            project = _project;
        }
    }
    private Project rank[];
    private PreferenceNode prefs[];
    private int id;
    private String name;

    public Student(Project prefs[], int id, String name){

    }

    public Project[] getPrefs() {
        return rank;
    }

    public void setPrefs(Project[] prefList) {
        this.rank = prefList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

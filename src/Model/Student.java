package Model;

/**
 *
 * @author rorys
 */
public class Student {
    private class PreferenceNode{
        Project project;
        int nextProject;
        int nextLecturerProject;
        int prevProject;
        int prevLecturerProject;
        public PreferenceNode(Project _project){
            this.project = _project;
        }

        public void setNextLecturerProject(int nextLecturerProject) {
            this.nextLecturerProject = nextLecturerProject;
        }
        public void setPrevLecturerProject(int prevLecturerProject){
            this.prevLecturerProject = prevLecturerProject;
        }

        public int getNextLecturerProject() {
            return nextLecturerProject;
        }

        public int getPrevLecturerProject() {
            return prevLecturerProject;
        }
    }
    private Project[] rank;
    private PreferenceNode[] prefs;
    private PreferenceNode prefsHead; //first Node in prefs, since index can change with deletions
    private final int id;
    private String name;
    private Project assignedProject;

    public Student(Project rank[], int id, String name){
        this.rank = rank;
        this.id = id;
        this.name = name;
        this.assignedProject = null;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }

    public PreferenceNode[] initializePrefs(){
        int[] lastProjectFromLecturer = new int[rank.length]; //stores index  of prefs for last project from a lecturer
        int[] lecturerIDs = new int[rank.length]; //stores lecturer IDs
        int top = 0;
        this.prefs = new PreferenceNode[rank.length];
        //this.prefs = new ArrayList<PreferenceNode>();
        for (int i = 0; i < rank.length; i++){
            //this.prefs.add(new PreferenceNode(rank[i]));
            this.prefs[i] = new PreferenceNode(rank[i]);
            this.prefs[i].prevProject = i-1;
            this.prefs[i].nextProject = i+1;
            lastProjectFromLecturer[i] =  top;
            top++;

            //if(rank[i].getLecturer().getId() ==)
        }
        this.prefsHead = this.prefs[0];
        return this.prefs;
    }
    public Project[] getRank() {
        return rank;
    }

    public void setRank(Project[] prefList) {
        this.rank = prefList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

package Model;

import java.util.*;

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
        public PreferenceNode(Project project){
            this.project = project;
            this.prevLecturerProject = -1;
            this.nextLecturerProject = -1;//denotes that this value has not been set. Will cause an error if used to access prefs
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
    private int[] rank;
    private LinkedList<Project> prefs;
    //private PreferenceNode[] prefs;
    private final int id;
    private String name;
    private Project assignedProject;

    public Student(String name, int id){
        //this.rank = rank;
        this.id = id;
        this.name = name;
        this.assignedProject = null;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }

    public LinkedList<Project> initializePrefs(HashMap<Integer, Project> projectMap){
        for (int i = 0; i < this.rank.length; i++) {
            //this.prefs.addFirst(this.rank[i]);
            this.prefs.addFirst(projectMap.get(i));
        }
        return this.prefs;
    }
    public Student unassignProject(){
        Project unassignedProject = this.assignedProject;
        this.assignedProject = null;
        return this;
    }
    public Project deleteProject(Project deletedProject){
        this.prefs.remove(deletedProject);
        return deletedProject;
    }

    public ArrayList<Project> deleteLecturer(Lecturer deletedLecturer){
        Iterator<Project> it = this.prefs.iterator();
        ArrayList<Project> deletedProjects = new ArrayList<>();
        while(it.hasNext()){
            Project p = it.next();
            if(deletedLecturer.getId() == p.getLecturer().getId()){
                deletedProjects.add(p);
                it.remove();
            }
        }
        return deletedProjects;
    }

    public boolean isAcceptable(Project project){
        int id = project.getId();
        int i = 0;
        boolean acceptable = false;
        while (acceptable == false && i< this.rank.length){
            if(this.rank[i] == project.getId()){
                acceptable = true;
                return acceptable;
            }else{
                i++;
            }
        }
        return acceptable;
    }
    public Project getAssignedProject() {
        return assignedProject;
    }

    public void assignProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
    public Project getFirstProject(){
        return this.prefs.pollFirst();
    }

    public int[] getRank() {
        return rank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Project> getPrefs() {
        return prefs;
    }
    /*
    public PreferenceNode[] initializePrefs(){
        //int[] lastProjectFromLecturer = new int[rank.length]; //stores index  of prefs for last project from a lecturer
        //int[] lecturerIDs = new int[rank.length]; //stores lecturer IDs
        int currentID;
        HashMap<Integer, Integer> prefMap = new HashMap<Integer, Integer>();//store the index of the last project from lecturer
        this.prefs = new PreferenceNode[rank.length];
        //this.prefs = new ArrayList<PreferenceNode>();
        for (int i = 0; i < rank.length; i++){
            //this.prefs.add(new PreferenceNode(rank[i]));
            currentID = this.prefs[i].project.getLecturer().getId();//lecturer id of project at rank i
            this.prefs[i] = new PreferenceNode(rank[i]);
            this.prefs[i].prevProject = i-1;
            this.prefs[i].nextProject = i+1;
            if(prefMap.containsKey(currentID)) {//if a project from lecturer has already been seen
                this.prefs[i].prevLecturerProject = prefMap.get(currentID);//set prevLecturerProject to the index of prefs where it was seen
                this.prefs[prefMap.get(currentID)].nextLecturerProject = i;//set preLecturerProject's next header to current index
                prefMap.replace(this.prefs[i].project.getLecturer().getId(), i);//update prefMap so that project from current lecturer was last seen at i
            }else{
                prefMap.put(this.prefs[i].project.getLecturer().getId(), i);//add lecturer to map with current index representing when they were last seen
            }
            //if(rank[i].getLecturer().getId() ==)
        }
        return this.prefs;
    }

    public Project deleteProject(Project deletedProject){
        int i = this.prefsHead;
        while(this.prefs[i].nextProject<this.prefsTail&&this.prefs[i].project.getId()!= deletedProject.getId()){
            if(this.prefs[i].project.getId() == deletedProject.getId()&&i>=prefsHead){
                this.prefs[this.prefs[i].prevProject].nextProject = this.prefs[i].nextProject; //set next of previous project to next of current
                this.prefs[this.prefs[i].nextProject].prevProject = this.prefs[i].prevProject;
                if(this.prefs[i].prevLecturerProject!=-1 && this.prefs[this.prefs[i].prevLecturerProject].nextLecturerProject == i){

                }
            }else if(this.prefs[i].project.getId() == deletedProject.getId()&&i==prefsHead){

            }else if(this.prefs[i].project.getId() == deletedProject.getId()&&i==prefsTail-1){

            }else{
                i=this.prefs[i].nextProject;
            }
        }
    }
*/
}

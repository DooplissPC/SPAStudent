package Model;

import java.util.*;

/**
 * Represents a student within an SPA matching. Stores their preference list,
 * @author rorys
 */
public class Student {
    private int[] rank;
    private LinkedList<Project> prefs;
    private final int id;
    private String name;
    private Project assignedProject;

    public Student(String name, int id){
        this.id = id;
        this.name = name;
        this.assignedProject = null;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }

    public LinkedList<Project> initializePrefs(HashMap<Integer, Project> projectMap, int[] rank){
        this.rank = rank;
        this.prefs = new LinkedList<>();
        for (int i = 0; i < this.rank.length; i++) {
            //this.prefs.addFirst(this.rank[i]);
            this.prefs.add(projectMap.get(i));
        }
        return this.prefs;
    }
    public Project unassignProject(){
        Project unassignedProject = this.assignedProject;
        this.assignedProject = null;
        return unassignedProject;
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
        while (i < this.rank.length){
            if(this.rank[i] == project.getId()){
                acceptable = true;
                return acceptable;
            }else{
                i++;
            }
        }
        return acceptable;
    }
    public boolean hasAssignedProject(){
        return this.getAssignedProject() == null;
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
}

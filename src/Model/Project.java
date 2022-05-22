package Model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author rorys
 */
public class Project {
    private ArrayList<Student> projectedPrefs;
    private ArrayList<Student> assignedStudents;
    private Lecturer lecturer;
    private final int id;
    private int capacity;
    public Project(Lecturer lecturer, int id, int capacity) {
        this.lecturer = lecturer;
        this.id = id;
        this.capacity = capacity;
    }

    public ArrayList<Student> getProjectedPrefs() {
        return projectedPrefs;
    }

    public Student[] deleteStudents(Student){

    }
    public ArrayList<Student> deleteWorstStudent(){

    }
    public void setProjectedPrefs() {
        //ArrayList<Student> preferredStudents = new ArrayList<Student>();
        Student[] rank = this.lecturer.getPrefs();
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < rank[i].getPrefs().size(); j++) {
                if (this.lecturer.getId() == rank[i].getPrefs().get(j).g) {

                }
            }
        }
        this.projectedPrefs = projectedPrefs;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public int getId() {
        return id;
    }
}

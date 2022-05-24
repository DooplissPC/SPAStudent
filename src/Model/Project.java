package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author rorys
 */
public class Project {
    private ArrayList<Student> projectedPrefs;
    //private ArrayList<Student> assignedStudents;
    //private int lastStudent;
    private Lecturer lecturer;
    private int lecturerId;
    private final int id;
    private int capacity;
    private int maxCapacity;
    private String name;
    public Project(String name, int id, int maxCapacity, int lecturerId, HashMap<Integer, Lecturer> lecturerMap) {
        this.lecturerId = lecturerId;
        this.lecturer = lecturerMap.get(this.lecturerId);
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.capacity = 0;
        this.name=name;
    }

    public void initializeProjectedPrefs() {
        ArrayList<Student> rank = this.lecturer.getPrefs();
        Project p = this;
        for (Student student : rank) {
            if(student.getPrefs().contains(p)){
                this.projectedPrefs.add(student);
            }
        }
        //this.lastStudent = this.projectedPrefs.size()-1;
    }
    public void assignStudent(Student student) {//if false, lecturer is full
        this.capacity++;
        if (!this.projectedPrefs.contains(student)) {//if student not in prefs, add to end of prefs, and update last pref
            this.projectedPrefs.add(student);
            //this.lastPref++;
        }
    }
    public void unassignWorstStudent(){
        ListIterator<Student> it = this.projectedPrefs.listIterator(this.projectedPrefs.size());
        while(it.hasPrevious()){

        }
        this.capacity--;
    }
    public boolean isOversubscribed(){
        return this.capacity > this.maxCapacity;
    }
    public boolean isFull(){
        return this.capacity == this.maxCapacity;
    }
    public void deleteStudent(Student student){
        this.projectedPrefs.remove(student);
    }

    public ArrayList<Student> deleteWorstStudents() throws Exception {
        ArrayList<Student> removedStudents = new ArrayList<Student>();
        if (this.capacity == this.maxCapacity) {
            ListIterator<Student> it = this.projectedPrefs.listIterator(this.projectedPrefs.size());
            while(it.hasPrevious()){
                Student currentStudent = it.previous();
                if (currentStudent.getAssignedProject() != null && currentStudent.getAssignedProject().getId() == this.id) {
                    break;
                } else {
                    this.deleteStudent(currentStudent);//delete student from project preflist
                    currentStudent.deleteProject(this);//delete all projects from this lecturer in student preflist
                    removedStudents.add(currentStudent);//add to list of removed students
                    it.remove();//remove
                }
            }
        } else {
            throw new Exception("delete method has been accessed when project" + String.valueOf(this.id) + "was not at capacity");
        }
        return removedStudents;
    }

    public ArrayList<Student> getProjectedPrefs() {
        return projectedPrefs;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public int getId() {
        return id;
    }
}

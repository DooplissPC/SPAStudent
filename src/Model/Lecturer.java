package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class Lecturer {
    private int[] rank;
    private ArrayList<Student> prefs;
    private final int id;
    private String name;
    private int capacity;
    private int maxCapacity;
    public Lecturer(String name, int id, int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.capacity = 0;
        this.id = id;
        this.name = name;
    }
    public ArrayList<Student> initializePrefs(HashMap<Integer, Student> projectMap, int[] rank) {
        this.prefs = new ArrayList<>();
        this.rank=rank;
        for (int i = 0; i < this.rank.length; i++) {
            this.prefs.add(projectMap.get(i));
        }
        return this.prefs;
    }
    public void assignStudent(Student student){//if false, lecturer is full
        this.capacity++;
        if(!prefs.contains(student)){//if student not in prefs, add to end of prefs, and update last pref
            this.prefs.add(student);
        }
    }
    public boolean isOverSubscribed(){
        return this.capacity > this.maxCapacity;
    }
    public void decrementCapacity(){
        this.capacity--;
    }
    public Student unassignStudent() {//should be run alongside unassignProject
        Student worstStudent = null;
        Project worstStudentProject;
        ListIterator<Student> it = this.prefs.listIterator(this.prefs.size());
        while (it.hasPrevious()&&this.capacity>this.maxCapacity) {
            Student currentStudent = it.previous();
            if (currentStudent.hasAssignedProject() && currentStudent.getAssignedProject().getLecturer().getId() == this.id) {//&& for short circuit evaluation
                this.capacity--;
                worstStudentProject = currentStudent.unassignProject();
                worstStudent = currentStudent;
                worstStudentProject.decrementCapacity();
            }
        }
        return worstStudent;
    }
    /**
     * Deletes this lecturer's projects from all incompatible student's preference lists, as well from this lecturer's project's projected preference list. Will throw an exception if this method
     * is run before this lecturer is at capacity.
     * @throws Exception if accessed when lecturer is not at capacity
     */
    public ArrayList<Student> deleteWorstStudents() throws Exception {
        ArrayList<Student> removedStudents = new ArrayList<>();
        ListIterator<Student> it = this.prefs.listIterator(this.prefs.size());
            if (this.capacity == this.maxCapacity) {
                while (it.hasPrevious()) {
                    Student currentStudent = it.previous();
                    if (currentStudent.hasAssignedProject() && currentStudent.getAssignedProject().getLecturer().getId() == this.id) {//&& for short circuit evaluation
                        break;
                    } else {
                        currentStudent.getAssignedProject().deleteStudent(currentStudent);//delete student from project preflist
                        currentStudent.deleteLecturer(this);//delete all projects from this lecturer in student preflist
                        removedStudents.add(currentStudent);//add to list of removed students
                        it.remove();//remove
                    }
                }
            }else{
                throw new Exception("delete method has been accessed when lecturer" + this.id + "was not at capacity");
            }
        return removedStudents;
    }
    public boolean isFull(){
        return this.capacity == this.maxCapacity;
    }
    public void setRank ( int[] rank){
        this.rank = rank;
    }

    public int[] getRank() {
        return rank;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setMaxCapacity ( int capacity){
        this.maxCapacity = capacity;
    }

    public ArrayList<Student> getPrefs () {
        return prefs;
    }

    public int getId () {
        return this.id;
    }

    public String getName () {
        return name;
    }

    public int getCapacity () {
        return maxCapacity;
    }

}

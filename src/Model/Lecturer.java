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
    public ArrayList<Student> initializePrefs(HashMap<Integer, Student> projectMap) {
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
    public Student unassignStudent(Student student){//should be run alongside unassignProject
        Student worstStudent;
        if (this.capacity > this.maxCapacity) {
            int i = this.prefs.size();
            while(i >=0){
                Student currentStudent = this.prefs.get(i);
                if(currentStudent.getAssignedProject().getId() == this.id){
                    worstStudent = currentStudent.unassignProject();
                    this.capacity--;
                    return false;
                }else{
                    i--;
                }
            }
        }
    }
    /**
     * Deletes this lecturer's projects from all incompatible student's preference lists, as well from this lecturer's project's projected preference list. Will throw an exception if this method
     * is run before this lecturer is at capacity.
     * @throws Exception
     */
    public ArrayList<Student> deleteWorstStudents() {
        ArrayList<Student> removedStudents = new ArrayList<Student>();
        ListIterator<Student> it = this.prefs.listIterator(this.prefs.size());
        while(it.hasPrevious()){
            Student currentStudent = it.previous();
            if (currentStudent.getAssignedProject() != null && currentStudent.getAssignedProject().getLecturer().getId() == this.id) {//&& for short circuit evaluation
                break;
            } else {
                currentStudent.getAssignedProject().deleteStudent(currentStudent);//delete student from project preflist
                currentStudent.deleteLecturer(this);//delete all projects from this lecturer in student preflist
                removedStudents.add(currentStudent);//add to list of removed students
                it.remove();//remove
            }
        }
        return removedStudents;
    }
    public boolean isFull(){
        return this.capacity == this.maxCapacity;
    }
    public void setRank ( int[] rank){
        this.rank = rank;
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

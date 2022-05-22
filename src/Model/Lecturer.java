package Model;

import java.util.ArrayList;

public class Lecturer {
    private Student[] prefs;
    //private ArrayList<Student> prefs;
    private ArrayList<Student> assignedStudents;
    private int lastPref; //index of last member of prefs. once capacity is reached, update to worst member
    private final int id;
    private String name;
    private int capacity;
    public Lecturer(Student [] prefs, int id, String name) {
        this.prefs = prefs;
        this.id = id;
        this.name = name;
        this.lastPref = prefs.length - 1;
    }

    public Student assignStudent(Student student) {
        this.assignedStudents.add(student);
        this.lastPref = this.prefs.length - 1;

        if (this.assignedStudents.size() > capacity){
            //todo find and unassign worst student
        }
    }

    /**
     * Deletes all incompatible students from this lecturer's preference list. Will throw an exception if this method
     * is run before this lecturer is at capacity.
     * @throws Exception
     */
    public ArrayList<Student> deleteWorstStudents() throws Exception {
        ArrayList<Student> removedStudents = new ArrayList<Student>();
        if (this.assignedStudents.size() == capacity){
            int i = prefs.length;
            while (i >= 0) {
                if (this.prefs[i].getAssignedProject() != null && prefs.get(i).getAssignedProject().getLecturer().getId() == this.id) { //&& for short circuit evaluation
                    this.lastPref = i;

                    //Student[] newPrefs = new Student[this.lastPref];
                    //System.arraycopy(this.prefs, 0, newPrefs, 0, this.lastPref);
                    //this.prefs = newPrefs;
                    i = 0;
                } else {
                    this.prefs[i].getAssignedProject().deleteStudent();
                    this.prefs[i].deleteLecturer();
                    removedStudents.add(this.prefs.remove(i));

                    i--;
                }
            }
        }else{
            throw new Exception("delete method has been accessed when lecturer" + String.valueOf(this.id)+ "was not at capacity");
        }
    }

    public void setPrefs(Student[] prefs) {
        this.prefs = prefs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Student[] getPrefs() {
        return prefs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }



}

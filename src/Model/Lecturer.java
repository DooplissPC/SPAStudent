package Model;

import java.util.ArrayList;

public class Lecturer {
    private Student[] prefs;
    private ArrayList<Student> assignedStudents;
    private int lastIndex; //index of last member of assignedStudents. once capacity is reached, update to worst member
    private final int id;
    private String name;
    private int capacity;
    public Lecturer(Student[] prefs, int id, String name) {
        this.prefs = prefs;
        this.id = id;
        this.name = name;
    }

    public void addStudent(Student student){
        assignedStudents.add(student);
        this.lastIndex = assignedStudents.size()-1;
        if(assignedStudents.size() == capacity){
            int i = prefs.length;
            while(i >= 0){
                if(assignedStudents.get(i).getAssignedProject() != null && i >= 0){ //i>=0 for short circuit evaluation
                    this.lastIndex = i;
                    Student[] newPrefs = new Student[this.lastIndex];
                    System.arraycopy(this.prefs, 0, newPrefs, 0, this.lastIndex);
                    this.prefs = newPrefs;
                }else{

                    i--;
                }

            }

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

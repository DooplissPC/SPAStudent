package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Matchmaker {
    private ArrayList<Student> students;
    private ArrayList<Project> projects;
    private ArrayList<Lecturer> lecturers;
    //private HashMap<Student, Project> matching;
    private ArrayList<Student> unmatchedStudents;
    private ArrayList<Project> unmatchedProjects;

    public Matchmaker(){

    }
    public Student match(ArrayList<Student> students, ArrayList<Project> projects, ArrayList<Lecturer> lecturers){
        this.students = students;
        this.projects = projects;
        this.lecturers = lecturers;
        int id = 0;
        Student freeStudent;
        Queue<Student> freeStudents = new Queue;
        for (int i = 0; i < this.students.size(); i++) {
            freeStudents.add(this.students.get(i));
        }
        while (!freeStudents.isEmpty()&& freeStudent.getPrefs().){
            freeStudent = freeStudents.remove();

        }
    }
    public Project delete(Project deletedProject){

    }
}

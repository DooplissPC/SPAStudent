package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;


public class Matchmaker {

    private ArrayList<Student> students;
    private ArrayList<Project> projects;
    private ArrayList<Lecturer> lecturers;
    private HashMap<Integer, Project> projectHashMap;
    private HashMap<Integer, Student> studentHashMap;
    private HashMap<Integer, Lecturer> lecturerHashMap;
    //private HashMap<Student, Project> matching;
    private ArrayList<Student> unmatchedStudents;
    private ArrayList<Project> unmatchedProjects;
    private int idGen;
    public Matchmaker(){
        this.idGen = 0;
    }
    public Student match(ArrayList<Student> students, ArrayList<Project> projects, ArrayList<Lecturer> lecturers){
        this.students = students;
        this.projects = projects;
        this.lecturers = lecturers;
        Student student;
        Project project;
        Lecturer lecturer;
        ArrayList<Student> removedStudents;
        boolean accepted;
        Queue<Student> freeStudents = new Queue;
        for (Student s : this.students) {
            freeStudents.add(s);
        }
        while (!freeStudents.isEmpty() && !freeStudents.peek().getPrefs().isEmpty()){
            student = freeStudents.remove();
            project = student.getFirstProject();
            lecturer = project.getLecturer();
            student.assignProject(project);
            project.assignStudent(student);
            lecturer.assignStudent(student);
            if(project.isOversubscribed()){
                student = project.getWorstStudent();
                project.unassignStudent();
                student.unassignProject();
                freeStudents.add(student);
            }else if(lecturer.isOverSubscribed()){
                student = lecturer.getWorstStudent();
                project.unassignStudent(student);
                student.unassignProject();
                freeStudents.add(student);
            }
            if(project.isFull()){

            }
            if(lecturer.isFull()){
                lecturer.deleteWorstStudents();
            }
        }
    }
/*
    public Project delete(Project deletedProject){
        deletedProject.deleteWorstStudents();
    }*/
    /**
     * Generates an ArrayList of n=length students with names and IDs, but without preferences
     * @param length
     * @return
     */
    public ArrayList<Student> genStudents(int length){
        ArrayList<Student> generatedStudents = new ArrayList<Student>();
        for (int i = 0; i < length; i++) {
            generatedStudents.add(new Student("student_"+this.idGen,this.idGen));
            this.idGen++;
        }
        return generatedStudents;
    }
    public ArrayList<Lecturer> genLecturers(int length, int capacity){
        ArrayList<Lecturer> generatedLecturers = new ArrayList<Lecturer>();
        for (int i = 0; i < length; i++) {
            generatedLecturers.add(new Lecturer("lecturer_"+this.idGen,this.idGen, capacity));
            this.idGen++;
        }
        return generatedLecturers;
    }
    public ArrayList<Project> genProjects(int length, int capacity, ArrayList<Lecturer> lecturers, HashMap<Integer, Lecturer> lecturerMap){
        ArrayList<Project> generatedProjects = new ArrayList<Project>();
        Random rand = new Random();
        int randInt;
        for (int i = 0; i < length; i++) {
            randInt = rand.nextInt(lecturers.size());
            generatedProjects.add(new Project("project_"+this.idGen,this.idGen, capacity, lecturers.get(randInt).getId(), lecturerMap));
            this.idGen++;
        }
        return generatedProjects;
    }
    public void genSetUp(int studLength, int lectLength, int lectCapacity, int projectLength, int projectCapacity){

    }
    /*public Student readStudents(File students){
        students
    }*/
}

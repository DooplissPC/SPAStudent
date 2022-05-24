package Model;

import java.io.File;
import java.util.*;

public class Matchmaker {
    private ArrayList<Student> students;
    private ArrayList<Project> projects;
    private ArrayList<Lecturer> lecturers;
    private HashMap<Integer, Project> projectMap;
    private HashMap<Integer, Student> studentMap;
    private HashMap<Integer, Lecturer> lecturerMap;
    //private HashMap<Student, Project> matching;
    private ArrayList<Student> matchedStudents;
    private ArrayList<Student> unmatchedStudents;
    private ArrayList<Project> unmatchedProjects;
    private int idGen;
    public Matchmaker(){
        this.idGen = 0;
    }
    public ArrayList<Student> match(ArrayList<Student> students, ArrayList<Project> projects, ArrayList<Lecturer> lecturers) throws Exception {
        this.students = students;
        this.projects = projects;
        this.lecturers = lecturers;
        Student student;
        Student worstStudent;
        Project project;
        Lecturer lecturer;
        boolean accepted;
        Queue<Student> freeStudents = new LinkedList<>(this.students);
        while (!freeStudents.isEmpty() && !freeStudents.peek().getPrefs().isEmpty()){
            student = freeStudents.remove();
            project = student.getFirstProject();
            lecturer = project.getLecturer();
            student.assignProject(project);
            project.assignStudent(student);
            if(project.isOversubscribed()){

                worstStudent = project.unassignStudent();
                if(worstStudent == null){
                    throw new Exception("Worst student assigned to project could not be found");
                }
                freeStudents.add(worstStudent);
            }else if(lecturer.isOverSubscribed()){
                worstStudent = lecturer.unassignStudent();
                if(worstStudent == null){
                    throw new Exception("Worst student assigned to project could not be found");
                }
                freeStudents.add(worstStudent);
            }
            if(project.isFull()){
                project.deleteWorstStudents();
            }
            if(lecturer.isFull()){
                lecturer.deleteWorstStudents();
            }
        }
        for (Student s :
                this.students) {
            if(s.hasAssignedProject()){
                this.matchedStudents.add(s);
            }else{
                this.unmatchedStudents.add(s);
            }
        }
        return this.matchedStudents;
    }
    /**
     * Generates an ArrayList of n=length students with names and IDs, but without preferences
     * @param length
     * @return
     */
    public ArrayList<Student> genStudents(int length){
        ArrayList<Student> generatedStudents = new ArrayList<>();
        Student student;
        for (int i = 0; i < length; i++) {
            student = new Student("student_"+this.idGen,this.idGen);
            generatedStudents.add(student);
            this.studentMap.put(student.getId(), student);
            this.idGen++;
        }
        return generatedStudents;
    }
    public ArrayList<Lecturer> genLecturers(int length, int capacity){
        ArrayList<Lecturer> generatedLecturers = new ArrayList<>();
        Lecturer lecturer;
        for (int i = 0; i < length; i++) {
            lecturer =new Lecturer("lecturer_"+this.idGen,this.idGen, capacity);
            generatedLecturers.add(lecturer);
            this.lecturerMap.put(lecturer.getId(), lecturer);
            this.idGen++;
        }
        return generatedLecturers;
    }
    public ArrayList<Project> genProjects(int length, int capacity, ArrayList<Lecturer> lecturers, HashMap<Integer, Lecturer> lecturerMap){
        ArrayList<Project> generatedProjects = new ArrayList<>();
        Project project;
        Random rand = new Random();
        int randInt;
        for (int i = 0; i < length; i++) {
            randInt = rand.nextInt(lecturers.size());
            project = new Project("project_"+this.idGen,this.idGen, capacity, lecturers.get(randInt).getId(), lecturerMap);
            generatedProjects.add(project.getId(), project);
            this.idGen++;
        }
        return generatedProjects;
    }
    public void genSetUp(int studLength, int lectLength, int lectCapacity, int projectLength, int projectCapacity){
        this.students = genStudents(studLength);
        this.lecturers = genLecturers(lectLength, lectCapacity);
        this.projects = genProjects(projectLength, projectCapacity, this.lecturers,this.lecturerMap);
        int maxSize = Math.max(this.students.size(), this.lecturers.size());
        int i=0;
        while(i<maxSize){
            if(i<this.students.size()){
                this.students.get(i).initializePrefs(this.projectMap);
            }
            if(i<this.lecturers.size()){
                this.lecturers.get(i).initializePrefs(this.studentMap);
            }
            i++;
        }
        for (Project p : this.projects) {
            p.initializeProjectedPrefs();
        }
    }
    /*public Student readStudents(File students){
        students
    }*/
}

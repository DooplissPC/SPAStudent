package Model;

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
    private int idGenerator;
    public Matchmaker(){
        this.idGenerator = 0;
        this.studentMap = new HashMap<>();
        this.projectMap = new HashMap<>();
        this.lecturerMap = new HashMap<>();
    }
    public ArrayList<Student> match(ArrayList<Student> students, ArrayList<Project> projects, ArrayList<Lecturer> lecturers) throws Exception {
       //this.students = students;
      //  this.projects = projects;
      //  this.lecturers = lecturers;
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
    public ArrayList<Student> generateStudents(int length){
        ArrayList<Student> generatedStudents = new ArrayList<>();
        Student student;
        for (int i = 0; i < length; i++) {
            student = new Student("student_"+this.idGenerator,this.idGenerator);
            generatedStudents.add(student);
            //Integer test = new Integer(student.getId());
            this.studentMap.put(student.getId(), student);
            this.idGenerator++;
        }
        this.idGenerator = 0;
        return generatedStudents;
    }
    public ArrayList<Lecturer> generateLecturers(int length, int capacity){
        ArrayList<Lecturer> generatedLecturers = new ArrayList<>();
        Lecturer lecturer;
        for (int i = 0; i < length; i++) {
            lecturer =new Lecturer("lecturer_"+this.idGenerator,this.idGenerator, capacity);
            generatedLecturers.add(lecturer);
            this.lecturerMap.put(lecturer.getId(), lecturer);
            this.idGenerator++;
        }
        this.idGenerator = 0;
        return generatedLecturers;
    }
    public ArrayList<Project> generateProjects(int length, int capacity, ArrayList<Lecturer> lecturers, HashMap<Integer, Lecturer> lecturerMap){
        ArrayList<Project> generatedProjects = new ArrayList<>();
        Project project;
        Random rand = new Random();
        int randInt;
        for (Lecturer l : lecturers) {
            for (int i = 0; i < length; i++) {
                randInt = rand.nextInt(lecturers.size());
                project = new Project("project_"+this.idGenerator,this.idGenerator, capacity, l.getId(), lecturerMap);
                generatedProjects.add(project);
                this.idGenerator++;
            }
        }

        this.idGenerator = 0;
        return generatedProjects;
    }
    public ArrayList<Student> generateStudentRank(ArrayList<Student> students, ArrayList<Project> projects, int rankLength){
        if(rankLength>projects.size()){
            rankLength = projects.size();
        }
        Random rand = new Random();

        for (Student s: students) {
            int[] rank = new int[rankLength];
            for (int i = 0; i < rankLength; i++) {
                rank[i] = rand.nextInt(projects.size());
            }
            s.setRank(rank);
        }
        return students;
    }
    public ArrayList<Lecturer> generateLecturerRank(ArrayList<Lecturer> lecturers, ArrayList<Student> students, int rankLength){
        if(rankLength>students.size()){
            rankLength = students.size();
        }
        Random rand = new Random();
        for (Lecturer l: lecturers) {
            int[] rank = new int[rankLength];
            for (int i = 0; i < rankLength; i++) {
                rank[i] = rand.nextInt(students.size());
            }
            l.setRank(rank);
        }
        return lecturers;
    }
    public void generateSetUp(int studLength, int lectLength, int lectCapacity, int projectLength, int projectCapacity, int rankLength){
        this.students = generateStudents(studLength);
        this.lecturers = generateLecturers(lectLength, lectCapacity);
        this.projects = generateProjects(projectLength, projectCapacity, this.lecturers,this.lecturerMap);
        this.students = generateStudentRank(this.students,this.projects,rankLength);
        this.lecturers = generateLecturerRank(this.lecturers,this.students,rankLength);
        int maxSize = Math.max(this.students.size(), this.lecturers.size());
        int i=0;
        while(i<maxSize){
            if(i<this.students.size()){
                this.students.get(i).initializePrefs(this.projectMap, this.students.get(i).getRank());
            }
            if(i<this.lecturers.size()){
                this.lecturers.get(i).initializePrefs(this.studentMap, this.lecturers.get(i).getRank());
            }
            i++;
        }
        for (Project p : this.projects) {
            p.initializeProjectedPrefs();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<Student> getMatchedStudents() {
        return matchedStudents;
    }

    public ArrayList<Student> getUnmatchedStudents() {
        return unmatchedStudents;
    }

    public HashMap<Integer, Project> getProjectMap() {
        return projectMap;
    }

    public HashMap<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public HashMap<Integer, Lecturer> getLecturerMap() {
        return lecturerMap;
    }
/*public Student readStudents(File students){
        students
    }*/
}

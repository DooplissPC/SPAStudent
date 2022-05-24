package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchmakerTest {
    Matchmaker m = new Matchmaker();


    @BeforeEach
    void setup(){
        this.m = new Matchmaker();
        m.generateSetUp(50,30,4,80,2, 50);
    }

    @Test
    void genStudents(){
        m.generateStudents(2);
    }
    @Test
    void genLecturers(){
        m.generateLecturers(2,4);
    }

    @Test
    void genProjects() {
        //m.genProjects(2,2,)
    }

    @Test
    void genStudentRank() {
    }

    @Test
    void genLecturerRank() {
    }

    @Test
    void genSetUp() {
    }

    @Test
    void matchTest(){
        m.generateSetUp(50,30,4,6,2, 50);
        //m.generateSetUp(50,30,4,80,2, 50);
    }

    @Test
    void match() throws Exception {
        m.generateSetUp(50,30,4,2,2, 50);
        System.out.println(m.getProjects().get(0).getId());
        System.out.println(m.getLecturerMap().get(0).getId());
        m.match(m.getStudents(),m.getProjects(), m.getLecturers());
    }

}
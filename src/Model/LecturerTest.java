package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LecturerTest {
    @Test
    void copyArray(){
        class TestArray{
            char[] ar1;
            public void testArray(){
                this.ar1 = new char[4];
                for (int i = 0; i < 4; i++) {
                    this.ar1[i] = 'P';
                }

                char[] ar2 = {'e', 'f'};
                //this.ar1 = ar2;
                System.out.println("ar1: "+this.ar1+ "len:"+ ar1.length);
                System.arraycopy(this.ar1, 0, ar2, 0, ar2.length);
                this.ar1 = ar2;
                System.out.println("ar1: "+this.ar1+ "len:"+ ar1.length);
            }
        }
        TestArray arTest = new TestArray();
        arTest.testArray();
    }
    @Test
    void addStudent() {

    }
}
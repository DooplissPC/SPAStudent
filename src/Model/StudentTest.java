package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testArrayList(){
        ArrayList<String> testArray = new ArrayList<String>();
        for(int i = 0; i < 8; i++){
            testArray.add(String.valueOf(i));
        }
        int four = 4;
        System.out.println("array: "+testArray.size());
        testArray.remove(four);
        System.out.println("array: "+testArray.size());
        System.out.println("index at 7:"+testArray.get(7));
    }

}
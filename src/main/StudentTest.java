package main;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.*;

public class StudentTest {

    @Test
    public void constructorStudentTest() throws FileNotFoundException {
        Student test = new Student(22348069, "csv/students.csv");
        System.out.println(test.getName());
        assertEquals(test.getName(), "Evan Buggy");
    }
}

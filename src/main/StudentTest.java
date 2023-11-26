package main;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentTest {

    @Test
    public void constructorStudentTestName() throws FileNotFoundException {
        Student test = new Student(22348069, "src/csv/students.csv");
        System.out.println(test.getName());
        assertEquals(test.getName(), "Mr. Evan Buggy");
    }

    @Test
    public void constructorStudentTestIsAttending() throws FileNotFoundException {
        Student test = new Student(22348069, "src/csv/students.csv");
        System.out.println(test.isAttending());
        assertEquals(test.isAttending(), "Currently Attending");
    }
}

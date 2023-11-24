package main;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentTest {

    @Test
    public void constructorStudentTest() throws FileNotFoundException {
        Student test = new Student(22348069, "csv/students.csv");
        System.out.println(test.getName());
        assertEquals(test.getName(), "Evan Buggy");
    }
}

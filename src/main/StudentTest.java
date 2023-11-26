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

    @Test
    public void constructorStudentGradesTest() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        String[] arr_str = (test.getGradeTotalString()).get(0);
        assertEquals(arr_str[8], "NA");
    }

    @Test
    public void constructorStudentGradesTest2ndSemResults() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        String[] arr_str = (test.getGradeTotalString()).get(1);
        assertEquals(arr_str[3], "1");
    }
}

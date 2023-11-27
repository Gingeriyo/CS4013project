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

    // Checks to see if 22348069's address prints correctly.
    @Test
    public void StudentTestAddress() throws FileNotFoundException {
        Student test = new Student(22348069, "src/csv/students.csv");
        System.out.println(test.getAddress());
        assertEquals(test.getAddress(), "addressline1,\naddressline2,\naddressline3,\naddressline4\ny35x8d8");
    }

    // Checks to see if the 5th modules result of 22348069's 2nd semester were 43.
    @Test
    public void StudentGradesSemesterResults() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        String[] temp = test.getSemester(1);
        assertEquals(temp[8], "43");
    }
}

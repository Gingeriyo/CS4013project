package main;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentTest {

    @Test
    public void constructorStudentTestName() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        System.out.println(test.getName());
        assertEquals(test.getName(), "Mr. Evan Buggy");
    }

    @Test
    public void constructorStudentTestName2() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22342761, "src/csv/students.csv", "src/csv/grades.csv");
        System.out.println(test.getName());
        assertEquals(test.getName(), "Mr. Leo O'Shea");
    }

    @Test
    public void constructorStudentTestIsAttending() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        System.out.println(test.isAttending());
        assertEquals(test.isAttending(), "Currently Attending");
    }

    // Checks to see if 22348069's address prints correctly.
    @Test
    public void StudentTestAddress() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        System.out.println(test.getAddress());
        assertEquals(test.getAddress(), "addressline1,\naddressline2,\naddressline3,\naddressline4\ny35x8d8");
    }

    // Checks to see if the 5th modules result of 22348069's 2nd semester were 43.
    @Test
    public void StudentGradesSemesterResults() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        String[] temp = test.getResults(1);
        assertEquals(temp[4], "43");
    }

    @Test
    public void StudentGradesSemesterYearOfStudy() throws FileNotFoundException {
        StudentGrades test = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        String temp = test.getYearofStudy(1);
        assertEquals(temp, "2022/23");
    }

    // Tests to see if the 1st semester this student attended was LM121.
    @Test
    public void StudentCourseCode() throws FileNotFoundException {
        StudentGrades student = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        assertEquals("LM121", student.getCourseCode(0));
    }

    // Returns the full name from a StudentGrades grades object.
    @Test
    public void StudentGradesReturnName() throws FileNotFoundException {
        StudentGrades student = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        assertEquals("Mr. Evan Buggy", student.getName());
    }

    @Test
    public void LoginTest() throws FileNotFoundException {
        Login test = new Login(22348069, "CoolPassword92");
        assertEquals(true, test.read());
    }

    @Test
    public void LoginTest2() throws FileNotFoundException {
        Login test = new Login(17563628, "yay");
        assertEquals(false, test.read());
    }

    @Test
    public void NumOfSemesters() throws FileNotFoundException {
        StudentGrades student = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        assertEquals(8, student.getNumberOfSemesters());
    }
}

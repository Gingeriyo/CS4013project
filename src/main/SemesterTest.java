package main;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterTest {

    @Test
    public void SemesterGetModuleNameTest() throws FileNotFoundException {
        Semester test = new Semester(30, "LM121", 0, "src/csv/course.csv", "src/csv/modules.csv");
        String[] temp = "CS4012CS4141CS4221ET4011MA4111".split("(?<=\\G.{6})");
        for(int i = 0; i < temp.length; i++) {
            System.out.println(temp[i] + "\n");
        }
        assertEquals("Representation and Modelling", test.getModuleName(0));
    }

    @Test
    public void SemesterGetModuleNameTest2() throws FileNotFoundException {
        Semester test = new Semester(30, "LM121", 0, "src/csv/course.csv", "src/csv/modules.csv");
        String[] temp = "CS4012CS4141CS4221ET4011MA4111".split("(?<=\\G.{6})");
        for(int i = 0; i < temp.length; i++) {
            System.out.println(temp[i] + "\n");
        }
        assertEquals("Representation and Modelling", test.getModuleName(0));
    }

    @Test
    public void SemesterGetDirector() throws FileNotFoundException {
        Semester test = new Semester(30, "LM121", 0, "src/csv/course.csv", "src/csv/modules.csv");
        assertEquals("Dr Emil Vassev", test.getCourseDirector());
    }

    // Returns the course director's name of the 1st semester the student took.
    @Test
    public void SemesterGradesTest() throws FileNotFoundException {
        StudentGrades student = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        Semester test = new Semester(30, student.getCourseCode(0), student.getSemester(0), "src/csv/course.csv", "src/csv/modules.csv");
        assertEquals("Dr Emil Vassev", test.getCourseDirector());
    }
}

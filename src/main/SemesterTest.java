package main;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterTest {

    // This test gets the first module name of the semester.
    // In this case, CS4012.
    // Reminder that the order of modules matters in the course.csv file
    // to work with the order of modules in the grades.csv file.
    
    @Test
    public void SemesterGetModuleNameTest() throws FileNotFoundException {
        Semester test = new Semester(30, "LM121", 0, "src/csv/course.csv", "src/csv/modules.csv");
        String[] temp = "CS4012CS4141CS4221ET4011MA4111".split("(?<=\\G.{6})");
        for(int i = 0; i < temp.length; i++) {
            System.out.println(temp[i] + "\n");
        }
        assertEquals("Representation and Modelling", test.getModuleName(0));
    }

    // Similar test but for another module.
    @Test
    public void SemesterGetModuleNameTest2() throws FileNotFoundException {
        Semester test = new Semester(30, "LM121", 0, "src/csv/course.csv", "src/csv/modules.csv");
        String[] temp = "CS4012CS4141CS4221ET4011MA4111".split("(?<=\\G.{6})");
        for(int i = 0; i < temp.length; i++) {
            System.out.println(temp[i] + "\n");
        }
        assertEquals("Discrete Mathematics", test.getModuleName(4));
    }

    // This gets the course director.
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
        test.gradeCalc(student.getResults(0));
        assertEquals("F NG A1 C1 F ", test.getGrades());
    }

    // Gets the module marking scheme for the 3rd module of the student's 1st semester..
    @Test
    public void SemesterModuleMarkingSchemeReturn() throws FileNotFoundException {
        StudentGrades student = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        Semester test = new Semester(30, student.getCourseCode(0), student.getSemester(0), "src/csv/course.csv", "src/csv/modules.csv");
        assertEquals(30, test.getModules().get(0).getMarkingScheme()[2]);
    }

    // Gets the QCA of this student's 1st semester.
    @Test
    public void SemesterReturnQCA() throws FileNotFoundException {
        StudentGrades student = new StudentGrades(22348069, "src/csv/students.csv", "src/csv/grades.csv");
        Semester test = new Semester(30, student.getCourseCode(0), student.getSemester(0), "src/csv/course.csv", "src/csv/modules.csv");
        test.gradeCalc(student.getResults(0));
        System.out.println(test.QCACalc());
        assertEquals(1.32, test.QCACalc());
    }
}

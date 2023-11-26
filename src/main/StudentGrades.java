package main;

import java.io.FileNotFoundException;

public class StudentGrades extends Student {

    public StudentGrades(int id, String path) throws FileNotFoundException {
        super(id, path);
    }
    // This class is the one instantiated at runtime.
    // It is a child of Student, which holds the personal information about a student.
    // This class will store the actual grades for a student.
    // The information is read from/written to students.csv.
    // Grades can be assigned by a Faculty user.
}

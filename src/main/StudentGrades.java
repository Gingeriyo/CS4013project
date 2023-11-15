package main;

import java.io.FileNotFoundException;

public class StudentGrades extends Student {

    public StudentGrades(int id, String path) throws FileNotFoundException {
        super(id, path);
    }
    // This holds objects of each semester.
    // Stored in years (represented as YYYY/YY).
    // Need to account for students that have dropped out halfway through
    // a year and students that have came back after.
}

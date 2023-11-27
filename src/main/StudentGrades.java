package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// This class is the one instantiated at runtime.
// It is a child of Student, which holds the personal information about a student.
// This class will store the actual grades for a student.
// The information is read from/written to students.csv.
// Grades can be assigned by a Faculty user.

public class StudentGrades extends Student {

    // Grades are stored in this ArrayList. Each string array in this
    // list corresponds to a semester.
    // The 1st string is always empty.
    // The 2nd is the course code.
    // The 3rd is the number of the semester out of the duration of the course (starts from 0).
    ArrayList<String[]> grades = new ArrayList<String[]>();

    public StudentGrades(int id, String studentInfoPath, String gradesPath) throws FileNotFoundException {
        super(id, studentInfoPath);
        String gradeID = Integer.toString(id);
        Scanner gradeReader = new Scanner(new File(gradesPath));
        gradeReader.useDelimiter(",");
            while (gradeReader.hasNext()) {
            String ID = gradeReader.next();
            
            if (Objects.equals(gradeID, ID)) {
                String[] gradeDetails = (gradeReader.nextLine()).replace(gradeID + ",", "").split(" ");
                for (int i = 0; i < gradeDetails.length; i++) {
                    grades.add(gradeDetails[i].split(","));
                }
                break;
            }
        }
    }

    // Returns the results for a specified semester (sem) and other info about the semester.
    public String[] getSemester(int sem) {
        return grades.get(sem);
    }

    public String getYearofStudy(int sem) {
        return grades.get(sem)[2];
    }
}

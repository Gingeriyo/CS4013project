package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
        boolean reading = true;
        String temp;

        while (reading) {
            temp = gradeReader.next();
            
            if (gradeID.equals(temp.replace("\n", ""))) {
                String[] gradeDetails = (gradeReader.nextLine()).replace(gradeID + ",", "").split(" ");
                System.out.println(Arrays.toString(gradeDetails));
                for (int i = 0; i < gradeDetails.length; i++) {
                    grades.add(gradeDetails[i].split(","));
                }
                reading = false;
            }
        }
    }

    // Returns the results for a specified semester (sem) and other info about the semester.
    public int getSemester(int sem) {
        return Integer.parseInt(grades.get(sem)[3]);
    }

    public String getYearofStudy(int sem) {
        return grades.get(sem)[2];
    }

    public String getCourseCode(int sem) {
        return grades.get(sem)[1];
    }

    public String[] getResults(int sem) {
        String[] temp = new String[grades.get(sem).length - 4];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = grades.get(sem)[i + 4];
        }
        return temp;
    }
}

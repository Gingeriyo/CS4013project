package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    ArrayList<String[]> comments = new ArrayList<String[]>();

    /**
     * The constructor for a StudentGrades object.
     * This object contains all the semesters a student has done for a single student.
     * The information is stored in the grades.csv file. The basic information about a
     * student is stored in students.csv and the code for getting that information
     * is inherited from the Student class.
     * 
     * Take this line for example:
     * 22342761,0,Leo,O'Shea,0851833320,leosCoolEmail@leo.com,0,addressline1,addressline2,addressline3,addressline4,y35da0c,true,

     * The 1st token is the student ID.
     * The next is the title for Leo (used for an array of titles).
     * Then the 1st and 2nd name.
     * The 5th and 6th are their phone number and email respectively.
     * The 7th is what type of student they are.
     * The next 5 tokens are their address and eircode.
     * the final token is whether the student is currently attending or not.
     * grades.csv: Contains information about the student's grades. Is used for when StudentGrades is instantiated.
     * There is a line for each student. "NA" means a result has not yet been assigned.

     * Take this line for example:
     * 92546743,LM110,2020/21,0,76,99,45,60,22,,LM110,2020/21,1,77,NA,43,87,NA,,AQ822,2022/23,0,76,99,45,60,22,

     * The first token is the Student ID, Followed by the course code and the semester that the following grades are for.
     * So this student has grades of 76, 99, 45, 60, and 22 for the first semester of LM110 (Bachelors for Game Development).
     * The college year is showed after the college code.
     * The semesters are separated by an empty delimiter.
     * The student then has 77, NA, 43, 87 and NA for the 2nd semester.
     * This student has yet to receive their grades for their 2nd and 5th module, so they are marked as NA.
     * This student dropped out, and then came back and decided to do a different course, with a course code of AQ822.
     * Faculty users have the ability to write this data to the grades.csv file.
     * 
     * @param id The ID of the student.
     * @param studentInfoPath The path to the csv file containing the informatiom about a student.
     * @param gradesPath The path to the csv file containing the student's grades.
     * @throws FileNotFoundException
     */
    public StudentGrades(int id, String studentInfoPath, String gradesPath) throws FileNotFoundException {
        super(id, studentInfoPath);
        String gradeID = Integer.toString(id);
        Scanner gradeReader = new Scanner(new File(gradesPath));
        Scanner commentReader = new Scanner(new File("src/csv/comments.csv"));
        gradeReader.useDelimiter(",");
        commentReader.useDelimiter(",");
        boolean reading = true;
        String temp;
        String temp2;

        while (reading) {
            temp = gradeReader.next();
            
            if (gradeID.equals(temp.replace("\n", ""))) {
                String[] gradeDetails = (gradeReader.nextLine()).replace(gradeID + ",", "").split(" ");
                for (int i = 0; i < gradeDetails.length; i++) {
                    grades.add(gradeDetails[i].split(","));
                }
                reading = false;
            }
        }

        reading = true;
        while (reading) {
            temp2 = commentReader.next();
            
            if (gradeID.equals(temp2.replace("\n", ""))) {
                String[] commentDetails = (commentReader.nextLine()).replace(gradeID + ",", "").split(";");
                for (int i = 0; i < commentDetails.length; i++) {
                    comments.add(commentDetails[i].split(","));
                }
                reading = false;
            }
        }
    }

    // Returns the results for a specified semester (sem) and other info about the semester.
    public int getSemester(int sem) {
        return Integer.parseInt(grades.get(sem)[3]);
    }

    public int getNumberOfSemesters() {
        return grades.size();
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
    
    public String[] getComments(int sem) {
        String[] temp = new String[comments.get(sem).length - 4];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = comments.get(sem)[i + 4];
        }
        return temp;
    }
}

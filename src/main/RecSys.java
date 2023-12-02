package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RecSys {

    StudentGrades student;
    ArrayList<Semester> semesters = new ArrayList<Semester>();

    // RecSys constructor that makes multiple semester objects for the one student.
    public RecSys(int id) throws FileNotFoundException {
        student = new StudentGrades(id, "src/csv/students.csv", "src/csv/grades.csv");
        for (int i = 0; i < student.getNumberOfSemesters(); i++) {
            Semester adding = new Semester(30, student.getCourseCode(i), student.getSemester(i), "src/csv/course.csv", "src/csv/modules.csv");
            adding.gradeCalc(student.getResults(i));
            semesters.add(adding);
        }
    }

    // Iterates through the semester's module arraylists to get the modules
    // names, codes and credits.
    // The string array is in the format as follows:
    // "Module code", "Module credits", "Module name"
    public String[] getAllModuleInfo() {
        String temp = "";
        ArrayList<Module> mods;
        for (int i = 0; i < semesters.size(); i++) {
            mods = (semesters.get(i)).getModules();
            for (int p = 0; p < mods.size(); p++) {
                temp += (mods.get(p).getClassCode() + "," + mods.get(p).getCredit() + "," + mods.get(p).getName() + ",");
            }
        }
        return temp.split(",");
    }

    // Returns information about the modules of a specific Semester.
    // needs to be changed to use string.format
    public String getModulesInfo(int sem) {
        ArrayList<Module> mods = semesters.get(sem).getModules();
        String temp = "";
        for (int i = 0; i < mods.size(); i++) {
            temp += (mods.get(i).getClassCode() + ", " + mods.get(i).getCredit() + ", " + mods.get(i).getName() + "\n");
        }
        return temp;
    }

    public int getLastSemesterNum() {
        return semesters.size() - 1;
    }

    public StudentGrades getStudent() {
        return student;
    }

    public String transcriptSingleSem(int sem) {
        String temp = "";
        Semester tempsem = semesters.get(sem);
        ArrayList<Module> mods = tempsem.getModules();
        temp += student.getName() + "\n" +
        student.getAddress() + "\n" +
        student.getEmail() + ", " + student.getphoneNumber() + "\n" +
        student.getStudentType() + ", " + student.isAttending() + "\n" + "--------------------" + "\n" +
        student.getYearofStudy(sem) + "\n";

        for (int i = 0; i < mods.size(); i++) {
            temp += mods.get(i).getClassCode() + "     |  " +
            String.format("%-10s", mods.get(i).getCredit()) + "|  " +
            String.format("%-50s", mods.get(i).getName()) + "|  " +
            tempsem.getSingleGrade(i) + "\n";
        }

        temp += "--------------------" + "\n" +
        tempsem.getAttendedHours() + " Attended Hours\n" +
        "QCA:" + tempsem.QCACalc() + "\n\n";

        return temp;
    }

    public String transcript() {
        String temp = "";
        for (int i = 0; i < semesters.size(); i++) {
            temp += transcriptSingleSem(i);
        }
        return temp;
    }
}

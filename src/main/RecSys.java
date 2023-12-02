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
            semesters.add(new Semester(30, student.getCourseCode(i), student.getSemester(i), "src/csv/course.csv", "src/csv/modules.csv"));
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
}

package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RecSys {

    StudentGrades student;
    ArrayList<Semester> semesters = new ArrayList<Semester>();

    public RecSys(int id) throws FileNotFoundException {
        student = new StudentGrades(id, "src/csv/students.csv", "src/csv/grades.csv");
        for (int i = 0; i < student.getNumberOfSemesters(); i++) {
            semesters.add(new Semester(30, student.getCourseCode(i), student.getSemester(i), "src/csv/course.csv", "src/csv/modules.csv"));
        }
    }

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

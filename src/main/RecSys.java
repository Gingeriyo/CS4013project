package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RecSys {

    StudentGrades student;
    ArrayList<Semester> semesters = new ArrayList<Semester>();

    /**
     * This is the constructor for RecSys.
     * This class acts as a frontend for the GUI and for the Menu class to instantiate.
     * It creates a StudentGrades object, and then Semester objects for each semester the student has done.
     * @param id The ID of the student.
     * @throws FileNotFoundException
     */
    public RecSys(int id) throws FileNotFoundException {
        student = new StudentGrades(id, "src/csv/students.csv", "src/csv/grades.csv");
        for (int i = 0; i < student.getNumberOfSemesters(); i++) {
            Semester adding = new Semester(30, student.getCourseCode(i), student.getSemester(i), "src/csv/course.csv", "src/csv/modules.csv");
            adding.gradeCalc(student.getResults(i));
            semesters.add(adding);
        }
    }

    /**
     * This method returns basic information about all of the modules a student has done.
     * @return A String Array of the details in the order of the module code, the credits and the name.
     */
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

    /**
     * This method returns basic information about all of the modules a student has done ine a single semester.
     * @param sem The semester to choose from.
     * @return A String Array of the details in the order of the module code, the credits and the name.
     */
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

    /**
     * This method returns the information about a student.
     * Most of it is derived from the super constructor in StudentGrades for the Student class.
     * It is used in combination with transcript() and transcriptSingleSem().
     * @return A single string of all the information about a student.
     */
    public String getStudentInfo() {
        String temp = "";
        temp += String.format("%-15s", "Name:") + "|  " + student.getName() + "\n" +
        String.format("%-15s", "Email:") + "|  " +  student.getEmail() + "\n" +
        String.format("%-15s", "Phone Number:") +  "|  " + student.getphoneNumber() + "\n" +
        String.format("%-15s", "Status:") +  "|  " + student.getStudentType() + ", " + student.isAttending() +
        "\nAddress:\n" + student.getAddress() + "\n\n";
        return temp;
    }

    public Semester getSemester(int sem) {
        return semesters.get(sem);
    }

    /**
     * This method produces information about a semester for use within a transcript.
     * This includes the module names and credits, the student's grade and the comment
     * left by a faculty member, telling them whether to repeat the module or link in to a
     * different module. The QCA for the semester is also represented. This method is used
     * in combination with transcript and transcriptSingleSem().
     * @param sem The semester to return the information from.
     * @return A single string of information.
     */
    public String transcriptSemesterInfo(int sem) {
        String temp = "";
        Semester tempsem = semesters.get(sem);
        ArrayList<Module> mods = tempsem.getModules();
        
        for (int i = 0; i < mods.size(); i++) {
            temp += mods.get(i).getClassCode() + "     |  " +
            String.format("%-10s", mods.get(i).getCredit()) + "|  " +
            String.format("%-50s", mods.get(i).getName()) + "|  " +
            String.format("%-5s" ,tempsem.getSingleGrade(i)) +
            String.format("%-20s", student.getComments(sem)[i]) + "\n";
        }

        temp += "--------------------" + "\n" +
        tempsem.getAttendedHours() + " Attended Hours\n" +
        "QCA:" + tempsem.QCACalc() + "\n\n";

        return temp;
    }

    /**
     * This method returns a transcript for only a single semester.
     * @param sem The semester to print a transcript from.
     * @return A single string containing the transcript.
     */
    public String transcriptSingleSem(int sem) {
        return getStudentInfo() + "\n" + transcriptSemesterInfo(sem);
    }

    /**
     * This method returns an entire student transcript as a single string.
     * It calls the transcriptSingleSem() method for each semester the student has done,
     * and returns the cumulative QCA for the student at the top of the transcript.
     * @return A single string containing the transcript.
     */
    public String transcript() {
        String temp = "";
        double QCA = 0.0;
        for (int i = 0; i < semesters.size(); i++) {
            temp += transcriptSemesterInfo(i);
            QCA += semesters.get(i).QCACalc();
        }
        return getStudentInfo() + String.format("%-15s", "QCA: ") + "|  " + ((double)Math.round((QCA / semesters.size()) * 100d) / 100d) + "\n\n" + temp;
    }
}

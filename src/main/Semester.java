package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Semester {

    // These data fields contain basic information about a course.
    private int attendedHours;
    private int non_QualityHours;
    private String courseCode;
    private String nameOfCourse;
    private int years;
    private String courseDirector;
    private String email;

    private String[] arr_grade = {"NG", "F", "D2", "D1", "C3", "C2", "C1", "B3", "B2", "B1", "A2", "A1"};
    
    HashMap<String, Double> Grade = new HashMap<>(){{
        put("A1", 4.0);
        put("A2", 3.6);
        put("B1", 3.2);
        put("B2", 3.0);
        put("B3", 2.8);
        put("C1", 2.6);
        put("C2", 2.4);
        put("C3", 2.0);
        put("D1", 1.6);
        put("D2", 1.2);
        put("F", 0.0);
        put("NG", 0.0);
        put("N/A", 0.0);
    }};

    // Checks if a string is an integer.
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    // These are important lists for storing the required elements for QCA calculation
    // and for displaying on a transcript.
    // Grades stores, well, grades, as strings (e.g. A1, A2, etc.).
    // moduleCodes is a list for storing the codes for the modules for a semester.
    // mods is for storing the modules as objects, which contain important info
    // like how many credits they're worth and their name.
    ArrayList<String> Grades = new ArrayList<String>();
    ArrayList<String> moduleCodes = new ArrayList<String>();
    ArrayList<Module> mods = new ArrayList<Module>();

    /**
     * This is the constructor for the Semester object.
     * It is instantiated for every semester that the student does in RecSys.
     * It contains the grades and QCA for a single semester, and gets the informatiom about the course
     * the semester is for from course.csv.
     * 
     * Here is a line from course.csv:
     * LM121,Computer Science Common Entry,4,Dr Emil Vassev,emil.i.vassev@ul.ie,CS4012CS4141CS4221ET4011MA4111,CS4043CS4222CS4182ET4162MA4402,
     * 
     * The 1st token is the course code.
     * The 2nd is the name.
     * The 3rd is the length of the course in years (4 years in this case).
     * The 4th is the course director.
     * The following tokens are the modules, in order, for each semester.
     * In this case there are 2 semesters, so there are 2 tokens with the module codes.
     * Each module code is 6 characters in length.
     * 
     * This constructor also uses modules.csv, and a semester object will hold a list of them.
     * The file contains the grade to mark scheme for modules. Every module must include
     * "NG", "F", "D2", "D1", "C3", "C2", "C1", "B3", "B2", "B1", "A2" and "A1" grades.
     * The 1st token after the module code represents the credits of the module.
     * The numbers after correspond to each grade respectively.
     * Dissertations and thesis also fall under this category.
     * @param attendedHours The amount of hours for this semester.
     * @param course The course code.
     * @param semNum The number the semester is for.
     * @param coursePath The path to the course.csv file.
     * @param modulePath The path to the modules.csv file.
     * @throws FileNotFoundException
     */
    public Semester(int attendedHours, String course, int semNum, String coursePath, String modulePath) throws FileNotFoundException {
        this.attendedHours = attendedHours;
        Scanner courseReader = new Scanner(new File(coursePath));
        courseReader.useDelimiter(",");
        boolean reading = true;
        String temp;

            while (reading) {
            temp = courseReader.next();
            if (course.equals(temp.replace("\n", ""))) {
                String[] details = courseReader.nextLine().split(",");
                courseCode = details[0];
                nameOfCourse = details[1];
                years = Integer.parseInt(details[2]);
                courseDirector = details[3];
                email = details[4];
                String[] arr_modCodesStore = details[5 + semNum].split("(?<=\\G.{6})");
                for (int i = 0; i < arr_modCodesStore.length; i++) {
                    mods.add(new Module(arr_modCodesStore[i], modulePath));
                }

                reading = false;
            }
        }
    }

    /**
     * This method calculates the results inside a semester object and converts
     * them to grades that can be displayed on the transcript. These results by the
     * modules contained within the semester object and must be in correct order
     * to work. The first result in the String array parameter must be for the first
     * module in the associated semester in the course.csv file. This method must be
     * called if the user wishes to print a student transcript.
     * @param results The string array containing the results.
     */
    public void gradeCalc(String[] results) {
        int p = 0;
        for (int i = 0; i < mods.size(); i++) {
            int[] temp = mods.get(i).getMarkingScheme();
            if (isInteger(results[i])) {
                p = 0;
                for (p = 0; p < temp.length; p++) {
                    if (Integer.parseInt(results[i]) < temp[p]) {
                        break;
                    }
                }
                Grades.add(arr_grade[p - 1]);
            }
            else {
                Grades.add("N/A");
            }
        }
    }

    // This method returns the points associated with a grade.
    // Passing in "A1" will return 4.0.
    public double getGrade(String name) {
        return this.Grade.get(name);
    }

    /**
     * This method returns the QCA for this semester.
     * The gradeCalc() method must be called before hand for this method to work.
     * It uses a standard formula for calculating a QCA.
     * @return a douhle containing the QCA for this semester.
     */
    public double QCACalc() {
        double result = 0;
        double scale = 0;
        double QCS = 0.0;

        for(int i =  0; i < Grades.size(); i++) {
            QCS = QCS + getGrade(Grades.get(i)) * mods.get(i).getCredit();
        }

        double QCA = QCS / (attendedHours - non_QualityHours);
        scale = Math.pow(10,2); //use this for rounding
        result = Math.round(QCA * scale) / scale;

        return result;
    }

    /**
     * This method prints every grade the student has earned within this semester.
     * @return A single string if grades separated by spaces.
     */
    public String getGrades() {
        String temp = "";
        for (String s : Grades) {
            temp += s + " ";
        }
        return temp;
    }

    public String getSingleGrade(int mod) {
        return Grades.get(mod);
    }

    public int getAttendedHours() {
        return attendedHours;
    }

    public ArrayList<Module> getModules() {
        return mods;
    }

    public String getModuleName(int num) {
        return mods.get(num).getName();
    }

    public void addModule(Module m) {
        mods.add(m);
    }

    public String getCourseDirector() {
        return courseDirector;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public String getEmail() {
        return email;
    }

    public int getYears() {
        return years;
    }
}




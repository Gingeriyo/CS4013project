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

    // This is the constructor for a semester.
    // It takes in the amount of attended hours, the course it's a part of, the semester
    // number (starts from 0) and the filepaths for course.csv and modules.csv.
    // This will find the course and record its information via a scanner.
    // The module codes will then be split and used to find them in the modules.csv file,
    // creating individual module objects for each of them.
    // These are added to the mods ArrayList.
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

    // This method will add the grades from a string of results to the
    // Grades ArrayList.
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

    // Calculates the QCA for a semester.
    //did one quick change here it should be called QCS not QPV Thats something else
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




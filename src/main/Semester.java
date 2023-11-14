package main;
import java.util.HashMap;
import java.util.*;

public class Semester {
    private int attendedHours;
    private int non_QualityHours;
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
        put("I", 0.0);
        put("F", 0.0);
        put("NG", 0.0);
    }};


    ArrayList <String> Grades = new ArrayList<>();
    ArrayList<Module> mods = new ArrayList<Module>();



    public Semester(int attendedHours) {
        this.attendedHours = attendedHours;
    }
    public double getGrade(String Name) {
        return this.Grade.get(Name);
    }
    public double QCACalc(ArrayList<Module> credits,ArrayList <String> Grade) {
        //very basic implementation
        double result = 0;
        double scale = 0;
        double QPV = 0.0;

        for(int i =  0; i<Grades.size();i++) {
            QPV = QPV+ getGrade(Grades.get(i)) * credits.get(i).getCredit();
        }
        double QCA = QPV/(attendedHours-non_QualityHours);
        scale = Math.pow(10,2);//use this for rounding
        result = Math.round(QCA * scale)/scale;

        return result;

    }
    public ArrayList<String> getGrades() {
        return Grades;
    }
    public void addGrade(String grade) {
        Grades.add(grade);
    }

    public int getAttendedHours() {
        return attendedHours;
    }

    public ArrayList<Module> getCredits() {
        return mods;
    }
    public void addModule(Module m) {
        mods.add(m);
    }


    public Semester() {}

    }




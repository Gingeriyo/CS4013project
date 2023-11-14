package main;
import java.util.HashMap;
import java.util.*;

public class Semester {
    private int attendedHours;
    HashMap<String, Double> Grade = new HashMap<>();


    ArrayList <String> Grades = new ArrayList<>();
    ArrayList<Module> mods = new ArrayList<Module>();




    {

        Grade.put("A1",4.0);
        Grade.put("A2",3.6);
        Grade.put("B1",3.2);
        Grade.put("B2",3.0);
        Grade.put("B3",2.8);
        Grade.put("C1",2.60);
        Grade.put("C2",2.4);
        Grade.put("C3",2.0);
        Grade.put("D1",1.6);
        Grade.put("D2",1.2);
        Grade.put("F",0.0);
        Grade.put("NG",0.0);


    }

    public Semester(int attendedHours) {
        this.attendedHours = attendedHours;
    }
    public double getGrade(String Name) {
        return this.Grade.get(Name);
    }
    public double QCACalc(ArrayList<Module> credits,ArrayList <String> Grade, int sumAttended, int nonQ) {
        //very basic implementation
        double result = 0;
        double scale = 0;
        for(int i =  0; i<Grades.size();i++) {
            double QCA = (getGrade(Grades.get(i)) * credits.get(i).getCredit()) / (sumAttended - nonQ);

            result = result+QCA;
        }
        scale = Math.pow(10,2);//use this for rounding
        result = Math.round(result * scale)/scale;

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




package main;

import java.util.ArrayList;

public class Tester {
    public static void main(String []  args) {
        Semester sem = new Semester(30);
        Module maths = new Module("Maths",6,"idk");
        Module intro = new Module("intro",6,"LM121");
        Module bus = new Module("business",6,"who knows");
        Module economic = new Module("economics",6,"LM121");
        Module databases = new Module("Databases",6,"LM121");
        sem.addGrade("A1");
        sem.addGrade("A2");
        sem.addGrade("A2");
        sem.addGrade("B3");
        sem.addGrade("B1");// these are my grades so it should calc 3.44
        sem.addModule(maths);
        sem.addModule(intro);
        sem.addModule(bus);
        sem.addModule(economic);
        sem.addModule(databases);
        ArrayList <String> Grades = new ArrayList<>();
        Grades.add("A1");
        Grades.add("A2");
        Grades.add("A2");
        Grades.add("B3");
        Grades.add("B1");
        ArrayList <java.lang.Module> mods = new ArrayList<>();

        int notattended = 0;
        double QCA=sem.QCACalc(sem.getCredits(),sem.getGrades(),sem.getAttendedHours(),notattended);
        System.out.println(QCA);
    }
}

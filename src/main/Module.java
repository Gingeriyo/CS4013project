package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Module {
    private String name;
    private int credit;
    private String moduleCode;
    private int[] markingScheme = new int[12];

    public Module(String code, String path) throws FileNotFoundException {
        Scanner modReader = new Scanner(new File(path));
        modReader.useDelimiter(",");
        boolean reading = true;
        String temp;

        while (reading) {
            temp = modReader.next();
            
            if (code.equals(temp.replace("\n", ""))) {
                String[] details = modReader.nextLine().split(",");
                System.out.println(Arrays.toString(details));
                moduleCode = code;
                name = details[1];
                credit = Integer.parseInt(details[2]);
                for (int i = 0; i < details.length - 3; i++) {
                    markingScheme[i] = Integer.parseInt(details[i + 3]);
                }
                reading = false;
            }
        }
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public String getClassCode() {
        return moduleCode;
    }

    public int[] getMarkingScheme() {
        return markingScheme;
    }


    
//returns 3d array with the specified information once you input the users id
//the info returned isthe code for the modules ,credits and name for all 
//the modules the user does
//right now it isint done as the semester is hard coded to be 0 so i might make it so 
// you have to specify the semester as well
//it should work now
public static String [] leo (int id,int semester)throws FileNotFoundException {
    String [] sab = new String[100];
    ArrayList<Module> sm = new ArrayList<>();
    StudentGrades grade = new StudentGrades(id, "src/csv/students.csv", "src/csv/grades.csv");
    String coursecode =grade.getCourseCode(semester);
    Semester sem = new Semester(30, coursecode, semester, "src/csv/course.csv", "src/csv/modules.csv");
    sm = sem.getModules();
    for(int i = 0;i<sm.size();i++) {
        int credits =sm.get(i).getCredit();
        String name =sm.get(i).getName();
        String code = sm.get(i).getClassCode();
        sab[i+(2*i)] = code;
        sab[i+1+(2*i)] = credits+"";
        sab[i+2+(2*i)] = name;
        
    }
    return sab;
}
}
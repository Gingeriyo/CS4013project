package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Module {
    private String name;
    private int credit;
    private String moduleCode;
    private int[] markingScheme = new int[12];

    /**
     * This is the constructor for the Module class.
     * It takes in parameters via a csv file filled with information about modules.
     * 
     * The csv file contains the grade to mark scheme for modules. Every module must include
     * "NG", "F", "D2", "D1", "C3", "C2", "C1", "B3", "B2", "B1", "A2" and "A1" grades.
     * The 1st token after the module code represents the credits of the module.
     * The numbers after correspond to each grade respectively.
     * Dissertations and thesis also fall under this category.
     * @param code The module code.
     * @param path The file path to the csv file.
     * @throws FileNotFoundException
     */
    public Module(String code, String path) throws FileNotFoundException {
        Scanner modReader = new Scanner(new File(path));
        modReader.useDelimiter(",");
        boolean reading = true;
        String temp;

        while (reading) {
            temp = modReader.next();
            
            if (code.equals(temp.replace("\n", ""))) {
                String[] details = modReader.nextLine().split(",");
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
}
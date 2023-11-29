package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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
            System.out.println(code + "Input");
            temp = modReader.next();
            System.out.println(temp);
            
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
}
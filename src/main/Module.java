package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Module {
    private String name;
    private int credit;
    private String moduleCode;
    private int[] markingScheme = new int[12];

    public Module(String code, String path) throws FileNotFoundException {
        String codeID = code;
        Scanner modReader = new Scanner(new File(path));
        modReader.useDelimiter(",");
            while (modReader.hasNext()) {
            String ID = modReader.next();
            
            if (Objects.equals(codeID, ID)) {
                String[] details = modReader.nextLine().split(",");
                moduleCode = details[0];
                name = details[1];
                credit = Integer.parseInt(details[2]);
                for (int i = 0; i < details.length - 3; i++) {
                    markingScheme[i] = Integer.parseInt(details[i + 3]);
                }
                break;
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
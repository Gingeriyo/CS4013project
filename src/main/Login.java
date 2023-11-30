package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {

    Scanner csvReader;
    private String identifier;
    private String pw;
    
    // Student Login (Uses ID)
    public Login(int id, String pw) throws FileNotFoundException {
        csvReader = new Scanner(new File("src/csv/studentLogin.csv"));
        identifier = String.valueOf(id);
        this.pw = pw;
    }

    // Faculty Login (Uses email)
    public Login(String email, String pw) throws FileNotFoundException {
        csvReader = new Scanner(new File("src/csv/facultyLogin.csv"));
        identifier = email;
        this.pw = pw;
    }

    public boolean read() {
        csvReader.useDelimiter(",");
        boolean reading = true;
        String temp;

        while (reading) {
            temp = csvReader.next();
            if (identifier.equals(temp.replace("\n", "")) && pw.equals(csvReader.next())); {
                return true;
            }
        }
        return false;
    }
}

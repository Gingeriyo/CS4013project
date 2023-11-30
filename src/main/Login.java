package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
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
        try {
            csvReader.useDelimiter(",");
            boolean reading = true;
            String temp;
            String temp2;
            boolean result = false;

            while (reading) {
                temp = csvReader.next();
                temp2 = csvReader.next();
                if (identifier.equals(temp.replace("\n", "")) && pw.equals(temp2)) {
                    result = true;
                    reading = false;
                }
            }
        return result;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}

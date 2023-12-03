package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Login {

    private Scanner csvReader;
    private String identifier;
    private String pw;
    private String path;
    private boolean loggedIn = false;
    
    /**
     * This is the constructor for the Login class.
     * It accepts a username and password.
     * Since a student ID can only be an integer and contain no characters,
     * there are 2 different constructors to discern whether to log in as a student or faculty member,
     * with this one accepting an integer for students.
     * @param id the identifier, in this case, an integer.
     * @param pw the password of the student.
     * @throws FileNotFoundException
     */
    public Login(int id, String pw) throws FileNotFoundException {
        path = "src/csv/studentLogin.csv";
        csvReader = new Scanner(new File(path));
        identifier = String.valueOf(id);
        this.pw = pw;
    }

    /**
     * This is the constructor for the Login class.
     * It accepts a username and password.
     * Since a student ID can only be an integer and contain no characters,
     * there are 2 different constructors to discern whether to log in as a student or faculty member,
     * with this one accepting a String for faculty.
     * @param id the identifier, in this case, a string.
     * @param pw the password of the faculty member.
     * @throws FileNotFoundException
     */
    public Login(String email, String pw) throws FileNotFoundException {
        path = "src/csv/facultyLogin.csv";
        csvReader = new Scanner(new File(path));
        identifier = email;
        this.pw = pw;
    }

    /**
     * This method returns whether the arguments created with the login object have
     * worked.
     * @return true for logging in, false for not.
     */
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
            loggedIn = result;
            return result;
        }
        catch (NoSuchElementException e) {
            loggedIn = false;
            return false;
        }
    }

    /**
     * This method changes the password of the person signed in.
     * It can only be done if read() returns true.
     * @param new_pw the new password the user wants to set.
     */
    public void changePassword(String new_pw) {
        if (loggedIn) {
            try {
            Scanner scan = new Scanner(new File(path));
            String write = "";
            //in this part i split up the next line and if the username of the 
            //line = the provoided then we check if the password provoided matches 
            //the old one if so we change the password 
            while(scan.hasNextLine()) {
                String [] parts = scan.nextLine().split(",");
                if(parts[0].equals(identifier)){
                    if(parts[1].equals(pw)) {
                        parts[1] = new_pw;
                    }
                }
                //here im just putting the segments into a string to be written to later
                    for(int i  = 0; i < parts.length; i++) {
                        write += parts[i]+",";
                        
                    }
                    write += "\n";
            }
            //i use a buffered writer to then finally write to the csv file 
            //with the updated info 
            scan.close();
            BufferedWriter buff = new BufferedWriter(new FileWriter(path));
            buff.write(write);
            buff.close();
            } 
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}

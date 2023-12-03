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
    
    // Student Login (Uses ID)
    public Login(int id, String pw) throws FileNotFoundException {
        path = "src/csv/studentLogin.csv";
        csvReader = new Scanner(new File(path));
        identifier = String.valueOf(id);
        this.pw = pw;
    }

    // Faculty Login (Uses email)
    public Login(String email, String pw) throws FileNotFoundException {
        path = "src/csv/facultyLogin.csv";
        csvReader = new Scanner(new File(path));
        identifier = email;
        this.pw = pw;
    }

    // Call this method to login.
    // Returns false if no element is found.
    // Takes the 2 elements from a Login CSV
    // and checks if they match what was passed in with the constructor.
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
    //i think it works pretty good no probelms from what i can see but 
    // i didnt heavily test it so tell me if any bugs are found 
}

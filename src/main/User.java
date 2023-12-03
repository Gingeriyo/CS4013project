package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class User {
    private String[] titles = { "Mr.", "Mrs.", "Mx." };
    private int myTitle;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String eircode;

    public String getName() {
        return titles[myTitle] + " " + firstName + " " + lastName;
    }

    public String getEmail(){
        return email;
    }

    public int getphoneNumber(){
        return phoneNumber;
    }

    public String getAddress() {
        return addressLine1 + ",\n" +
        addressLine2 + ",\n" +
        addressLine3 + ",\n" +
        addressLine4 + "\n" +
        eircode;
    }

    public static void changePassword(String oldPassword,String newPassword,String username) {
        try{
        //this checks if student or teacher by checking if email or not
        //it does this by checking for @sign as a student number cant have 
        //that and an email is required to have that 
        String location = "src/csv/studentLogin.csv";
        if(username.contains("@")) {
            location = "src/csv/facultyLogin.csv";
        }
        //this all goes through the file 
        Scanner scan = new Scanner(new File(location));
        String write = "";
        //in this part i split up the next line and if the username of the 
        //line = the provoided then we check if the password provoided matches 
        //the old one if so we change the password 
        while(scan.hasNextLine()) {
            String [] parts = scan.nextLine().split(",");
            if(parts[0].equals(username)){
                if(parts[1].equals(oldPassword)) {
                    parts[1] = newPassword;
                }
            }
            //here im just putting the segments into a string to be written to later
                for(int i  = 0; i<parts.length;i++) {
                    write+= parts[i]+",";
                    
                }
                write += "\n";
        }
        //i use a buffered writer to then finally write to the csv file 
        //with the updated info 
        scan.close();
        BufferedWriter buff = new BufferedWriter(new FileWriter(location));
        buff.write(write);
        buff.close();
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    //i think it works pretty good no probelms from what i can see but 
    // i didnt heavily test it so tell me if any bugs are found 
}

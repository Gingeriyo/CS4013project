package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public abstract class Student extends User {
    // Personal Information about the Student.
    // These can be changed if the student is logged in.
    private int studentID;
    private String[] titles = { "Mr.", "Mrs.", "Mx." };
    private int myTitle;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String[] types = { "Undergraduate", "Postgraduate", "PhD", "Masters" };
    private int studentType;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String eircode;
    private boolean currentStudent = false;

    // This constructor adds all the information from the csv to the data fields
    // of the student.
    public Student(int id, String path) throws FileNotFoundException {
        String IDinput = Integer.toString(id);
        Scanner csvReader = new Scanner(new File(path));
        csvReader.useDelimiter(",");
        boolean reading = true;
        String temp;

        while (reading) {

            temp = csvReader.next();
            if (IDinput.equals(temp.replace("\n", ""))) {
                String[] details = csvReader.nextLine().split(",");
                studentID = Integer.parseInt(IDinput);
                myTitle = Integer.parseInt(details[1]);
                firstName = details[2];
                lastName = details[3];
                phoneNumber = Integer.parseInt(details[4]);
                email = details[5];
                studentType = Integer.parseInt(details[6]);
                addressLine1 = details[7];
                addressLine2 = details[8];
                addressLine3 = details[9];
                addressLine4 = details[10];
                eircode = details[11];
                currentStudent = Boolean.parseBoolean(details[12]);
                reading = false;
            }
        }
        csvReader.close();
    }

    public int getID() {
        return studentID;
    }

    @Override
    public String getName() {
        return titles[myTitle] + " " + firstName + " " + lastName;
    }

    public String isAttending() {
        if (currentStudent) {
            return "Currently Attending";
        }
        else {
            return "Not Currently Attending";
        }
    }

    @Override
    public String getAddress() {
        return addressLine1 + ",\n" +
        addressLine2 + ",\n" +
        addressLine3 + ",\n" +
        addressLine4 + "\n" +
        eircode;
    }

    @Override
    public String getEmail(){
        return email;
    }

    @Override
    public int getphoneNumber(){
        return phoneNumber;
    }

    public String getStudentType() {
        return types[studentType];
    }
}
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Student {
    // Personal Information about the Student.
    // These can be changed if the student is logged in.
    private int studentID;
    private String[] titles = {"Mr.", "Mrs.", "Mx."};
    private int studentTitle;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String[] types = {"Undergraduate", "Postgraduate", "PhD", "Masters"};
    private int studentType;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String eircode;
    private boolean currentStudent = false;

    // This constructor adds all the information from the csv to the data fields
    // of the student.
    // NEEDS TO BE FIXED!
    public Student(int id, String path) throws FileNotFoundException {
        String IDinput = Integer.toString(id);
        Scanner csvReader = new Scanner(new File(path));
        csvReader.useDelimiter(",");

        while (csvReader.hasNext()) {
            System.out.println(csvReader.next());
            
            // This if statement does not work and the comparison between
            // IDinput and the csvReader.next() never returns true.
            if (Objects.equals(IDinput, csvReader.next())) {
                String[] details = csvReader.nextLine().split(",");
                studentID = Integer.parseInt(details[0]);
                studentTitle = Integer.parseInt(details[1]);
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
                currentStudent = Boolean.parseBoolean(details[4]);
                break;
            }
        }
    }

    public String getName() {
        return firstName + lastName;
    }

    public boolean isAttending() {
        return currentStudent;
    }

}
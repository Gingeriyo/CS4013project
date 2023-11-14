package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Student {
    // Personal information about the Student.
    // These can be changed if the student is logged in.
    Scanner csvScan;
    private String title;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private String[] studentType = {"Undergraduate", "Postgraduate", "PhD", "Masters"};
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String eircode;
    private int studentID;
    private boolean currentStudent = true;
    private String currentDate = "13/Nov/2023";

    public String transcript(){
        String transcript;
        String semesterTranscript;

        return "";
    }


    public Student(int IDinput, String path) throws FileNotFoundException {
        csvScan = new Scanner(new File(path));
    }

}
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class Menu {
    public static void main(String[] args) {

        // This will be the class to contain the main method
        // (Or not depending on if we require more classes).
        // It will print out all the data to produce a transcript.

        /*
         * testing the student transcript
         * 
         * Student me = new Student();
         * 
         * System.out.println(me.transcript());
         * 
         */

        /*
         * this is the first thing that pops up when the project is run
         * it creates a scanner and asks if you are logging in as a student or faculty
         * member
         * if a student is logging in then it asks for an id number.
         * If faculty is logging in it asks for first and last names and checks through
         * a csv file of faculty to grant access
         * if anyone has a better check for this feel free to change it cuz there is a
         * fault where a student can just enter
         * a lecturers name for access
         *
         *
         * NOTE FOR MYSELF: when an id number is put in the system should scan through a
         * csv file of students
         * and it pull the names and addresses from there. the userstudent class should
         * hold a method for this scan
         * UserStudent should grant the permissions to change personal details like
         * address etc.
         */
        final String firstName;
        final String lastName;
        final int idNumber;
        final Scanner in;

        try {
            in = new Scanner(System.in);
            boolean more = true;
            BufferedReader br = new BufferedReader(new FileReader("students.csv")) {

            };

            while (more) {
                System.out.println("S)tudent or F)aculty");
                String command = in.nextLine().toUpperCase();
                int newLine;

                if (command.equals("S")) {

                    System.out.println("Enter StudentID number");
                    String line = in.nextLine();
                    if ((line = br.readLine()) != null) {
                        System.out.println("welcome");
                    }

                } else if (command.equals("F")) {
                    System.out.println("Enter First name and Last name");
                    String nameLine = in.nextLine();

                } else {
                    System.out.println("please state if you are S)tudent or F)aculty");
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

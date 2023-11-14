package main;

import java.io.File;
import java.util.Scanner;
import java.io.*;

public class User {
    // This is the parent class for all types of users.
    // A user is used alongside execution to determine what permissions
    // they have.

    private String firstName;
    private String lastName;
    private int idNumber;
    private Scanner in;

    public studentLogin(String firstName, String lastName, int idNumber) {
        try {
            in = new Scanner(System.in);
            boolean more = true;

            while (more) {
                System.out.println("S)tudent or F)aculty");
                String command = in.nextLine().toUpperCase();

                if (command.equals("S")) {
                System.out.println("Enter StudentID number");
                String line = in.nextLine();

            } else if (command.equals("F")) {
                System.out.println("Enter First name and Last name");
                String nameLine = in.nextLine();

            }

            }


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

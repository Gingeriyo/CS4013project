package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Faculty extends User {
    // The faculty user has the ability to add and modify semester objects
    // to a StudentGrades object.

    private String[] titles = {"Dr.", "Mr.", "Mrs.", "Mx."};
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

    @Override
    public String getName() {
        return titles[myTitle] + " " + firstName + " " + lastName;
    }

    @Override
    public String getAddress() {
        return addressLine1 + ",\n" +
        addressLine2 + ",\n" +
        addressLine3 + ",\n" +
        addressLine4 + "\n" +
        eircode;
    }

    // email is used for login
    @Override
    public String getEmail(){
        return email;
    }

    @Override
    public int getphoneNumber(){
        return phoneNumber;
    }

    public static void updateGrade(String id,String courseCode,int semNum,String modCode,double Grade) {
        try {
            Scanner s = new Scanner(new File("src/csv/grades.csv"));
            String write = "";
            Semester sem = new Semester(30, courseCode, semNum, "src/csv/course.csv", "src/csv/modules.csv");
            int position = 0;
            ArrayList <Module> mod = sem.getModules();
            int length = mod.size();
            boolean nomore = false;
            
            for(int i = 0;i<mod.size();i++) {
                if(mod.get(i).getClassCode().equals(modCode)) {
                    position = i;
                }
                //all of this code above gets the position of the grade that needs to be replaced

            }
            int pos = position + 4;
            if(semNum>0) {
                pos = 4*(1+semNum)+(length*semNum)+position;
            }
            //this code is a formula i came up with to get the position of the code
            //given differnt semesters ive tested it and it seems to work
            //maybe ive missed something but it worked for my tests anyway
            
            while(s.hasNextLine()) {
                String [] parts = s.nextLine().split(",");
                if(nomore == false){
                if(parts[pos-position -3]!= courseCode) {
                    for(int i = pos;i<parts.length;i++) {
                        if(parts[i].equals(courseCode)) {
                            pos= i+3+ (semNum* length)+4*(1+(semNum-1))+position;
                            nomore = true;
                            break;
                        }
                    }
                    }
                }
                //this part is a bit of a mess right now but ill try and reduce the statements
                //in the future please dont panic ill just leave it like this until i can so
                //it at least works for now. The purpose of this block of code is to detect and 
                //update the position of the grade if the student has changed courses between the years
                //it should work fine but ill do more testing this is the most experimental part of 
                //the code right now so bugs are expected but ill try and get rid of them soon
                if(parts[0].equals(id)) {

                
                parts[pos] = Grade+"";
                
                }
                for(int i  = 0 ;i<parts.length;i++) {
                    write += parts[i]+",";
                }
                write += "\n";
            }
            //this whole part finds the proper id and changes the grade given the position i found 
            //once this happens the for loop loads all the parts array into a string
            //which below is then appended or written to the csv file thereby changing its contents 
            s.close();
            BufferedWriter buff = new BufferedWriter(new FileWriter("src/csv/grades.csv"));
            buff.append(write);
            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //why i wrote the code this way 
        //im aware the method using 5 parameters is quite a lot but i cant seem
        //to figure out how to do it with less. i imagine one of you can 
        //maybe see a way to do it without the params semnum and course code 
        //but for the moment i cant but i will happily update it if someone points out how.

        //the first assumption i made is that the grades in the grade csv file
        //are ordered the same way the modules are in the module array that
        //sem.getmodules() returns so with that assumption i just needed to find 
        // the position of the module specifed using the module code by comparing the 
        //module code with the one given.

        // The second assumption that needs to be true for this
        //code to work properly at the moment is in the grades csv file there is an extra space,
        //between each semester if this wasnt on purpose even though the data already in there
        //before i modified anything suggested that it is then the code will need to be changed.

        //how to use: 
        //you need the student id of the student grade you want to change
        //you need the course code of the Module you want to change
        //you need the semester number of the of the module aka intro to programming for us was in sem 1
        //you need the module code of the module you want to change 
        //finally you need to input the grade you input 
        //a bit much i know but its all i could do 


    }
}


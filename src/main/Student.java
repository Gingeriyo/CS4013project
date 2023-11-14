package main;
public class Student {
    // Personal information about the Student.
    // These can be changed if the student is logged in.
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

        transcript = String.format(
                "+------------------------------------------------------------------------------------------------------------------------+\n" +
                "|                                     University of Limerick                                                             |\n" +
                "|                                                                                                                        |\n" +
                "|%s                          Student Full Transcript                                       %8d             |\n" +
                "|                                                                                                                        |\n" +
                "+------------------------------------------------------------------------------------------------------------------------+\n" +

                "Name       %S            %S                  %S\n" +
                "\n" +
                "Address     %S\n" +
                "\n" +
                "            %S\n" +
                "\n" +
                "            %S\n" +
                "                                                                                  Telephone  %d\n" +
                "            %S\n" +
                "\n" +
                "\n" +
                "\n",
                currentDate, studentID, title, firstName, lastName, addressLine1, addressLine2, addressLine3, phoneNumber, addressLine4
        );
        /*
        // removed from the above String.format because
        // batch? advisor? can we remove these?
                " Status     Current Student                                 Batch      23BSCGDEUFA\n" +
                " Course     COMPUTER GAMES DEVELOPMENT                      Advisor\n" +
                " Programme  Computer Games Development                      Award\n" +
                " Route      Computer Games Development                      Class",

         */

        /*
        // string.format will not work for this section of the transcript
        semesterTranscript = String.format(
                "+--------------------------------------------------------------------------------------------+---------------------------+\n" +
                "|2022/3         SEM1          Part   1                                                      |            Session To-Date|\n" +
                "|                                                                                            |                           |\n" +
                "|Module       Title                                Block11      Regn Type  Grade   Credits   |Factor      1.000          |\n" +
                "|                                                                                            |Att Hrs     30.00   30.00  |\n" +
                "|CS4012       REPRESENTATION AND MODELLING                        N             B2   6       |Cred Hours  30.00   30.00  |\n" +
                "|CS4141       INTRODUCTION TO PROGRAMMING                         N             A2   6       |Non-Q hours 0.00    0.00   |\n" +
                "|CS4221       FOUNDATIONS OF COMPUTER SCIENCE 1                   N             B1   6       |QCS         91.20   91.20  |\n" +
                "|ET4011       FUNDAMENTALS OF COMPUTER ORGANISATION               N             B2   6       |QCA         3.04    3.04   |\n" +
                "|MS4111       DISCRETE MATHEMATICS 1                              N             C2   6       |                           |\n" +
                "|                                                                                            |                           |\n" +
                "|                                                                                            |                           |\n" +
                "|                                                                                            |                           |\n" +
                "|                                                                                            |                           |\n" +
                "|                                                                                            |                           |\n" +
                "|                                                                                            |                           |\n" +
                "+--------------------------------------------------------------------------------------------+---------------------------+"
        );

         */

        return transcript;
    }


    
}
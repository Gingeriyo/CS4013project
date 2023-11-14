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

        return transcript;
    }


    
}
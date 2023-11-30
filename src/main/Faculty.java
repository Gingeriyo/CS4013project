package main;
public class Faculty extends User {
    // The faculty user has the ability to add and modify semester objects
    // to a StudentGrades object.


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

    @Override
    public String getName() {
        return titles[myTitle] + " " + firstName + " " + lastName;
    }

    @Override
    public String isAttending() {
        return "Faculty member";
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
}

package main;

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
    private boolean currentStudent;

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

    public String isAttending() {
        if (currentStudent) {
            return "Currently Attending";
        }
        else {
            return "Not Currently Attending";
        }
    }
}

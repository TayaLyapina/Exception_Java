package org.example.hw3;
import java.util.Date;

public class User {
    private String FirstName;
    private String LastName;
    private String Surname;
    private Date Birthdate;
    private long PhoneNumber;
    private String Gender;


    public User(String firstName, String lastName, String surname, Date birthdate, long phoneNumber, String gender) {
        FirstName = firstName;
        LastName = lastName;
        Surname = surname;
        Birthdate = birthdate;
        PhoneNumber = phoneNumber;
        Gender = gender;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getSurname() {
        return Surname;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public Date getBirthdate() {
        return Birthdate;
    }
}
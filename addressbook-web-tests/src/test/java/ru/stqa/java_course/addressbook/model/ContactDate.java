package ru.stqa.java_course.addressbook.model;

public class ContactDate {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;

    public ContactDate(String firstname, String lastname, String address, String mobile,
                       String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}

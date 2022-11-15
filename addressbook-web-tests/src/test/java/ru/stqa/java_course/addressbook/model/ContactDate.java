package ru.stqa.java_course.addressbook.model;

public class ContactDate {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String mobile;
    private String email;

    public ContactDate withId(int id) {
        this.id = id;
        return this;
    }

    public ContactDate withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactDate withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactDate withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactDate withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactDate withEmail(String email) {
        this.email = email;
        return this;
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
    public int getId() {
            return id;
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDate that = (ContactDate) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

//    public int getGroup() {
//        return ; }

}

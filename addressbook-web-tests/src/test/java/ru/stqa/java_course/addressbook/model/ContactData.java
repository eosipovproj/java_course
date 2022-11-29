package ru.stqa.java_course.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Column(name = "firstname")
    @Expose
    private String firstname;
    @Column(name = "lastname")
    @Expose
    private String lastname;
    @Type(type = "text")
    @Column(name = "address")
    @Expose
    private String address;
    @Type(type = "text")
    @Column(name = "mobile")
    @Expose
    private String mobile;
    @Type(type = "text")
    @Column(name = "email")
    @Expose
    private String email;
    @Type(type = "text")
    @Column(name = "work")
    @Expose
    private String workPhone;
    @Type(type = "text")
    @Column(name = "home")
    @Expose
    private String homePhone;
    @Type(type = "text")
    @Column(name = "phone2")
    @Expose
    private String secondaryHomePhone;
    @Transient
    private String allPhones;
    @Type(type = "text")
    @Column(name = "email2")
    @Expose
    private String email2;
    @Type(type = "text")
    @Column(name = "email3")
    @Expose
    private String email3;
    @Transient
    private String allEmails;
    @Type(type = "text")
    @Column(name = "photo")
    @XStreamOmitField
    private String photo;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withSecondaryHomePhone(String secondaryHomePhone) {
        this.secondaryHomePhone = secondaryHomePhone;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2= email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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
    public String getWorkPhone() {
        return workPhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getSecondaryHomePhone() {
        return secondaryHomePhone;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getAllEmails() {
        return allEmails;
    }
    public File getPhoto() {
        return new File(photo);
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

        ContactData that = (ContactData) o;

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

}

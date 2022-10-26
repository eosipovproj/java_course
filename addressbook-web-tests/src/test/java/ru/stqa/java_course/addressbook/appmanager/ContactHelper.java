package ru.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(String firstname, String lastname, String address, String mobile, String email) {
        type(By.name("firstname"), firstname);
        type(By.name("lastname"), lastname);
        type(By.name("address"), address);
        type(By.name("mobile"), mobile);
        type(By.name("email"), email);

    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }
}

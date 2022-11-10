package ru.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.java_course.addressbook.model.ContactDate;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactDate contactDate, boolean creation) {
        type(By.name("firstname"), contactDate.getFirstname());
        type(By.name("lastname"), contactDate.getLastname());
        type(By.name("address"), contactDate.getAddress());
        type(By.name("mobile"), contactDate.getMobile());
        type(By.name("email"), contactDate.getEmail());
//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactDate.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        } else
            Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeAllert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[5]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }
    public void returnHomePage(){
        click(By.linkText("home page"));
    }

    public void createContact(ContactDate contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        returnHomePage();
    }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactDate> getContactList() {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            ContactDate contact = new ContactDate(id, firstname, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}

package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        ContactDate contactDate = new ContactDate("Evgeniy-ED", "Osipov-ED", "Saint-Petersburg-ED",
                "+78199999999", "test_edited@test.ru");
        app.getContactHelper().fillContactForm(contactDate.getFirstname(), contactDate.getLastname(),
                contactDate.getAddress(), contactDate.getMobile(), contactDate.getEmail());
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();
    }
}

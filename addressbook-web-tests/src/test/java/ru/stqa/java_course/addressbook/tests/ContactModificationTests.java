package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                "+78112341123", "test@test.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnHomePage();
    }
}

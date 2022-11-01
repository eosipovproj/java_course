package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                    "+78112341123", "test@test.ru", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                "+78112341123", "test@test.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}

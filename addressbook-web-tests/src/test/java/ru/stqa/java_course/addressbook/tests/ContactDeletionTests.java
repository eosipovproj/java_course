package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                    "+78112341123", "test@test.ru", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAllert();
    }
}

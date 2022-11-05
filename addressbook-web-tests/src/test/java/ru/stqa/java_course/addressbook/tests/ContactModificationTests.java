package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if(! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            if (! app.getGroupsHelper().isThereAGroup()){
                app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
            }
            app.getNavigationHelper().goToHomePage();
            app.getContactHelper().createContact(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                    "+78112341123", "test@test.ru"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                "+78112341123", "test@test.ru"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}

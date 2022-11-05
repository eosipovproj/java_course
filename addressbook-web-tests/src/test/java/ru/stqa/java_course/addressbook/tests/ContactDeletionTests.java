package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupsHelper().isThereAGroup()){
            app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
        }
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                    "+78112341123", "test@test.ru"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAllert();
    }
}

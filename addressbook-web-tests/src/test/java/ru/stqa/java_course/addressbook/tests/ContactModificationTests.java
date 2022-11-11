package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if(! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            if (! app.getGroupsHelper().isThereAGroup()){
                app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
            }
            app.getNavigationHelper().goToHomePage();
            app.getContactHelper().createContact(new ContactDate("Evgeniy",
                    "Osipov", "Saint-Petersburg", "+78112341123", "test@test.ru"));
        }
        List<ContactDate> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactDate contact = new ContactDate(before.get(before.size() - 1).getId(),"Evgeniy-t",
                "Osipov-t", "Saint-Petersburg", "+781", "tet@test.ru");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactDate> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

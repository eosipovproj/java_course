package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupDate().withGroupname("test2").withHeader("test header").withFooter("test comment"));
        }
        app.goTo().homePage();
        if( app.contact().list().size() == 0) {
            app.contact().create(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
                    "+78112341123", "test@test.ru"));
        }
    }
    @Test(enabled = false)
    public void testContactDeletion () {
        List<ContactDate> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactDate> after = app.contact().list();

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}

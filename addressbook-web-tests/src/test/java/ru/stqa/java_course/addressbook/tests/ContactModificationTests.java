package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().list().size() == 0) {
            app.goTo().groupPage();
            if (app.group().list().size() == 0) {
                app.group().create(new GroupDate("test1", "test header", "test comment"));
            }
            app.goTo().homePage();
            app.contact().create(new ContactDate("Evgeniy",
                    "Osipov", "Saint-Petersburg", "+78112341123", "test@test.ru"));
        }
    }
    @Test()
    public void testContactModification() {
        List<ContactDate> before = app.contact().list();
        int index = before.size() - 1;
        ContactDate contact = new ContactDate(before.get(index).getId(),"Evgeniy-t",
                "Osipov-t", "Saint-Petersburg", "+781", "tet@test.ru");
        app.contact().modify(index, contact);
        app.goTo().homePage();
        List<ContactDate> after = app.contact().list();

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupDate().withGroupname("test2").withHeader("test header").withFooter("test comment"));
        }
        app.goTo().homePage();
        if( app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Evgeniy").withLastname("Osipov")
                    .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru"));
        }
    }
    @Test
    public void testContactDeletion () {
        Contacts before = app.contact().all();
        ContactDate deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

//        before.remove(deletedContact);
//        Assert.assertEquals(before, after);
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}

package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withGroupname("test2").withHeader("test header").withFooter("test comment"));
            }
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Evgeniy").withLastname("Osipov")
                    .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru"));
        }
    }
    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData mofifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(mofifiedContact.getId()).withFirstname("Evgeniy").withLastname("Osipov")
                .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru");
        app.contact().modify(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo( before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(mofifiedContact).withAdded(contact)));
    }
}

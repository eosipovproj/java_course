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
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupname("test1").withHeader("test header").withFooter("test comment"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Evgeniy").withLastname("Osipov")
                    .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru"));
        }
    }
    @Test(enabled = false)
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
    @Test
    public void testContactModificationDb() {
        Contacts before = app.db().contacts();
        ContactData mofifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(mofifiedContact.getId()).withFirstname("Evgeniy").withLastname("Test")
                .withMobile("+7(123)999999").withHomePhone("22-22-22").withWorkPhone("111-111")
                .withSecondaryHomePhone("333 333").withAddress("1231231, г.Тест-тест, ул.Тест, д.23к8, кв.1")
                .withEmail("1231@mail.ru").withEmail2("email@mail.ru").withEmail3("email3@mail.ru");
        app.contact().modify(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(mofifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}

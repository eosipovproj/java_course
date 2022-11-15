package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupDate().withGroupname("test2").withHeader("test header").withFooter("test comment"));
            }
            app.goTo().homePage();
            app.contact().create(new ContactDate().withFirstname("Evgeniy").withLastname("Osipov")
                    .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru"));
        }
    }
    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactDate mofifiedContact = before.iterator().next();
        ContactDate contact = new ContactDate().withId(mofifiedContact.getId()).withFirstname("Evgeniy").withLastname("Osipov")
                .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru");
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();

//        before.remove(index);
//        before.add(contact);
//        Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(before, after);
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(mofifiedContact).withAdded(contact)));
    }
}

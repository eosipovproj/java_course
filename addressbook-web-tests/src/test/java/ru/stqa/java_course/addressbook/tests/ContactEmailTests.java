package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmailTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Evgeniy").withLastname("Test")
                    .withEmail("1231@mail.ru ").withEmail2(" email@mail.ru "));
        }
    }
    @Test
    public void testContactEmail() {
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }
    private String mergeEmails(ContactDate contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    public static String cleaned (String email){
        return email.replaceAll("\\s", "");
    }

}
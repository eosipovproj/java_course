package ru.stqa.java_course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPropertysTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Evgeniy").withLastname("Test")
                    .withMobile("+7 (123)").withHomePhone("22-22-22").withWorkPhone(" 111-111 ")
                    .withSecondaryHomePhone("333 333").withAddress("1231231, г.Тест-тест, ул.Тест, д.23к8, кв.1")
                    .withEmail("1231@mail.ru ").withEmail2(" email@mail.ru "));
        }
    }
    @Test
    public void testContactPhone (){
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }
    private String mergePhones(ContactDate contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(),
                        contact.getWorkPhone(), contact.getSecondaryHomePhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPropertysTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }
    public static String cleanedPhones (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
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
                .map(ContactPropertysTests::cleanedEmails)
                .collect(Collectors.joining("\n"));
    }
    public static String cleanedEmails (String email){
        return email.replaceAll("^\\s", "").replaceAll("\\s$", "");
    }

    @Test
    public void testContactAddress() {
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), CoreMatchers.equalTo(contactInfoFromEditForm.getAddress()));
    }
}

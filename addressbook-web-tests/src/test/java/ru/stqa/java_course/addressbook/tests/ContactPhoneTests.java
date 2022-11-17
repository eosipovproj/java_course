package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactDate().withFirstname("Evgeniy").withLastname("Test")
                    .withMobile("+7 (123)").withHomePhone("22-22-22"));
        }
    }
    @Test
    public void testContactPhone (){
        ContactDate contact = app.contact().all().iterator().next();
        ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }
    private String mergePhones(ContactDate contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}

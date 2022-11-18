package ru.stqa.java_course.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{


    @BeforeMethod
        public void ensurePrecondition() {
            app.goTo().homePage();
            if (app.contact().all().size() == 0) {
                app.contact().create(new ContactDate().withFirstname("Evgeniy").withLastname("Test")
                        .withAddress("1231231, г.Тест-тест, ул.Тест, д.23к8, кв.1"));
            }
        }

        @Test
        public void testContactAddress() {
            ContactDate contact = app.contact().all().iterator().next();
            ContactDate contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
            assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        }
    }

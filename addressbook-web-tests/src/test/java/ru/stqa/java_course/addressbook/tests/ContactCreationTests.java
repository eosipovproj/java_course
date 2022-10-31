package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
            "+78112341123", "test@test.ru", "test1"), true);
  }
}

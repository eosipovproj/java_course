package ru.stqa.java_course.addressbook;

import org.testng.annotations.Test;


public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
            "+78112341123", "test@test.ru"));
    submitContactCreation();
    returnHomePage();
  }

}

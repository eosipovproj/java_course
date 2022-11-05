package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupsHelper().isThereAGroup()){
      app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
    }
    app.getContactHelper().createContact(new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
            "+78112341123", "test@test.ru"));
  }
}

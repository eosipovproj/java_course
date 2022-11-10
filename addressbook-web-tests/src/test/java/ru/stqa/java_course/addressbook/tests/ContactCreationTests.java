package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupsHelper().isThereAGroup()){
      app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactDate> before = app.getContactHelper().getContactList();
    ContactDate contact = new ContactDate("Evgeniy", "Osipov", "Saint-Petersburg",
            "+78112341123", "test@test.ru");
    app.getContactHelper().createContact(contact);
    List<ContactDate> after = app.getContactHelper().getContactList();


    before.add(contact);
    Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}

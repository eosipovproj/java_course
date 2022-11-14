package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {
  @BeforeMethod
    public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withGroupname("test2").withHeader("test header").withFooter("test comment"));
    }
  }
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    List<ContactDate> before = app.contact().list();
    ContactDate contact = new ContactDate().withFirstname("Evgeniy").withLastname("Osipov")
            .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru");
    app.contact().create(contact);
    List<ContactDate> after = app.contact().list();


    before.add(contact);
    Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}

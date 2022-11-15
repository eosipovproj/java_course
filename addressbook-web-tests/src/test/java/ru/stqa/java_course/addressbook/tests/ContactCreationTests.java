package ru.stqa.java_course.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactDate;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupDate;

import static org.hamcrest.MatcherAssert.assertThat;


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
    Contacts before = app.contact().all();
    ContactDate contact = new ContactDate().withFirstname("Evgeniy").withLastname("Osipov")
            .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), Matchers.equalTo( before.size() + 1));

//    before.add(contact);
//    Comparator<? super ContactDate> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//    before.sort(byId);
//    after.sort(byId);
//    Assert.assertEquals(before, after);
    assertThat(after, Matchers.equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

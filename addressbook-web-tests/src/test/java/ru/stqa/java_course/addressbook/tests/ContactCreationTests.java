package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ContactCreationTests extends TestBase {
  @BeforeMethod
    public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withGroupname("test2").withHeader("test header").withFooter("test comment"));
    }
  }
  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/test.png");
    ContactData contact = new ContactData().withFirstname("Evgeniy").withLastname("Osipov")
            .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

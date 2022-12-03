package ru.stqa.java_course.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactFromXml() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null){
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      xstream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }
  @BeforeMethod
    public void ensurePrecondition() {
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withGroupname("test1").withHeader("test header").withFooter("test comment"));
    }
  }
  @Test(enabled = false,dataProvider = "validContactFromJson")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test(dataProvider = "validContactFromJson")
  public void testContactCreationDb(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testContactCreationWithPhoto() throws Exception {
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
  @Test
  public void testContactCreationWithPhotoDb() throws Exception {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/test.png");
    ContactData contact = new ContactData().withFirstname("Evgeniy").withLastname("Osipov")
            .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo( before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}

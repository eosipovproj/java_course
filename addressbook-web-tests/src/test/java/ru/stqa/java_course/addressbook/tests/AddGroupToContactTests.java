package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;
import ru.stqa.java_course.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddGroupToContactTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupname("test1").withHeader("test header").withFooter("test comment"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Evgeniy").withLastname("Osipov")
                    .withAddress("Saint-Petersburg").withMobile("+78112341123").withEmail("test@test.ru"));
        }
    }

    @Test
    public void testAddingGroupToContact(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData selectContact = contacts.iterator().next();
        int beforeAddingGroup = selectContact.getGroups().size();
        if (beforeAddingGroup == groups.size()){
            GroupData selectedGroup = new GroupData().withGroupname("test-a");
            app.group().create(selectedGroup);
            app.contact().addingGroup(selectContact,selectedGroup);
        } else {
            GroupData group = groups.iterator().next();
            app.contact().addingGroup(selectContact,group);

        }
        app.goTo().homePage();
        int afterAddingGroup = selectContact.getGroups().size();
        assertThat(afterAddingGroup,equalTo(beforeAddingGroup + 1));
    }
}

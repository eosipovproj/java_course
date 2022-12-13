package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;
import ru.stqa.java_course.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddGroupToContactTests extends TestBase {
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
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        int contactFullSize = 0;
        for(ContactData contact: contacts){
            if (contact.getGroups().size() == groups.size()) {
                contactFullSize++;
            }
            if (contactFullSize == contacts.size()) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withGroupname("test1").withHeader("test header")
                        .withFooter("test comment"));
                app.goTo().homePage();
            }
        }
    }


    @Test
    public void testAddingGroupToContact() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData selectContact = getSelectContact(contacts, groups.size());
        GroupData selectGroup = getSelectGroup(groups,selectContact);
        int beforeAddingGroup = selectContact.getGroups().size();
        app.contact().addingGroup(selectContact, selectGroup);
        contacts = app.db().contacts();
        ContactData findContact = getFindContact(contacts, selectContact.getId());
        int afterAddingGroup = findContact.getGroups().size();
        assertThat(afterAddingGroup, equalTo(beforeAddingGroup + 1));
    }

    public ContactData getSelectContact (Contacts contact, int groupsSize){
        return contact.stream().filter((c) -> c.getGroups().size() != groupsSize).findFirst().get();
    }
    public  GroupData getSelectGroup (Groups groupsAll,ContactData contact){
        groupsAll.removeAll(contact.getGroups());
        return groupsAll.iterator().next();
    }
    private ContactData getFindContact(Contacts contact, int contactId) {
        return contact.stream().filter((c) -> c.getId() == contactId).findFirst().get();
    }

}

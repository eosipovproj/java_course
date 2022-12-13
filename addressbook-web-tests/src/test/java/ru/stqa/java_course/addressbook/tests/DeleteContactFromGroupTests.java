package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;
import ru.stqa.java_course.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactFromGroupTests extends TestBase {
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
        for(ContactData contact: contacts){
            if(contact.getGroups().size() == 0){
                app.contact().addingGroup(contact, groups.iterator().next());
                app.goTo().homePage();
            }
            break;
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Contacts contacts = app.db().contacts();
        ContactData selectContact = getSelectContact(contacts);
        GroupData selectGroup = selectContact.getGroups().iterator().next();
        int beforeDeletingGroup = selectContact.getGroups().size();
        app.contact().deleteGroupFromContact(selectGroup, selectContact);
        contacts = app.db().contacts();
        ContactData findContact = getFindContact(contacts, selectContact.getId());
        int afterDeletingGroup = findContact.getGroups().size();
        assertThat(afterDeletingGroup, equalTo(beforeDeletingGroup - 1));
    }

    public ContactData getSelectContact(Contacts contact) {
        return contact.stream().filter((c) -> c.getGroups().size() > 0).findFirst().get();
    }
    private ContactData getFindContact(Contacts contact, int contactId) {
        return contact.stream().filter((c) -> c.getId() == contactId).findFirst().get();
    }
}

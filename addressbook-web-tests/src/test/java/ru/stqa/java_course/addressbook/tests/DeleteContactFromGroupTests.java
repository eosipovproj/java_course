package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.ContactData;
import ru.stqa.java_course.addressbook.model.Contacts;
import ru.stqa.java_course.addressbook.model.GroupData;
import ru.stqa.java_course.addressbook.model.Groups;

public class DeleteContactFromGroupTests extends TestBase{

    @Test
    public void testDeleteContactFromGroup (){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData selectedGroup = groups.iterator().next();
        if(selectedGroup.getContacts().size() == 0){
            ContactData selectedContact = new ContactData().withFirstname("sd");
            app.contact().create(selectedContact);
            app.contact().deleteGroupFromContact(selectedGroup, selectedContact);
        }


//        assertThat(beforeDeletingContact.size(),equalTo(afterAddingGroup.size() - 1));

    }
}

package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification () {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupsHelper().isThereAGroup()){
            app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
        }
        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().initGroupModification();
        app.getGroupsHelper().fillGroupForm(new GroupDate("test1-Edited",
                "test header-Edited", "test comment-Edited"));
        app.getGroupsHelper().submitGroupModification();
        app.getGroupsHelper().returnToGroupPage();
    }
}

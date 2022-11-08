package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification () {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupsHelper().isThereAGroup()){
            app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
        }
        List<GroupDate> before = app.getGroupsHelper().getGroupList();
        app.getGroupsHelper().selectGroup(before.size() - 1);
        app.getGroupsHelper().initGroupModification();
        GroupDate group = new GroupDate(before.get(before.size() - 1).getId(), "test1-ed",
                "test header-Edited", "test comment-Edited");
        app.getGroupsHelper().fillGroupForm(group);
        app.getGroupsHelper().submitGroupModification();
        app.getGroupsHelper().returnToGroupPage();
        List<GroupDate> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);    
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

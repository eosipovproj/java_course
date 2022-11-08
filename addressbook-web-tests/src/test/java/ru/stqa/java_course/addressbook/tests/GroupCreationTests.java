package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    List<GroupDate> before = app.getGroupsHelper().getGroupList();
    GroupDate group = new GroupDate("test2", "test header", "test comment");
    app.getGroupsHelper().createGroup(group);
    List<GroupDate> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

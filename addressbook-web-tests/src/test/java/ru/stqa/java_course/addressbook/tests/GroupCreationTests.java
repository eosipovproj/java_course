package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    List<GroupDate> before = app.getGroupsHelper().getGroupList();
    app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
    List<GroupDate> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}

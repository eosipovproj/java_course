package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

public class GroupDeletionTests extends TestBase{


  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupsHelper().isThereAGroup()){
      app.getGroupsHelper().createGroup(new GroupDate("test1", "test header", "test comment"));
    }
    app.getGroupsHelper().selectGroup();
    app.getGroupsHelper().deleteSelectedGroups();
    app.getGroupsHelper().returnToGroupPage();
  }

}

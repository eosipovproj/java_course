package ru.stqa.java_course.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupDate("test1", "test header", "test comment"));
    submitGroupCreation();
    returnToGroupPage();
  }
}

package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;
import ru.stqa.java_course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition () {
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupDate().withGroupname("test1").withHeader("test header").withFooter("test comment"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupDate deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();

    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}

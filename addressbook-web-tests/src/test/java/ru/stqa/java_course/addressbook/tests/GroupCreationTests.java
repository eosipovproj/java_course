package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupData;
import ru.stqa.java_course.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTests extends TestBase{
  @DataProvider
  public Iterator<Object[]> validGroup(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withGroupname("test1").withHeader("Header1").withFooter("Footer1")});
    list.add(new Object[] {new GroupData().withGroupname("test2").withHeader("Header2").withFooter("Footer2")});
    list.add(new Object[] {new GroupData().withGroupname("test3").withHeader("Header3").withFooter("Footer3")});
    return list.iterator();
  }
  @Test(dataProvider = "validGroup")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(),equalTo( before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withGroupname("test2'");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));
  }
}

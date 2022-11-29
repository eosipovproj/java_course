package ru.stqa.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupData;
import ru.stqa.java_course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition () {
       if (app.db().groups().size() == 0){
           app.goTo().groupPage();
           app.group().create(new GroupData().withGroupname("test1").withHeader("test header").withFooter("test comment"));
       }
    }
    @Test(enabled = false)
    public void testGroupModification () {
        Groups before = app.group().all();
        GroupData mofifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(mofifiedGroup.getId()).withGroupname( "test1-ed").
                withHeader("test header-Edited").withFooter( "test comment-Edited");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.without(mofifiedGroup).withAdded(group)));
    }
    @Test
    public void testGroupModificationDb () {
        Groups before = app.db().groups();
        GroupData mofifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(mofifiedGroup.getId()).withGroupname( "test1-ed").
                withHeader("test header-Edited").withFooter( "test comment-Edited");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();

        assertThat(after, equalTo(before.without(mofifiedGroup).withAdded(group)));
    }
}

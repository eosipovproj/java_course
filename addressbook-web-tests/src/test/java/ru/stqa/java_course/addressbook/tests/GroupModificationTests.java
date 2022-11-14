package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition () {
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupDate().withGroupname("test1").withHeader("test header").withFooter("test comment"));
        }
    }
    @Test
    public void testGroupModification () {
        Set<GroupDate> before = app.group().all();
        GroupDate mofifiedGroup = before.iterator().next();
        GroupDate group = new GroupDate().withId(mofifiedGroup.getId()).withGroupname( "test1-ed").
                withHeader("test header-Edited").withFooter( "test comment-Edited");
        app.group().modify(group);
        Set<GroupDate> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(mofifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }

}

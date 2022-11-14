package ru.stqa.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition () {
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupDate().withGroupname("test1").withHeader("test header").withFooter("test comment"));
        }
    }
    @Test
    public void testGroupModification () {
        List<GroupDate> before = app.group().list();
        int index = before.size() - 1;
        GroupDate group = new GroupDate().withId(before.get(index).getId()).withGroupname( "test1-ed").
                withHeader("test header-Edited").withFooter( "test comment-Edited");
        app.group().modify(index, group);
        List<GroupDate> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);    
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}

package ru.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.java_course.addressbook.model.GroupDate;

import java.util.ArrayList;
import java.util.List;

public class GroupsHelper extends BaseHelper{

    public GroupsHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("groups"));
    }

    public void submitGroupCreation() {
      click(By.name("submit"));
    }

    public void fillGroupForm(GroupDate groupDate) {
        type(By.name("group_name"), groupDate.getGroupname());
        type(By.name("group_header"), groupDate.getHeader());
        type(By.name("group_footer"), groupDate.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupDate group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }
    public void groupModify(int index, GroupDate group) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupDate> getGroupList() {
        List<GroupDate> groups = new ArrayList<GroupDate>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupDate group = new GroupDate(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}

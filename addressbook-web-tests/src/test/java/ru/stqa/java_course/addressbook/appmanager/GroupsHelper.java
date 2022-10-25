package ru.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.java_course.addressbook.model.GroupDate;

public class GroupsHelper {
    private WebDriver wd;

    public GroupsHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void returnToGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    public void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupDate groupDate) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupDate.getGroupname());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupDate.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupDate.getFooter());
      wd.findElement(By.name("group_header")).click();
    }

    public void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    public void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }
}

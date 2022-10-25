package ru.stqa.java_course.addressbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    private WebDriver wd;
    private JavascriptExecutor js;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new ChromeDriver();

      wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
      js = (JavascriptExecutor) wd;
      login("admin", "secret");
    }

    private void login(String username, String password) {
      wd.get("http://localhost/addressbook/group.php");
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    private void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    protected void returnToGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    protected void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupDate groupDate) {
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

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      logout();
      wd.quit();

    }

    private boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    protected void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    protected void submitContactCreation() {
      wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    protected void returnHomePage() {
      wd.findElement(By.linkText("home page")).click();
      wd.get("http://localhost/addressbook/index.php");
    }

    protected void fillContactForm(ContactDate contactDate) {
      wd.get("http://localhost/addressbook/edit.php");
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactDate.getFirstname());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactDate.getLastname());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(contactDate.getAddress());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactDate.getMobile());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactDate.getEmail());
    }

    protected void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }
}

package ru.stqa.java_course.addressbook.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private NavigationHelper navigationHelper;
    private GroupsHelper groupsHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    WebDriver wd;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(Browser.CHROME.browserName())) {
                wd = new ChromeDriver();
            } else if (browser.equals(Browser.IE.browserName())) {
                wd = new InternetExplorerDriver();
            } else if (browser.equals(Browser.FIREFOX.browserName())) {
                wd = new FirefoxDriver(new FirefoxOptions().setBinary(properties.getProperty("web.pathToFirefox")));
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
//            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        groupsHelper = new GroupsHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public GroupsHelper group() {
        return groupsHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }
    public byte[] takeScreenshot(){
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }
}

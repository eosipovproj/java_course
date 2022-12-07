package ru.stqa.java_course.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {

    WebDriver wd;
    private String browser;
    private final Properties properties;
    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties", target))));

        if (browser.equals(Browser.CHROME.browserName())) {
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.IE.browserName())) {
            wd = new InternetExplorerDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            wd = new FirefoxDriver(new FirefoxOptions().setBinary(properties.getProperty("web.pathToFirefox")));
        }
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.get(properties.getProperty("web.baseUrl"));

    }
        public void stop() {
        wd.quit();
    }
}

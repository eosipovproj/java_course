package ru.stqa.java_course.mantis.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.java_course.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
//        app.ftp().upload(new File("src/test/resources/config_inc.php")
//                ,"config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
//        app.ftp().restore("config_inc.php.bak","config_inc.php");
        app.stop();
    }
    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    public boolean isIssueOpen (int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String status = app.soap().getIssueStatus(issueId);
        if( status.equals("resolved") || status.equals("closed")) {
            return false;
        }
        return true;
    }
}
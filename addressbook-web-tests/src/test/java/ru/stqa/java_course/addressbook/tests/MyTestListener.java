package ru.stqa.java_course.addressbook.tests;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.stqa.java_course.addressbook.appmanager.ApplicationManager;

public class MyTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
        saveScreenshot(app.takeScreenshot());
    }
//    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
    @Override
    public void onTestFailure(ITestResult result) {

    }
    @Override
    public void onTestSkipped(ITestResult result) {

    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }
    @Override
    public void onStart(ITestContext context) {

    }
    @Override
    public void onFinish(ITestContext context) {

    }
}

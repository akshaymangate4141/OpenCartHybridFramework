package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import testCases.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtendReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String reportName;

    @Override
    @Test
    public void onStart(ITestContext context) {

        reportName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Test_Data/Reports/" + reportName);
        sparkReporter.config().setDocumentTitle("Automation Testing");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Modules", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Enviroment", "QA");

        String os = context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }

    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); // create a new entry in the report
        test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
        test.log(Status.INFO, "Test case FAILED cause is: " + result.getThrowable());

        try {
            String imagePath = new BaseClass().captureTheScreenshot(result.getName());
            test.addScreenCaptureFromPath(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
        test.log(Status.INFO, "Test case SKIPPED is:" + result.getThrowable());

    }

    public void onFinish(ITestContext context) {

        extent.flush();
        String pathOfTheExtentReport = System.getProperty("user.dir" + "\\reports\\"+reportName);
        File file = new File(pathOfTheExtentReport);
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ScreenshotUtil;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected static WindowsDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest extentTest;
    protected static String reportFolderPath;

    @BeforeSuite
    public void setupSuite() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportFolderPath = "reports/" + timestamp;
        new File(reportFolderPath).mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportFolderPath + "/ExtentReport.html");
        spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Metadata Assignment Execution");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setupTest() throws Exception {
        extentTest = extent.createTest(this.getClass().getSimpleName());

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", "PathToYourApp.exe"); // Update
        caps.setCapability("deviceName", "WindowsPC");
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.fail(result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Step passed.");
            }
            String screenshotPath = ScreenshotUtil.takeScreenshot(reportFolderPath + "/screenshots/" + result.getName());
            extentTest.addScreenCaptureFromPath(new File(screenshotPath).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}

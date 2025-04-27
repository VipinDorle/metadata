package hooks;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    public static ExtentReports extent;
    public static ExtentTest test;
    public static String reportFolderPath;

    @Before
    public void startReport() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFolderPath = System.getProperty("user.dir") + "/reports/" + timestamp;
        new File(reportFolderPath + "/screenshots").mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportFolderPath + "/ExtentReport.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Metadata Assignment Tests");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @After
    public void endReport() {
        extent.flush();
    }
}

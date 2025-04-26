package utils;

import drivers.DesktopDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void captureScreenshot(String stepName) {
        try {
            File srcFile = ((TakesScreenshot) DesktopDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = "test-output/screenshots/" + stepName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            ScenarioContext.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}

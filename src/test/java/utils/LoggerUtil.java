package utils;

import com.aventstack.extentreports.Status;

public class LoggerUtil {

    public static void logInfo(String message) {
        ScenarioContext.getTest().log(Status.INFO, "ℹ️ " + message);
        ScreenshotUtil.captureScreenshot(message); // Screenshot after info log
    }

    public static void logPass(String message) {
        ScenarioContext.getTest().log(Status.PASS, "✅ " + message);
        ScreenshotUtil.captureScreenshot(message);
    }

    public static void logWarning(String message) {
        ScenarioContext.getTest().log(Status.WARNING, "⚠️ " + message);
        ScreenshotUtil.captureScreenshot(message);
    }

    public static void logFail(String message) {
        ScenarioContext.getTest().log(Status.FAIL, "❌ " + message);
        ScreenshotUtil.captureScreenshot(message);
    }
}

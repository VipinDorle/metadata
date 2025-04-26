package base;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class BaseTest {

    public static WindowsDriver driver;

    public static void initializeDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Path to your Desktop Application exe");
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

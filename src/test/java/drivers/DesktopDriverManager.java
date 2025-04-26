package drivers;

import io.appium.java_client.windows.WindowsDriver;
import base.BaseTest;

public class DesktopDriverManager {

    public static WindowsDriver getDriver() {
        return BaseTest.driver;
    }
}

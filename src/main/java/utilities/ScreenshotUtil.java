package utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenshotUtil {
    public static String takeScreenshot(String fullPathWithoutExtension) {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            String fullPath = fullPathWithoutExtension + ".png";
            File screenshotFile = new File(fullPath);
            screenshotFile.getParentFile().mkdirs();
            ImageIO.write(screenCapture, "png", screenshotFile);
            return fullPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

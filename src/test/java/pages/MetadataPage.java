package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import drivers.DesktopDriverManager;

public class MetadataPage {

    // Assign metadata field (example: "CustomerName", "Address")
    public void assignMetadataField(String fieldName, String value) {
        WebElement fieldElement = DesktopDriverManager.getDriver()
                .findElement(By.name(fieldName)); // Assuming each field is identifiable by its name
        fieldElement.clear();  // Clear any existing value
        fieldElement.sendKeys(value);  // Assign new value
    }

    // Click the Save button
    public void saveMetadata() {
        WebElement saveButton = DesktopDriverManager.getDriver().findElement(By.id("SaveButton"));
        saveButton.click();
    }

public void assignMultipleMetadataFields(Map<String, String> metadata) {
    for (Map.Entry<String, String> entry : metadata.entrySet()) {
        String field = entry.getKey();
        String value = entry.getValue();
        assignMetadataField(field, value);  // Use the existing method to assign metadata
    }
}
    public static void assignField(String fieldName, String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                System.out.println("Skipping field: " + fieldName + " because value is null or empty");
                return;
            }

            // Find the input box based on field label or ID
            // You might need to tweak locator according to your application structure
            WebElement fieldElement = DesktopDriverManager.getDriver().findElement(By.name(fieldName));

            fieldElement.clear();
            fieldElement.sendKeys(value);
            System.out.println("Assigned field: " + fieldName + " with value: " + value);

            // You can also add ExtentReport log and screenshot here if you want
            // Example: ExtentReportManager.logPass("Assigned field: " + fieldName, ScreenshotHelper.captureScreenshot());

        } catch (Exception e) {
            System.out.println("Error while assigning field: " + fieldName);
            e.printStackTrace();
        }
    }
}

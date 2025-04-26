package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import drivers.DesktopDriverManager;

public class QueuePage {

    // Select the Queue by name
    public void navigateToQueue(String queueName) {
        WebElement queueElement = DesktopDriverManager.getDriver().findElement(By.name(queueName));
        queueElement.click();
    }

    // Select Document by index
    public void selectDocument(int index) {
        WebElement document = DesktopDriverManager.getDriver()
                .findElements(By.className("DocumentRow"))
                .get(index);
        document.click();
    }
}

package test.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.task.driver.Waiting;

public class CustomFilter extends Element {

    public CustomFilter(WebDriver webDriver, String xpath) {
        this.xpath = xpath;
        this.webDriver = webDriver;
    }

    public void openIfClosed() {
        WebElement element = webDriver.findElement(By.xpath(xpath));
        if (!element.getAttribute("class").contains("open")) {
            element.click();
            Waiting.waitForPageLoaded(webDriver);
        }
    }
}

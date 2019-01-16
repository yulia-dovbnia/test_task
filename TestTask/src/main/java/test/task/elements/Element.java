package test.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract class Element {
    WebDriver webDriver;
    String xpath;

    public WebElement getElement() {
        return webDriver.findElement(By.xpath(xpath));
    }

    public List<WebElement> getElements() {
        return webDriver.findElements(By.xpath(xpath));
    }

}

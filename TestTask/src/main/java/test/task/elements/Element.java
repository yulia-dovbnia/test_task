package test.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Element {
    protected WebDriver webDriver;
    protected String xpath;

    public Element(WebDriver webDriver, String xpath) {
        this.webDriver = webDriver;
        this.xpath = xpath;
    }

    public WebElement getElement() {
        return webDriver.findElement(By.xpath(xpath));
    }

    public List<WebElement> getElements() {
        return webDriver.findElements(By.xpath(xpath));
    }

}

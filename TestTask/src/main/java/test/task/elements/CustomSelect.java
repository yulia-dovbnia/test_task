package test.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.task.driver.Waiting;

public class CustomSelect extends Element {
    public CustomSelect(WebDriver webDriver, String xpath) {
        this.webDriver = webDriver;
        this.xpath = xpath;
    }

    private void selectByAttribute(String attributeName, String attributeValue) {
        getElement().findElement(By.xpath("./option[@" + attributeName + "='" + attributeValue + "']")).click();
        Waiting.waitForPageLoaded(webDriver);
    }

    public void selectByAttribute(String attributeValue) {
        selectByAttribute("data-qa-selector-value", attributeValue);
    }

    public void selectByVisibleText(String text) {
        new Select(getElement()).selectByVisibleText(text);
        Waiting.waitForPageLoaded(webDriver);
    }

    private WebElement getElement() {
        return webDriver.findElement(By.xpath(xpath));
    }
}

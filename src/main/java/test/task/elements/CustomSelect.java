package test.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import test.task.driver.Waiting;

public class CustomSelect extends Element {
    public CustomSelect(WebDriver webDriver, String xpath) {
        super(webDriver, xpath);
    }

    private void selectByAttribute(String attributeName, String attributeValue) {
        getElement().findElement(By.xpath(String.format("./option[@%s='%s']", attributeName, attributeValue))).click();
        Waiting.waitForPageLoaded(webDriver, true);
    }

    public void selectByAttribute(String attributeValue) {
        selectByAttribute("data-qa-selector-value", attributeValue);
    }

    public void selectByVisibleText(String text) {
        new Select(getElement()).selectByVisibleText(text);
        Waiting.waitForPageLoaded(webDriver, true);
    }

}

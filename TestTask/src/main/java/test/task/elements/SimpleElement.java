package test.task.elements;

import org.openqa.selenium.WebDriver;

public class SimpleElement extends Element {
    public SimpleElement(WebDriver webDriver, String xpath) {
        this.webDriver = webDriver;
        this.xpath = xpath;
    }
}

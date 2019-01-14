package test.task.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage<T extends BasePage<T>> {

    final String  baseURL ="https://www.autohero.com";

    WebDriver webDriver;

    public T open() {
        webDriver.get(getURL());
        return (T) this;
    }

    abstract String getURL();


}

package test.task.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;

public abstract class BasePage<T extends BasePage<T>> {

    @Value("${host}")
    protected String baseURL;

    WebDriver webDriver;

    public T open() {
        webDriver.get(getURL());
        return (T) this;
    }

    abstract String getURL();


}

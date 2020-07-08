package test.task.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;

public abstract class BasePage<T extends BasePage<T>> {

    @Value("${host}")
    protected String baseURL;

    WebDriver webDriver;

    public T open() {
        Selenide.open(getURL());
        return (T) this;
    }

    abstract String getURL();


    public void waitForPageLoaded(boolean isElementReload) {
        FluentWait<WebDriver> webDriverFluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10));

        if (isElementReload) {
            final String loadingBanner = "//*[@data-qa-selector='loading-banner'][contains(@class, 'loading')]";
            ElementsCollection selenideElement = $$x(loadingBanner);
            webDriverFluentWait.until(condition -> selenideElement.size() > 0);
            webDriverFluentWait.until(condition -> selenideElement.size() == 0);
        }
        waitForReadyState(webDriverFluentWait);
    }

    private void waitForReadyState(FluentWait<WebDriver> webDriverFluentWait) {
        webDriverFluentWait.until(condition ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

}

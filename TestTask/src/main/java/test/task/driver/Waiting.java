package test.task.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import test.task.elements.Element;
import test.task.pages.SearchPage;

import java.time.Duration;

public class Waiting {
    private Waiting() {
    }

    public static void waitForPageLoaded(WebDriver webDriver, boolean isElementReload) {
        FluentWait<WebDriver> webDriverFluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10));

        if (isElementReload) {
            waitForLoadingBanner(webDriver, webDriverFluentWait);
        }
        waitForReadyState(webDriver, webDriverFluentWait);
    }

    private static void waitForLoadingBanner(WebDriver webDriver, FluentWait webDriverFluentWait) {
        Element element = new SearchPage(webDriver).loadingBanner;
        webDriverFluentWait.until(condition -> element.getElements().size() > 0);
        webDriverFluentWait.until(condition -> element.getElements().size() == 0);
    }

    private static void waitForReadyState(WebDriver webDriver, FluentWait webDriverFluentWait) {
        webDriverFluentWait.until(condition -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}

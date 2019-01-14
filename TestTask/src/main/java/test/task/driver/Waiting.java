package test.task.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Waiting {
    private Waiting() {
    }

    public static void waitForPageLoaded(WebDriver webDriver) {
        FluentWait<WebDriver> webDriverFluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10));

        waitForReadyState(webDriver, webDriverFluentWait);
        waitForAjax(webDriver, webDriverFluentWait);
    }

    private static void waitForAjax(WebDriver webDriver, FluentWait webDriverFluentWait) {
        try {
            webDriverFluentWait.until(condition -> (boolean) (((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active == 0;")));
        } catch (TimeoutException ignored) {
        }
    }

    private static void waitForReadyState(WebDriver webDriver, FluentWait webDriverFluentWait) {
        webDriverFluentWait.until(condition -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}

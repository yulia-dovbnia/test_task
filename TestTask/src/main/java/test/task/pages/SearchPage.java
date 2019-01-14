package test.task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.task.driver.Waiting;
import test.task.elements.CustomFilter;
import test.task.elements.CustomSelect;

import java.util.List;

public class SearchPage extends BasePage<SearchPage> {

    private final String url = baseURL + "/de/search/";

    public CustomFilter filterYear;
    public CustomSelect selectYear;
    public CustomSelect selectSort;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        filterYear = new CustomFilter(webDriver, "//*[@data-qa-selector='filter-year']");
        selectYear = new CustomSelect(webDriver, "//*[@data-qa-selector='select'][@name='yearRange.min']");
        selectSort = new CustomSelect(webDriver, "//*[@data-qa-selector='select'][@name='sort']");
    }

    @Override
    String getURL() {
        return url;
    }

    public void closePopupIfPresent() {
        List<WebElement> element = webDriver.findElements(By.xpath("//*[@class='modal-dialog']//button[@class='close']"));
        if (!element.isEmpty()) {
            element.get(0).click();
            Waiting.waitForPageLoaded(webDriver);
        }
    }
}

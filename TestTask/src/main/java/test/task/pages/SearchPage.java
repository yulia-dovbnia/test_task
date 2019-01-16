package test.task.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.task.driver.Waiting;
import test.task.elements.CustomFilter;
import test.task.elements.CustomSelect;
import test.task.elements.SimpleElement;

import java.util.List;

public class SearchPage extends BasePage<SearchPage> {

    private final String url = baseURL + "/de/search/";

    public CustomFilter filterYear;
    public CustomSelect selectYear;
    public CustomSelect selectSort;
    public SimpleElement loadingBanner;
    public SimpleElement closePopupButton;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        filterYear = new CustomFilter(webDriver, "//*[@data-qa-selector='filter-year']");
        selectYear = new CustomSelect(webDriver, "//*[@data-qa-selector='select'][@name='yearRange.min']");
        selectSort = new CustomSelect(webDriver, "//*[@data-qa-selector='select'][@name='sort']");
        loadingBanner = new SimpleElement(webDriver, "//*[@data-qa-selector='loading-banner'][contains(@class, 'loading')]");
        closePopupButton = new SimpleElement(webDriver, "//*[@class='modal-dialog']//button[@class='close']");
    }

    @Override
    String getURL() {
        return url;
    }

    public void closePopupIfPresent() {
        List<WebElement> element = closePopupButton.getElements();
        if (!element.isEmpty()) {
            element.get(0).click();
            Waiting.waitForPageLoaded(webDriver, false);
        }
    }
}

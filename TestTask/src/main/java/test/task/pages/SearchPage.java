package test.task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import test.task.driver.Waiting;
import test.task.elements.CustomFilter;
import test.task.elements.CustomSelect;
import test.task.elements.Element;
import test.task.elements.data.ProductDataItem;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPage extends BasePage<SearchPage> {
    public final CustomFilter filterYear;
    public final CustomSelect selectYear;
    public final CustomSelect selectSort;
    public final Element loadingBanner;
    public final Element closePopupButton;

    private static final String XPATH = "//*[@data-qa-selector='ad']";
    private static final String TITLE_XPATH = ".//*[@data-qa-selector='title']";
    private static final String DATE_XPATH = ".//*[@data-qa-selector='spec']";
    private static final String PRICE_XPATH = ".//*[@data-qa-selector='price']";

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        filterYear = new CustomFilter(webDriver, "//*[@data-qa-selector='filter-year']");
        selectYear = new CustomSelect(webDriver, "//*[@data-qa-selector='select'][@name='yearRange.min']");
        selectSort = new CustomSelect(webDriver, "//*[@data-qa-selector='select'][@name='sort']");
        loadingBanner = new Element(webDriver, "//*[@data-qa-selector='loading-banner'][contains(@class, 'loading')]");
        closePopupButton = new Element(webDriver, "//*[@class='modal-dialog']//button[@class='close']");
    }

    @Override
    String getURL() {
        return baseURL + "/de/search/";
    }

    @Override
    public SearchPage open() {
        super.open();
        closePopupIfPresent();
        return this;
    }

    private void closePopupIfPresent() {
        List<WebElement> element = closePopupButton.getElements();
        if (!element.isEmpty()) {
            element.get(0).click();
            Waiting.waitForPageLoaded(webDriver, false);
        }
    }

    public List<ProductDataItem> getProductInfoList() {
        List<WebElement> webElementList = webDriver.findElements(By.xpath(XPATH));
        return webElementList.stream().map(item -> {
            ProductDataItem productDataItem = new ProductDataItem();
            productDataItem.setTitle(item.findElement(By.xpath(TITLE_XPATH)).getText());
            productDataItem.setDate(item.findElement(By.xpath(DATE_XPATH)).getText());
            productDataItem.setPrice(item.findElement(By.xpath(PRICE_XPATH)).getText().split(" ")[0]);
            productDataItem.setYear(productDataItem.getDate().split("/")[1]);
            return productDataItem;
        }).collect(Collectors.toList());
    }
}

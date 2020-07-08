package test.task.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import test.task.elements.data.ProductDataItem;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class SearchPage extends BasePage<SearchPage> {
    public static final String FILTER_YEAR = "//*[@data-qa-selector='filter-year']";
    public static final String SELECT_YEAR = "//*[@data-qa-selector='select'][@name='yearRange.min']";
    public static final String SELECT_SORT = "//*[@data-qa-selector='select'][@name='sort']";
    private static final String DESCENDING = "//option[@data-qa-selector-value='offerPrice.amountMinorUnits.desc']";

    public static final String CLOSE_POPUP_BUTTON = "//*[@class='modal-dialog']//button[@class='close']";

    private static final String PRODUCT_INFO = "//*[@data-qa-selector='ad']";
    private static final String TITLE = ".//*[@data-qa-selector='title']";
    private static final String DATE = ".//*[@data-qa-selector='spec']";
    private static final String PRICE = ".//*[@data-qa-selector='price']";

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    String getURL() {
        return baseURL + "/de/search/";
    }

    @Step
    @Override
    public SearchPage open() {
        super.open();
        closePopupIfPresent();
        return this;
    }

    @Step
    private void closePopupIfPresent() {
        SelenideElement closePopupButton = $x(SearchPage.CLOSE_POPUP_BUTTON);
        if (closePopupButton.exists()) {
            closePopupButton.click();
        }
    }

    @Step
    public List<ProductDataItem> getProductInfoList() {
        return $$x(PRODUCT_INFO).stream().map(
                item -> new ProductDataItem(
                        item.$x(TITLE).getText(),
                        item.$x(DATE).getText(),
                        item.$x(PRICE).getText().split(" ")[0])
        ).collect(Collectors.toList());
    }

    @Step
    public void sortDescending() {
        $x(SELECT_SORT + DESCENDING).click();
        waitForPageLoaded(true);
    }

    @Step
    public void selectYear(String year) {
        $x(SELECT_YEAR).selectOption(year);
        waitForPageLoaded(true);
    }

    @Step
    public void openFilterYearIfClosed() {
        SelenideElement element = $x(FILTER_YEAR);
        String attribute = element.getAttribute("class");
        if (attribute == null || !attribute.contains("open")) {
            element.click();
            waitForPageLoaded(false);
        }
    }
}

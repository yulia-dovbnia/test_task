package test.task.smoke;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import test.task.driver.DriverFactory;
import test.task.elements.ProductItemFactory;
import test.task.pages.SearchPage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class SearchPageTest {
    private final WebDriver webDriver = new DriverFactory().getDriver();
    private final int yearToCheck = 2005;

    @Test
    public void yearFilterTest() {
        SearchPage searchPage = new SearchPage(webDriver).open();
        searchPage.closePopupIfPresent();

        searchPage.filterYear.openIfClosed();
        searchPage.selectYear.selectByVisibleText(String.valueOf(yearToCheck));
        searchPage.selectSort.selectByAttribute("offerPrice.amountMinorUnits.desc");

        List<Integer> yearsList = ProductItemFactory.getList(webDriver)
                .stream()
                .map(productData -> Integer.valueOf(productData.getYear()))
                .collect(Collectors.toList());
        List<Double> pricesList = ProductItemFactory.getList(webDriver)
                .stream()
                .map(productData -> Double.valueOf(productData.getPrice()))
                .collect(Collectors.toList());


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(pricesList).as("Check price is descending").isSortedAccordingTo(Comparator.reverseOrder());

        yearsList.forEach(year -> softly.assertThat(year >= yearToCheck)
                .as("Year is less than " + yearToCheck + ". Actual = " + year)
                .isTrue());
        softly.assertAll();
    }
}

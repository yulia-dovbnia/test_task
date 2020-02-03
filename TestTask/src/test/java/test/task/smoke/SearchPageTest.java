package test.task.smoke;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.AbstractTest;
import test.task.pages.SearchPage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class SearchPageTest extends AbstractTest {
    private final int yearToCheck = 2015;

    @Autowired
    SearchPage searchPage;

    @Test
    public void yearFilterTest() {
        searchPage.open();

        searchPage.filterYear.openIfClosed();
        searchPage.selectYear.selectByVisibleText(String.valueOf(yearToCheck));
        searchPage.selectSort.selectByAttribute("offerPrice.amountMinorUnits.desc");

        List<Integer> yearsList = searchPage.getProductInfoList()
                .stream()
                .map(productData -> Integer.valueOf(productData.getYear()))
                .collect(Collectors.toList());
        List<Double> pricesList = searchPage.getProductInfoList()
                .stream()
                .map(productData -> Double.valueOf(productData.getPrice()))
                .collect(Collectors.toList());


        softAssertions.assertThat(pricesList).as("Check price is descending").isSortedAccordingTo(Comparator.reverseOrder());

        yearsList.forEach(year -> softAssertions.assertThat(year >= yearToCheck)
                .as("Year is less than " + yearToCheck + ". Actual = " + year)
                .isTrue());
    }
}

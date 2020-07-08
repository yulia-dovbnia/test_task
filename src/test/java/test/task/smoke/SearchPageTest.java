package test.task.smoke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.AbstractTest;
import test.task.elements.data.ProductDataItem;
import test.task.pages.SearchPage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPageTest extends AbstractTest {
    private final int yearToCheck = 2015;

    @Autowired
    SearchPage searchPage;

    @Test
    @DisplayName("Technical task: open page, select year, soft desc -> verify sorting and filtering")
    public void yearFilterTest() {
        searchPage.open();

        searchPage.openFilterYearIfClosed();
        searchPage.selectYear(String.valueOf(yearToCheck));
        searchPage.sortDescending();

        List<ProductDataItem> productInfoList = searchPage.getProductInfoList();
        List<Integer> yearsList = productInfoList.stream()
                .map(productData -> Integer.valueOf(productData.getYear()))
                .collect(Collectors.toList());
        List<Double> pricesList = productInfoList.stream()
                .map(productData -> Double.valueOf(productData.getPrice()))
                .collect(Collectors.toList());


        softAssertions.assertThat(pricesList).as("Check price is descending").isSortedAccordingTo(Comparator.reverseOrder());

        yearsList.forEach(year -> softAssertions.assertThat(year >= yearToCheck)
                .as("Year is less than " + yearToCheck + ". Actual = " + year)
                .isTrue());
    }
}

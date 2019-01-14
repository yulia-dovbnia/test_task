package test.task.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ProductItemFactory {
    private static final String XPATH = "//*[@data-qa-selector='ad']";
    private static final String TITLE_XPATH = ".//*[@data-qa-selector='title']";
    private static final String DATE_XPATH = ".//*[@data-qa-selector='spec']";
    private static final String PRICE_XPATH = ".//*[@data-qa-selector='price']";

    private ProductItemFactory() {
    }

    public static List<ProductDataItem> getList(WebDriver webDriver) {
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

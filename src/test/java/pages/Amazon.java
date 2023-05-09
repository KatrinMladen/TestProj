package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Optional;
import tests.BaseTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Amazon {
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(xpath = "//a[normalize-space()='Sneakers']")
    WebElement sneakersFilterButton;

    @FindBy(id = "low-price")
    WebElement minPriceFilter;

    @FindBy(id = "a-autoid-1")
    WebElement goButton;

    //@FindBy(xpath = "//*[contains(@data-component-type,'s-search-result')]")
    @FindBy(xpath = "//*[contains(@data-component-type,'s-search-result')]//div[@data-component-type='s-search-result']")
    List<WebElement> allSneakers;

    public Map<String, String> getAllDistinctProducts() {

        Map<String, String> result = new HashMap<>();
        for (WebElement el : allSneakers) {
            //String name = el.findElement(By.cssSelector(".a-size-base-plus.a-color-base.a-text-normal")).getText();
            String name = null;
            try {
                name = el.findElement(By.xpath(".//a[contains(@class,'a-text-normal')]")).getText();
            } catch (Exception ex) {
                //System.out.println("Error: "+result);
                //BaseTest.LOG.debug("name is not find");
            }
            String price = null;
            try {
                //String price = el.findElement(By.cssSelector(".a-row.a-size-base.a-color-base span span")).getText();
                price = el.findElement(By.xpath(".//span[@class='a-offscreen']")).getAttribute("outerText");
                result.put(name, price);
                BaseTest.LOG.debug("Find price");
            } catch (Exception ex) {
                result.put(name, "$0.00");
            }
        }
               return result;
    }

    public void searchProduct(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
        BaseTest.LOG.info("Searching for Adidas shoes for men, click ENTER");
    }

    public void setMinPriceFilter(String text) {
        minPriceFilter.clear();
        BaseTest.LOG.debug("Set filter for minimum price");
        minPriceFilter.sendKeys(text);
        BaseTest.LOG.info("Applied filter for minimum price");
    }

    public void setSneakersType() {
        BaseTest.LOG.debug("Try to set Sneakers Type");
        sneakersFilterButton.click();
        BaseTest.LOG.info("Set Sneakers Type");

    }

    public void goSearch() {
        BaseTest.LOG.debug("Try to click on button");
        goButton.click();
        BaseTest.LOG.info("Submitting search query");
    }
}

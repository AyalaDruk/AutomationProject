package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    //constructor

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By searchBox = By.id("header-big-screen-search-box");
    By searchButton = By.xpath("//*[@id=\"header-search-form\"]/button");


    // METHODS
    // method to search a product
    public void SearchProduct(String product) {
        // sent a product to search box
        driver.findElement(searchBox).sendKeys(product);
        // click o tha  search button
        driver.findElement(searchButton).click();
    }

}

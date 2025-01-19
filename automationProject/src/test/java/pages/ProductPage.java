package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    WebDriver driver;

    //constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // locators
    By color = By.cssSelector("img[title='Pink Heart']");
    By sizeLocator = By.xpath("//*[@id=\"pdp-page-layout\"]/div[1]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/div");
    By size23 = By.xpath("//*[@id=\":R2alad99qldadamd6:\"]/li[4]");
    By addToBag = By.xpath("//*[@id=\":Rj69qldadamd6:\"]");

    By viewBag = By.cssSelector("a[data-ga-v3='View Bag']");

    By addMoreToBag = By.xpath("//*[@id=\"items\"]/div/div/div[5]/div[2]/div/button[2]");

    By quantity = By.id("qty_B57629");

    By checkout = By.xpath("//*[@id=\"pri\"]/div[1]/div[2]/div[1]/div/div[5]/button");

    // methods

    // method to chose color by click
    public void chooseColor() {

        driver.findElement(color).click();
    }

    // method to chose size
    public void chooseSize() {
        driver.findElement(sizeLocator).click();
        driver.findElement(size23).click();
    }

    // method to get chosen size
    public String getChosenSize() {
        String gSize = driver.findElement(sizeLocator).getText();
        return gSize;

    }

    // method add to bag
    public void addToBag() {
        driver.findElement(addToBag).click();
    }

    // method view the bag
    public void viewBag() {
        driver.findElement(viewBag).click();
    }

    // add another item to the bag
    public void addMoreToBag() {
        driver.findElement(addMoreToBag).click();
    }

    // method to get the get Quantity
    public String getQuantity() {
        String q = driver.findElement(quantity).getAttribute("value");
        return q;
    }

    // method to click btn to checkout
    public void checkOutBtnClick() {
        driver.findElement(checkout).click();
    }


}

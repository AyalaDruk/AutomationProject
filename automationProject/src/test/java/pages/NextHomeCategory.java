package pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NextHomeCategory {
    WebDriver driver;


    //constructor

    public NextHomeCategory(WebDriver driver) {
        this.driver = driver;
    }


    // locators of category

    By homeWareCategory = By.id("meganav-link-6");
    By kitchenLink = By.cssSelector("a.sidebar-links[href='/en/shop/department-homeware-productaffiliation-kitchen-0']");
    By bathroomCategory = By.xpath("//*[@id=\"multi-9-teaser-395742-1_item_1\"]/div/a/div[2]/h3");

    By babyCategory = By.id("meganav-link-3");

    // locators to change language to hebrew
    By countryFlags = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[2]/div[6]/button/img");

    By countryLanguage = By.cssSelector("button[data-testid='country-selector-language-button-0']");

    By shopNow = By.xpath("//*[@id=\"header-country-selector-wrapper\"]/div/div[3]/div/div[5]/button");


    //Methods
    // method to double-click action on home ware category
    public void homeWareCategoryDClick() {
        // Creates an Actions instance
        Actions actions = new Actions(driver);
        // find element by locator homeWareCategory
        WebElement homeWare = driver.findElement(homeWareCategory);
        // Performs a double click action
        actions.doubleClick(homeWare).build().perform();
    }

    // method to click on kitchen link
    public void shopByRoomKitchenClick() {
        driver.findElement(kitchenLink).click();
    }

    // method to click on bathroom link
    public void bathroomCategoryClick() {
        driver.findElement(bathroomCategory).click();
    }

    // method to double-click action on baby category
    public void babyBannerCategoryDClick() {
        // Creates an Actions instance
        Actions actions = new Actions(driver);
        // find element by locator babyCategory
        WebElement babyBanner = driver.findElement(babyCategory);
        // Performs a double click action
        actions.doubleClick(babyBanner).build().perform();
    }

    // method to navigate back to homWareCategory
    public void navigateBackToHomeCategory() {
        driver.navigate().back();
    }

    // method to  change Language to hebrew
    public void changeLanguage() {
        driver.findElement(countryFlags).click();
        driver.findElement(countryLanguage).click();
        driver.findElement(shopNow).click();
    }


}

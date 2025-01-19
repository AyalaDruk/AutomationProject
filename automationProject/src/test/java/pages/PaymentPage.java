package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {

    WebDriver driver;

    //constructor
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    // locator choose a card payment
    By cardPayment = By.id("card-payment-main-header");
    // locator card number
    By cardNumber = By.id("cardNumber");
    // locator expire month card
    By expireMM = By.id("expiryMonth");
    // locator expire year card
    By expireYY = By.name("expiryDate.expiryYear");
    // locator securityCode  card
    By securityCode = By.name("securityCode");
    // locator pay now button
    By payNowBtn = By.cssSelector("input[value='Pay Now']");

    // locator error_hint = 'Enter a valid card number'
    By error_hint = By.xpath("//*[@id=\"cardNumber-hint\"]");

    // methods
    // method click to chose card payment
    public void choseCardPayment() {
        driver.findElement(cardPayment).click();
    }

    // method to enter a card number
    public void enterCardNumber(String cardN) {
        driver.findElement(cardNumber).sendKeys(cardN);
    }

    // method get the entered card number
    public String getCardNumber() {
        String enteredC = driver.findElement(cardNumber).getText();
        return enteredC;
    }

    // method to enter ExpiredMonth
    public void enterExpiredMonth(String expireM) {
        driver.findElement(expireMM).sendKeys(expireM);
    }

    // method get the entered ExpiredMonth
    public String getExpiredMonth() {
        String getM = driver.findElement(expireMM).getText();
        return getM;
    }


    // method to enter ExpiredYear
    public void enterExpiredYear(String expireY) {
        driver.findElement(expireYY).sendKeys(expireY);
    }

    // method get the entered ExpiredMonth
    public String getExpiredYear() {
        String getY = driver.findElement(expireYY).getText();
        return getY;
    }

    // method to enter SecurityCode
    public void enterSecurityCode(String securityC) {
        driver.findElement(securityCode).sendKeys(securityC);
    }

    // method get the entered SecurityCode
    public String getSecurityCode() {
        String getCode = driver.findElement(securityCode).getText();
        return getCode;
    }

    // method to check if error_hint display
    public boolean errorHintDisplay() {
        return driver.findElement(error_hint).isDisplayed();

    }

    // get the error_hint
    public String getHintDisplay() {
        return driver.findElement(error_hint).getText();

    }

    // method to click payNow button
    public void payNowBtnClick() {
        driver.findElement(payNowBtn).click();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignIntoNextPage {

    WebDriver driver;

    //constructor
    public SignIntoNextPage(WebDriver driver) {
        this.driver = driver;
    }

    // locators
    // get myAccount locator by xpath
    By myAccount = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[2]/div[2]/div[2]/div/a/p");
    //get emailAddress locator by id
    By emailAddress = By.id("EmailOrAccountNumber");
    //get password locator by cssSelector
    By password = By.cssSelector("#Password");
    // get signInBtn locator by name
    By signInBtn = By.name("SignInNow");

    // methods
    // method to click on the MyAccount element
    public void clickMyAccount() {
        driver.findElement(myAccount).click();
    }

    // method to enter the  Email address
    public void enterEmailAddress(String eAddress) {
        driver.findElement(emailAddress).sendKeys(eAddress);
    }

    // method to get  EmailAddress text
    public String getEmailAddress() {
        String emailA = driver.findElement(emailAddress).getAttribute("value");
        return emailA;
    }


    // method to enter the  password
    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    // method to get  password text
    public String getPassword() {
        String pass = driver.findElement(password).getAttribute("value");
        return pass;
    }

    // method to click the SignIn button
    public void ClickSignInBtn() {
        driver.findElement(signInBtn).click();
    }


}

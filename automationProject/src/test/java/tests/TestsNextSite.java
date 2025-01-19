package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.checkerframework.checker.units.qual.C;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.xml.sax.SAXException;
import pages.*;
import utilities.Constants;
import utilities.Methods;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestsNextSite {
    // Declares a static WebDriver instance
    private static WebDriver driver;
    //Creates an instance of the SignIntoNextPage class
    SignIntoNextPage signIntoNextPage = new SignIntoNextPage(driver);
    // Creates an instance of the NextHomeCategory class
    NextHomeCategory nextHomeCategory = new NextHomeCategory(driver);
    // Creates an instance of the SearchPage class
    SearchPage searchPage = new SearchPage(driver);
    // Creates an instance of the ProductPage class
    ProductPage productPage = new ProductPage(driver);
    // Creates an instance of the PaymentPage class
    PaymentPage paymentPage = new PaymentPage(driver);
    //reports
    private static ExtentReports reports = new ExtentReports();

    private static ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
    // Creates an instance of the Methods class
    Methods methods = new Methods(driver);
    // Save the current time
    String currentTime = String.valueOf(System.currentTimeMillis());

    @BeforeClass
    public static void beforeClass() {
        System.out.println("start");
       /* ChromeOptions options=new ChromeOptions();
        options.addArguments("-incognito");
        driver=new ChromeDriver(options);*/
        // report attach
        reports.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Next website tests");
        spark.config().setDocumentTitle("Sanity tests");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void A_nextLoginPage() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        System.out.println("sign in next page");
        ExtentTest loginTest = reports.createTest("next_login_page");
        loginTest.info("Navigating to the Next home page");
        // navigate to next home page
        driver.get(Constants.NEXT_HOME_PAGE_URL_ENG);
        // validation test - if the actual pageUrl equals to expect pageUrl( next home page)
        String actualNextHomeUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(Constants.NEXT_HOME_PAGE_URL_ENG, actualNextHomeUrl);
            loginTest.pass("Successfully navigated to  next home page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "home page" + currentTime)).build());
            Thread.sleep(2000);
            loginTest.info("Current URL: " + actualNextHomeUrl);
            Thread.sleep(2000);
        } catch (AssertionError e) {
            loginTest.fail("Failed to navigate to  next home page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "home page" + currentTime)).build());
            Thread.sleep(2000);
            loginTest.info("Current URL:" + actualNextHomeUrl);
            Thread.sleep(2000);
        }
        // click on my account
        signIntoNextPage.clickMyAccount();
        Thread.sleep(2000);
        // validation test - if the actual pageUrl equals to expect pageUrl (next login page)
        String expectLoginUrl = Constants.NEXT_LOGIN_PAGE;
        String actualLoginUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(expectLoginUrl, actualLoginUrl);
            loginTest.pass("Successfully navigated to the login page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "login page" + currentTime)).build());
            Thread.sleep(2000);
            loginTest.info("Current URL: " + actualLoginUrl);
            Thread.sleep(2000);
        } catch (AssertionError e) {
            loginTest.fail("Failed to navigate to the login page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "login page" + currentTime)).build());
            Thread.sleep(2000);
            loginTest.info("Current URL:" + actualLoginUrl);
        }
        // enter email address & password to sign in
        signIntoNextPage.enterEmailAddress(methods.getData("email"));
        Thread.sleep(2000);
        // validation test -  if actual email address equal to expect email
        String actualEmailAddress = signIntoNextPage.getEmailAddress();
        try {
            Assert.assertEquals(methods.getData("email"), actualEmailAddress);
            Thread.sleep(2000);
            loginTest.pass("[pass] the email address is correct ", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "email" + currentTime)).build());
            loginTest.info(" email :" + signIntoNextPage.getEmailAddress());
        } catch (Exception e) {
            loginTest.fail("[Fail] the email address is not correct", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "email" + currentTime)).build());
            loginTest.info(" email :" + signIntoNextPage.getEmailAddress());
        }
        // enter password to sign in
        signIntoNextPage.enterPassword(methods.getData("password"));
        // validation test -  if actual password equal to expect password
        String actualPassword = signIntoNextPage.getPassword();
        try {
            Assert.assertEquals(methods.getData("password"), actualPassword);
            Thread.sleep(2000);
            loginTest.pass("[PASS] The password entered matches the expected value", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "password" + currentTime)).build());
            Thread.sleep(2000);
            loginTest.info("the password entered: " + actualPassword);
        } catch (Exception e) {
            loginTest.pass("[FAIL] The password does not match the expected value", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "password" + currentTime)).build());
            Thread.sleep(2000);
            loginTest.info("the password entered: " + actualPassword);

        }
        Thread.sleep(2000);
        // click on the button to sign in
        signIntoNextPage.ClickSignInBtn();
        //validation test - if the actual pageUrl equals to expect pageUrl
        String expectUrlAccount = Constants.NEXT_MY_ACCOUNT;
        String actualUrlAccount = driver.getCurrentUrl();
        try {
            Assert.assertEquals(expectUrlAccount, actualUrlAccount);
            loginTest.pass("[PASS] Successfully logged into the site with an existing account", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "logged" + currentTime)).build());
            loginTest.info("url account " + actualUrlAccount);
        } catch (Exception E) {
            loginTest.fail("[FAIL] Login to the site with an existing account failed", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "logged" + currentTime)).build());
            loginTest.info("url account " + actualUrlAccount);
        }
        // navigate back to home page
        driver.navigate().to((Constants.NEXT_HOME_PAGE_URL_ENG));
        Thread.sleep(2000);
    }


    @Test
    public void B_homeWareCategory() throws InterruptedException {
        System.out.println("home category tests");
        ExtentTest homeCategoryTests = reports.createTest("sanity tests home category");
        homeCategoryTests.info("homeWare page tests");
        driver.get(Constants.NEXT_HOME_PAGE_URL_ENG);
        // validation test - if the actual pageUrl equals to expect pageUrl( next home page)
        String actualNextHomeUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(Constants.NEXT_HOME_PAGE_URL_ENG, actualNextHomeUrl);
            homeCategoryTests.pass("Successfully navigated to  next home page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "nextHomeE" + currentTime)).build());
            Thread.sleep(2000);
            homeCategoryTests.info("Current URL: " + actualNextHomeUrl);
            Thread.sleep(2000);
        } catch (Exception e) {
            homeCategoryTests.fail("Failed to navigate to  next home page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "nextHomeE" + currentTime)).build());
            Thread.sleep(2000);
            homeCategoryTests.info("Current URL:" + actualNextHomeUrl);
            Thread.sleep(2000);
        }
        //double click actions on tha home ware category
        nextHomeCategory.homeWareCategoryDClick();
        // validation test - if the action success and we in the expected category (homeWareCategoryPage)
        String actualHomeWareCUrl = driver.getCurrentUrl();
        Thread.sleep(3000);
        try {
            Assert.assertEquals(actualHomeWareCUrl, Constants.NEXT_HOME_WARE);
            Thread.sleep(3000);
            homeCategoryTests.pass("[PASS] the test was successful we are in the expected category: HOME_WARE CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "HOME_WARE" + currentTime)).build());
            Thread.sleep(3000);
            homeCategoryTests.info("url : " + actualHomeWareCUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are in a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "HOME_WARE" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualHomeWareCUrl);
        }
        // click on the kitchen link at homeWare category
        nextHomeCategory.shopByRoomKitchenClick();
        // validation test - if the actual pageUrl equals to expect pageUrl (kitchen category)
        String actualHomeWareKitchen_Url = driver.getCurrentUrl();
        Thread.sleep(3000);
        try {
            Assert.assertEquals(actualHomeWareKitchen_Url, Constants.NEXT_HOME_WARE_KITCHEN);
            Thread.sleep(3000);
            homeCategoryTests.pass("[PASS] the test was successful we are in the expected category: KITCHEN CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "kitchen" + currentTime)).build());
            Thread.sleep(3000);
            homeCategoryTests.info("url : " + actualHomeWareKitchen_Url);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are in a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "kitchen" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualHomeWareKitchen_Url);
        }
        Thread.sleep(3000);
        // navigate back to home category
        nextHomeCategory.navigateBackToHomeCategory();
        // validation test if the navigate back to homeWareCategory success
        Thread.sleep(3000);
        try {
            Assert.assertEquals(actualHomeWareCUrl, Constants.NEXT_HOME_WARE);
            Thread.sleep(3000);
            homeCategoryTests.pass("[PASS] the test was successful we are navigate back the expected category: HOME_WARE CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "homeWareCategory" + currentTime)).build());
            Thread.sleep(3000);
            homeCategoryTests.info("url : " + actualHomeWareCUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are navigate a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "homeWareCategory" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualHomeWareCUrl);
        }
        // click on the bathroomCategory link at home category
        Thread.sleep(3000);
        nextHomeCategory.bathroomCategoryClick();
        // validation test - if the actual pageUrl equals to expect pageUrl (bathroom category)
        String actualBathroomUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualBathroomUrl, Constants.NEXT_HOME_WARE_BATHROOM);
            Thread.sleep(3000);
            homeCategoryTests.pass("[PASS] the test was successful we are in the expected category: BATHROOM CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "bathroomCategory" + currentTime)).build());
            Thread.sleep(3000);
            homeCategoryTests.info("url : " + actualBathroomUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are in a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "bathroomCategory" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualBathroomUrl);
        }
        Thread.sleep(3000);
        // navigate back to home category
        nextHomeCategory.navigateBackToHomeCategory();
        // validation test if the navigate back to homeWareCategory success
        Thread.sleep(3000);
        try {
            Assert.assertEquals(actualHomeWareCUrl, Constants.NEXT_HOME_WARE);
            Thread.sleep(3000);
            homeCategoryTests.pass("[PASS] the test was successful we are navigate back the expected category: HOME_WARE CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "homeWareCategory" + currentTime)).build());
            Thread.sleep(3000);
            homeCategoryTests.info("url : " + actualHomeWareCUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are navigate back a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "homeWareCategory" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualHomeWareCUrl);
        }
        //  double-click on babyBannerCategory link
        nextHomeCategory.babyBannerCategoryDClick();
        // validation test - if the action success and we in the expected category (babyBannerCategory)
        String actualBabyUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualBabyUrl, Constants.NEXT_BABY_PAGE);
            homeCategoryTests.pass("[PASS] the test was successful we are in the expected category: BABY  CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "babyBannerCategory" + currentTime)).build());
            homeCategoryTests.info("url : " + actualBabyUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are in a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "babyBannerCategory" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualBabyUrl);
        }
        Thread.sleep(3000);
        // navigate back to home category
        nextHomeCategory.navigateBackToHomeCategory();
        // validation test if the navigate back to homeWareCategory success
        Thread.sleep(3000);
        try {
            Assert.assertEquals(actualHomeWareCUrl, Constants.NEXT_HOME_WARE);
            Thread.sleep(3000);
            homeCategoryTests.pass("[PASS] the test was successful we navigate back in the expected category: HOME_WARE CATEGORY", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "homeWareCategory" + currentTime)).build());
            Thread.sleep(3000);
            homeCategoryTests.info("url : " + actualHomeWareCUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful we are navigate a different category that expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "homeWareCategory" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualHomeWareCUrl);
        }
        // change language to hebrew
        nextHomeCategory.changeLanguage();
        // check if language change to hebrew
        String actualLanguageUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualLanguageUrl, Constants.NEXT_HOME_PAGE_URL_HEB);
            homeCategoryTests.pass("[PASS] the test was successful ,the language was changed to Hebrew", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "language" + currentTime)).build());
            homeCategoryTests.info("url : " + actualLanguageUrl);
        } catch (Exception E) {
            homeCategoryTests.fail("[fail] the test was unsuccessful the language wasn't changed to Hebrew", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "language" + currentTime)).build());
            homeCategoryTests.info(" actual url : " + actualLanguageUrl);
        }

    }

    @Test
    public void D_searchPage() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        System.out.println("search product");
        ExtentTest searchProduct = reports.createTest("search product");
        searchProduct.info("search for a product in the search box");
        // navigate next home url
        driver.navigate().to(Constants.NEXT_HOME_PAGE_URL_ENG);
        // validation test - if the actual pageUrl equals to expect pageUrl( next home page)
        String actualNextHomeUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(Constants.NEXT_HOME_PAGE_URL_ENG, actualNextHomeUrl);
            searchProduct.pass("Successfully navigated to  next home page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "nextHomePage" + currentTime)).build());
            Thread.sleep(2000);
            searchProduct.info("Current URL: " + actualNextHomeUrl);
            Thread.sleep(2000);
        } catch (Exception e) {
            searchProduct.fail("Failed to navigate to  next home page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "nextHomePage" + currentTime)).build());
            Thread.sleep(2000);
            searchProduct.info("Current URL:" + actualNextHomeUrl);
            Thread.sleep(2000);
        }
        // search a product
        searchPage.SearchProduct(methods.getData("product"));
        Thread.sleep(2000);
        // check if we search the expected product
        String actualProductUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualProductUrl, Constants.NEXT_PRODUCT_URL);
            Thread.sleep(3000);
            searchProduct.pass("[PASS] the test was successful ,the actual product is  the expected one", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "product" + currentTime)).build());
            Thread.sleep(3000);
            searchProduct.info("url : " + actualProductUrl);
        } catch (Exception E) {
            Thread.sleep(3000);
            searchProduct.fail("[fail] the test was unsuccessful the actual product is not  the expected one", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "product" + currentTime)).build());
            Thread.sleep(3000);
            searchProduct.info(" actual url : " + actualProductUrl);
        }
    }

    @Test
    public void E_productPage() throws InterruptedException {
        System.out.println("product page");
        ExtentTest addProductToBag = reports.createTest("product page");
        addProductToBag.info("choose a size and color and add the  product to the bag");
        driver.get(Constants.NEXT_PRODUCT_URL);
        Thread.sleep(3000);
        // choose color
        productPage.chooseColor();
        Thread.sleep(3000);
        // check if choose the expected color (Pink Heart)
        String actualUrlChosenColor = driver.getCurrentUrl();
        try {
            Assert.assertEquals(Constants.CHOSEN_COLOR_PRODUCT_URL, actualUrlChosenColor);
            addProductToBag.pass("[pass]the test  successful the expected color selected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "color" + currentTime)).build());

            addProductToBag.info("was the expected color chosen ? " + actualUrlChosenColor);
            Thread.sleep(2000);
        } catch (AssertionError e) {
            Thread.sleep(3000);
            addProductToBag.fail("[fail ]the test  unsuccessful the expected color  wasn't selected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "color" + currentTime)).build());
            addProductToBag.info("was the expected color chosen ? " + actualUrlChosenColor);
        }
        // choose size
        productPage.chooseSize();
        // check if chosen the expected size (eu 23)
        String getSize = productPage.getChosenSize();
        if (getSize.equals(Constants.PRODUCT_SIZE)) {
            Thread.sleep(3000);
            addProductToBag.pass("[pass]the test  successful the expected size selected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "size" + currentTime)).build());
            addProductToBag.info("the chosen size : " + getSize);
        } else {
            Thread.sleep(3000);
            addProductToBag.fail("[fail ]the test  unsuccessful the expected color  wasn't selected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "size" + currentTime)).build());
            addProductToBag.info("the chosen size : " + getSize);
        }
        // add the product to the bag
        productPage.addToBag();
        Thread.sleep(3000);
        //view bag
        productPage.viewBag();
        Thread.sleep(3000);
        // add another one of the product to the bag
        productPage.addMoreToBag();
        // check if the quantity of products the same as expected
        String actualQuantity = productPage.getQuantity();
        if (actualQuantity.equals("2")) {
            Thread.sleep(3000);
            addProductToBag.pass("[pass]the test  successful the quantity of product items is the same as expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "quantity" + currentTime)).build());
            addProductToBag.info("the quantity of items : " + actualQuantity);
        } else {
            Thread.sleep(3000);
            addProductToBag.fail("[fail ]the test  unsuccessful the quantity of product items isn't the same as expected", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "quantity" + currentTime)).build());
            addProductToBag.info("the quantity of items : " + actualQuantity);
        }
        // checkout
        productPage.checkOutBtnClick();
        // check if the actual url equals to expected one (payment url)
        String actualCheckoutUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(Constants.LOGIN_CHECKOUT_URL, actualCheckoutUrl);
            addProductToBag.pass("Successfully navigated to checkout page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "checkout" + currentTime)).build());
            Thread.sleep(2000);
            addProductToBag.info("Current URL: " + actualCheckoutUrl);
            Thread.sleep(2000);
        } catch (Exception e) {
            addProductToBag.fail("Failed to navigate to checkout page", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "checkout" + currentTime)).build());
            Thread.sleep(2000);
            addProductToBag.info("Current URL:" + actualCheckoutUrl);
            Thread.sleep(2000);
        }


    }

    @Ignore
    @Test
    public void F_paymentPage() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        System.out.println("payment page tests");
        ExtentTest checkoutTests = reports.createTest("payment tests");
        // navigate payment page url
        driver.get(Constants.NEXT_PAYMENT_PAGE_URL);
        // chose card payment
        paymentPage.choseCardPayment();
        Thread.sleep(2000);
        // enter a card_number
        paymentPage.enterCardNumber(methods.getData("cardNumber"));
        Thread.sleep(2000);
        // check if enter the expected card_number
        String actualEnteredCard = paymentPage.getCardNumber();
        try {
            Assert.assertEquals(methods.getData("cardNumber"), actualEnteredCard);
            checkoutTests.pass("[pass]the test  successful the expected card_number entered", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "enteredCardN" + currentTime)).build());
            checkoutTests.info("card num : " + actualEnteredCard);
        } catch (AssertionError e) {
            checkoutTests.fail("[fail]the test  unsuccessful incorrect card_number entered", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "enteredCardN" + currentTime)).build());
            checkoutTests.info("card num : " + actualEnteredCard);
        }
        // enter expire date
        paymentPage.enterExpiredMonth(methods.getData("expireM"));
        Thread.sleep(2000);
        paymentPage.enterExpiredYear(methods.getData("expireY"));
        Thread.sleep(2000);
        //check if the expected expiry date entered
        String actualEMonth = paymentPage.getExpiredMonth();
        String actualEYear = paymentPage.getExpiredYear();
        try {
            Assert.assertEquals(methods.getData("expireM"), actualEMonth);
            Assert.assertEquals(methods.getData("expireY"), actualEYear);
            checkoutTests.pass("[pass]the test  successful the expected expiry_date  entered", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "YY-MM" + currentTime)).build());
            checkoutTests.info("expiry date month : " + actualEMonth);
            checkoutTests.info("expiry date year : " + actualEYear);

        } catch (AssertionError e) {
            checkoutTests.fail("[fail ]the test  unsuccessful incorrect expiry_date  entered", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "YY-MM" + currentTime)).build());
            checkoutTests.info("expiry date month : " + actualEMonth);
            checkoutTests.info("expiry date year : " + actualEYear);
        }
        // enter security code
        paymentPage.enterSecurityCode(methods.getData("securityCode"));
        // check if the expected securityCode entered
        String actualSCode = paymentPage.getSecurityCode();
        try {
            Assert.assertEquals(methods.getData("securityCode"), actualSCode);
            checkoutTests.pass("[pass]the test  successful the expected securityCode  entered", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "securityCode" + currentTime)).build());
            checkoutTests.info("securityCode : " + actualSCode);


        } catch (AssertionError e) {
            checkoutTests.fail("[fail ]the test  unsuccessful incorrect securityCode  entered", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "securityCode" + currentTime)).build());
            checkoutTests.info("securityCode : " + actualSCode);

        }
        // check if the error display
        boolean errorIsDisplay = paymentPage.errorHintDisplay();
        // get the error message is display
        String errorMessage = paymentPage.getHintDisplay();
        if (errorIsDisplay) {
            checkoutTests.pass("[pass]the test  successful error_hint 'Enter a valid card number' is display", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "errorD" + currentTime)).build());
            checkoutTests.info("error : " + errorMessage);
        } else {
            checkoutTests.pass("[fail ]the test  unsuccessful error_hint 'Enter a valid card number' isn't display", MediaEntityBuilder.createScreenCaptureFromPath(methods.takeScreenShot("spark\\pictures" + "errorD" + currentTime)).build());
            checkoutTests.info("error : " + errorMessage);

        }
        //click on button payNow to pay
        paymentPage.payNowBtnClick();


    }

    @AfterClass
    public static void afterClass() throws InterruptedException {
        System.out.println("end");
        Thread.sleep(4000);
        reports.flush();
        driver.quit();
    }
}

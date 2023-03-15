package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePageObjects;
import pages.LoginPageObjects;

import java.time.Duration;

public class LoginPageRunTestNGGroups {
    WebDriver driver = null; //    Initializing the WebDriver
    LoginPageObjects loginPageObj;
    HomePageObjects homePageObj;
    static String expected_welcomeHeaderText = "Welcome To Manager's Page of Guru99 Bank";
    static String expected_pageTitleText = "Guru99 Bank Home Page";
    static String expected_TextOnIncorrectLoginAlert = "User is not valid";

    @Parameters("browserName")
    @BeforeTest (groups = {"sanity", "smoke", "windows.regression", "regression"})
    public void beforeTestSteps(String browserName) throws Exception {

//        Initialize browser using the Web driver Manager.
        if(browserName.equalsIgnoreCase("chrome")){
            driver = WebDriverManager.chromedriver().create();
        }
        else{
            throw new Exception("Incorrect Browser");
        }
        //        Navigate to the demo web page
        driver.get("https://demo.guru99.com/v3/index.php");
//        Maximize the window
        driver.manage().window().maximize();
    }

    @Test (groups = {"sanity", "smoke"})
    public void verifyPageTitle(){
        loginPageObj = new LoginPageObjects(driver);
        String actual_PageTitleText = loginPageObj.getPageTitle_login();
        Assert.assertEquals(actual_PageTitleText,expected_pageTitleText);
        System.out.println("Page title is : " + actual_PageTitleText);
    }

    @Test (groups = {"sanity"})
    public void verifyIfStepsToGenerateAccessBannerIsPresent(){
        loginPageObj = new LoginPageObjects(driver);
        Boolean isStepsToGenerateAccessBannerPresent = loginPageObj.getAvailabilityOfStepsToGenerateAccessBanner();
        Assert.assertTrue(isStepsToGenerateAccessBannerPresent);
    }

    @Test (groups = {"smoke"})
    public void verifyIfResetButtonClearsUserIdAndPassword(){
        loginPageObj = new LoginPageObjects(driver);
        loginPageObj.setTextInUserId("DummyUserId");
        loginPageObj.setTextInPassword("DummyPassword");
        loginPageObj.clickOnResetButton();
        String actual_userIdText = loginPageObj.getTextInUserId();
        Assert.assertEquals(actual_userIdText, "");
        String actual_passwordText = loginPageObj.getTextInPassword();
        Assert.assertEquals(actual_passwordText, "");
    }

    @Test (groups = {"smoke"}) //    Negative Test of Login - 1
    public void verifyLoginWithIncorrectUserIdAndCorrectPassword(){
        loginPageObj = new LoginPageObjects(driver);
        loginPageObj.setTextInUserId("DummyUserId");
        loginPageObj.setTextInPassword("gAnEbuz");
        loginPageObj.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String actual_TextOnIncorrectLoginAlert = loginPageObj.getTextOnAlert();
        Assert.assertEquals(actual_TextOnIncorrectLoginAlert, expected_TextOnIncorrectLoginAlert);
        System.out.println("The text on the 'Incorrect Login' pop-up alert when we enter incorrect UserID is : " + actual_TextOnIncorrectLoginAlert);
    }

    @Test (groups = {"smoke"})
    public void verifyUserNavigatingToLoginAfterAcceptingAlert(){
        loginPageObj = new LoginPageObjects(driver);
        loginPageObj.clickOnAlertOkButton();
        String actual_PageTitleText = loginPageObj.getPageTitle_login();
        Assert.assertEquals(actual_PageTitleText,expected_pageTitleText);
        System.out.println("Page title is : " + actual_PageTitleText);
    }

    @Test (priority = 6) //    Negative Test of Login - 2
    public void verifyLoginWithCorrectUserIdAndIncorrectPassword(){
        loginPageObj = new LoginPageObjects(driver);
        loginPageObj.setTextInUserId("mngr480452");
        loginPageObj.setTextInPassword("DummyPassword");
        loginPageObj.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String actual_TextOnIncorrectLoginAlert = loginPageObj.getTextOnAlert();
        Assert.assertEquals(actual_TextOnIncorrectLoginAlert, expected_TextOnIncorrectLoginAlert);
        System.out.println("The text on the 'Incorrect Login' pop-up alert when we enter incorrect Password is : " + actual_TextOnIncorrectLoginAlert);
        loginPageObj.clickOnAlertOkButton();
    }

    @Test (priority = 7) //    Negative Test of Login - 3
    public void verifyLoginWithEmptyUserIdAndEmptyPassword(){
        loginPageObj = new LoginPageObjects(driver);
        if(loginPageObj.getTextInUserId().equals("") && loginPageObj.getTextInPassword().equals("") ){
            loginPageObj.clickOnLoginButton();
        }
        else {
            loginPageObj.setTextInUserId("");
            loginPageObj.setTextInPassword("");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String actual_TextOnIncorrectLoginAlert = loginPageObj.getTextOnAlert();
        Assert.assertEquals(actual_TextOnIncorrectLoginAlert, expected_TextOnIncorrectLoginAlert);
        System.out.println("The text on the 'Incorrect Login' pop-up alert when we enter incorrect Password is : " + actual_TextOnIncorrectLoginAlert);
        loginPageObj.clickOnAlertOkButton();
    }

    @Test (groups = {"regression"})
    public void verifyUserLogin() throws InterruptedException {
//        Create and object from the page classes
        loginPageObj = new LoginPageObjects(driver);
        homePageObj = new HomePageObjects(driver);
//        calling functions from the page class using the page class object
        loginPageObj.setTextInUserId("mngr480452");
        loginPageObj.setTextInPassword("gAnEbuz");
        loginPageObj.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        Get the actual Home page welcome header text
        String actual_welcomeHeaderText = homePageObj.getTextOfWelcomeHeader();
//        Verify the Home page welcome header text
        Assert.assertEquals(actual_welcomeHeaderText, expected_welcomeHeaderText);
    }

    @AfterTest
    public void afterTestSteps(){
        driver.quit();
    }
}

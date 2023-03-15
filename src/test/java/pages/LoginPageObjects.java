package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObjects {

//    Initializing the WebDriver
    WebDriver driver;

//    Locating Objects
    By userId_login = By.name("uid");
    By password_login = By.name("password");
    By loginButton_login = By.name("btnLogin");
    By resetButton_login = By.name("btnReset");
    By stepsToGenerateAccessBanner_login =By.xpath("//h4[@class='barone']");

//    Creating WebDriver constructor in order to call from the test class. We need to user driver instance from the test class.
    public LoginPageObjects(WebDriver driver){
        this.driver = driver;
    }

//    Adding actions to the objects
    public void setTextInUserId(String userId){
        driver.findElement(userId_login).sendKeys(userId);
    }

    public void setTextInPassword(String password){
        driver.findElement(password_login).sendKeys(password);
    }

    public String getTextInUserId(){
        return driver.findElement(userId_login).getText();
    }

    public String getTextInPassword(){
        return driver.findElement(password_login).getText();
    }

    public void clickOnLoginButton(){
        driver.findElement(loginButton_login).click();
    }

    public void clickOnResetButton(){
        driver.findElement(resetButton_login).click();
    }

    public String getPageTitle_login(){
        return driver.getTitle();
    }

    public Boolean getAvailabilityOfStepsToGenerateAccessBanner(){
        return driver.findElement((stepsToGenerateAccessBanner_login)).isDisplayed();
    }

    public String getTextOnAlert(){
        return driver.switchTo().alert().getText();
    }

    public void clickOnAlertOkButton(){
        driver.switchTo().alert().accept();
    }

}

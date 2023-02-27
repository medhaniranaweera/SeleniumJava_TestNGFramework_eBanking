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

    public void clickOnLoginButton(){
        driver.findElement(loginButton_login).click();
    }
}

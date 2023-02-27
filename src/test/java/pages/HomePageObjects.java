package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {

//    Initializing the WebDriver
    WebDriver driver;
    String actual_welcomeHeaderText;

//    Locating Objects
    By welcomeHeader = By.className("heading3");

//    Creating WebDriver constructor
    public HomePageObjects(WebDriver driver){
        this.driver = driver;
    }

//    Adding actions to the objects
    public String getTextOfWelcomeHeader(){
        actual_welcomeHeaderText = driver.findElement(welcomeHeader).getText();
        return actual_welcomeHeaderText;
    }
}

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePageObjects;
import pages.LoginPageObjects;
import java.time.Duration;

public class LoginPageCrossBrowserTest {
    WebDriver driver = null; //    Initializing the WebDriver
    LoginPageObjects loginPageObj;
    HomePageObjects homePageObj;
    static String expected_welcomeHeaderText = "Welcome To Manager's Page of Guru99 Bank";

    @Parameters("browserName") // take the browserName parameter from the testngTestWithExtentReports.xml file
    @BeforeTest
    public void beforeTestSteps(String browserName) throws Exception {

        System.out.println("Browser name is : " + browserName); // Printing the browser name to verify if the test is running on multiple browsers
        System.out.println("Thread id is : " + Thread.currentThread().getId()); // Printing the thread to verify if the test is running on multiple threads

//        Initialize browser using the Web driver Manager. Utilizing the browserName parameter from testngTestWithExtentReports.xml file
        if(browserName.equalsIgnoreCase("chrome")){
            driver = WebDriverManager.chromedriver().create();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
        }
        else if (browserName.equalsIgnoreCase("msedge")) {
            driver = WebDriverManager.edgedriver().create();
        }
        else{
//            If no browser is passed throw exception
            throw new Exception("Incorrect Browser");
        }
    }

    @Test (priority = 1)
    public void verifyUserLogin() throws InterruptedException {
//        Create and object from the page class
        loginPageObj = new LoginPageObjects(driver);
//        Navigate to the demo web page
        driver.get("https://demo.guru99.com/v3/index.php");
//        Maximize the window
        driver.manage().window().maximize();

//        calling functions from the page class using the page class object
        loginPageObj.setTextInUserId("mngr480452");
        loginPageObj.setTextInPassword("gAnEbuz");
        loginPageObj.clickOnLoginButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test (priority = 2)
    public void verifyHomePageTitle(){
//        Create and object from the page class
        homePageObj = new HomePageObjects(driver);

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

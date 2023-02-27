package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePageObjects;
import pages.LoginPageObjects;

public class LoginPageTest {

//    Initializing the WebDriver
    static WebDriver driver = null;
    ExtentReports extent;
    ExtentSparkReporter spark;
    ExtentTest testLogin, testHomePageTitle;
    LoginPageObjects loginPageObj;
    HomePageObjects homePageObj;

    static String expected_welcomeHeaderText = "Welcome To Manager's Page of Guru99 Bank";

    @BeforeSuite
    public void beforeTestSteps(){
//        start reporters
        extent = new ExtentReports();
//        Create the htmlReporter object. Name of the file is given as 'Spark.html'. The path to the file generation is given as 'target/Spark'
        spark = new ExtentSparkReporter("target/Spark/Spark.html#test-id=2"); //To navigate to the test using the anchor,uri component is added at the end of the report path
//        attach reporters
        extent.attachReporter(spark);

//        Spark reporter configurations
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");

//        Initialize browser Using the Web driver Manager
        driver = WebDriverManager.chromedriver().create();
//        Navigate to the demo web page
        driver.get("https://demo.guru99.com/v3/index.php");
//        test.pass("Navigated to the demo web page");
//        Maximize the window
        driver.manage().window().maximize();
    }

    @Test (priority = 1)
    public void verifyUserLogin() throws InterruptedException {
//        Create and object from the page class
        loginPageObj = new LoginPageObjects(driver);
//        creates a toggle for the given test, add all log events under it
        testLogin = extent.createTest("Bank Login Test","Test to verify the login");
        testLogin.log(Status.INFO,"Starting the test");

//        extent.createTest("LogLevels").info("info").pass("pass").warning("warning").skip("skip").fail("fail").addScreenCaptureFromPath("screenshot.png");

//        calling functions from the page class using the page class object
        loginPageObj.setTextInUserId("mngr480452");
        testLogin.pass("User name is entered");

        loginPageObj.setTextInPassword("gAnEbuz");
        testLogin.pass("Password is entered");

        loginPageObj.clickOnLoginButton();
        testLogin.pass("Login button is pressed");
//        test with snapshot
        Thread.sleep(1000);
        testLogin.addScreenCaptureFromPath("screenshot.png");
    }

    @Test (priority = 2)
    public void verifyHomePageTitle(){
//        Create and object from the page class
        homePageObj = new HomePageObjects(driver);
        testHomePageTitle = extent.createTest("Bank Login Test","Test to verify the title of the home page");
        testHomePageTitle.log(Status.INFO,"Starting the test");

//        Get the actual Home page welcome header text
        String actual_welcomeHeaderText = homePageObj.getTextOfWelcomeHeader();
//        Verify the Home page welcome header text
        Assert.assertEquals(actual_welcomeHeaderText, expected_welcomeHeaderText);
        testHomePageTitle.pass("Home page welcome header is verified");
    }

    @AfterSuite
    public void afterTestSteps(){
        driver.close();
        testHomePageTitle.pass("Current browser window is closed");
        driver.quit();
        testHomePageTitle.pass("WebDriver session is terminated");
        testHomePageTitle.info("Test completed");

//        calling flush write everything to the log file
        extent.flush();
    }
}

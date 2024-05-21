package automation.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest extends BrowserUtil {
    protected WebDriver driver;
    protected Pages pages;

    @BeforeSuite
    public void beforeSuite() {
        deletePreviousAllureReport();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeEachMethod() {
        driver = DriverUtil.getDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.getProperty("baseURL"));

        //Initialize Pages
        pages = new Pages(driver);
    }


    @AfterMethod(alwaysRun = true)
    public void afterEachMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            failureScreenShot(driver);
        }
        driver.close();
        DriverUtil.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        generateNewAllureReport();
    }
}

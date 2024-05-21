package automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * I use Singleton design pattern in order to have one driver instance at the same time
 */

public class DriverUtil {


    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private DriverUtil() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = ConfigReader.getProperty("browser");
            switch (browser) {
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Invalid browser name!");
            }
        }
        return driverPool.get();
    }


    public static void quitDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }

}
package automation.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class BrowserUtil {

    @Attachment
    public byte[] failureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    public static void deletePreviousAllureReport() {
        String deletePreviousAllureReport = "rm -rf allure-results allure-report";
        try {
            Process process = Runtime.getRuntime().exec(deletePreviousAllureReport);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateNewAllureReport() {
        String generateAllureReport = "allure generate allure-results --clean";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(generateAllureReport);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    public static void waitForElementDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String roundIfNoDecimal(String str) {
        if (str.contains(".")) {
            int decimalIndex = str.indexOf(".");
            int decimalLength = str.length() - decimalIndex - 1;
            //This will round the number if the digit after decimalIndex is 0 : 8.0 to 8
            if (decimalLength == 1 && str.charAt(decimalIndex + 1) == '0') {
                return str.substring(0, decimalIndex);
            }
            //This will round: xx.yyyyyyyyyyyyyyy to -> xx.yyyyyyyyyy (only 10 digits after decimalIndex)
            if (str.substring(decimalIndex).length() > 10) {
                return str.substring(0, decimalIndex) + str.substring(decimalIndex, decimalIndex + 11);
            }
        }
        return str;
    }

}

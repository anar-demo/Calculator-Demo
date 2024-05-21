package automation.utils;

import automation.pageObjects.Calculator;
import org.openqa.selenium.WebDriver;

public class Pages {

    public Calculator calculatorPage;


    public Pages(WebDriver driver) {
        calculatorPage = new Calculator(driver);

    }
}

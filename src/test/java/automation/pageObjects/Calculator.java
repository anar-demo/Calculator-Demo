package automation.pageObjects;

import automation.utils.BrowserUtil;
import automation.utils.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Calculator {

    WebDriver driverLocal;

    public Calculator(WebDriver driver) {
        PageFactory.initElements(DriverUtil.getDriver(), this);
        driverLocal = driver;
    }

    @FindBy(css = ".calc .calc-display>div")
    public WebElement CALCULATOR_FIELD;

    @FindBy(xpath = "//div[@class='button-column rhs-ops']/div[5]")
    public WebElement PLUS_BTN;

    @FindBy(xpath = "//div[@class='button-column rhs-ops']/div[4]")
    public WebElement MINUS_BTN;

    @FindBy(xpath = "//div[@class='button-column rhs-ops']/div[3]")
    public WebElement MULTIPLY_BTN;

    @FindBy(xpath = "//div[@class='button-column rhs-ops']/div[2]")
    public WebElement DIVIDE_BTN;

    @FindBy(xpath = "//div[@id='view11']/div[1]/div[4]/div[3]")
    public WebElement EQUALS_BTN;

    @FindBy(xpath = "//div[@class='calculator-display']//div/span")
    public WebElement RESULT_FIELD;


    public void performCalculatorOperation(String operation, String a, String b) {
        WebElement element;
        switch (operation) {
            case "plus":
                element = PLUS_BTN;
                break;
            case "minus":
                element = MINUS_BTN;
                break;
            case "multiply":
                element = MULTIPLY_BTN;
                break;
            case "divide":
                element = DIVIDE_BTN;
                if (b.equals("0")) {
                    throw new ArithmeticException("The number cannot be divided by 0");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operation selected!");
        }
        CALCULATOR_FIELD.sendKeys(a);
        element.click();
        CALCULATOR_FIELD.sendKeys(b);
        EQUALS_BTN.click();
        BrowserUtil.waitForSeconds(2);
    }


}

package automation.tests;

import automation.utils.BaseTest;
import automation.utils.BrowserUtil;
import automation.utils.ExcelUtil;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTests extends BaseTest {

    @DataProvider(name = "addition")
    public Object[][] additionData() {
        ExcelUtil excelUtil = new ExcelUtil("src/test/java/automation/resources/Data.xlsx", "Addition");
        Object[][] detailsFromExcel = excelUtil.getDataArray();
        return detailsFromExcel;
    }


    @Test(dataProvider = "addition")
    public void additionOperation(String number_1, String number_2, String expectedResult) {
        Allure.description("Addition operation with: [" + number_1 + " " + number_2 + "]");
        pages.calculatorPage.performCalculatorOperation("plus", number_1, number_2);
        String actualResult = pages.calculatorPage.RESULT_FIELD.getText();
        Assert.assertEquals(actualResult, BrowserUtil.roundIfNoDecimal(expectedResult));
    }


    @DataProvider(name = "subtraction")
    public Object[][] subtractionData() {
        ExcelUtil excelUtil = new ExcelUtil("src/test/java/automation/resources/Data.xlsx", "Subtraction");
        Object[][] detailsFromExcel = excelUtil.getDataArray();
        return detailsFromExcel;
    }

    @Test(dataProvider = "subtraction")
    public void subtractionOperation(String number_1, String number_2, String expectedResult) {
        Allure.description("Subtraction operation with: [" + number_1 + " " + number_2 + "]");
        pages.calculatorPage.performCalculatorOperation("minus", number_1, number_2);
        String actualResult = pages.calculatorPage.RESULT_FIELD.getText();
        Assert.assertEquals(actualResult, BrowserUtil.roundIfNoDecimal(expectedResult));
    }

    @DataProvider(name = "multiplication")
    public Object[][] multiplicationData() {
        ExcelUtil excelUtil = new ExcelUtil("src/test/java/automation/resources/Data.xlsx", "Multiplication");
        Object[][] detailsFromExcel = excelUtil.getDataArray();
        return detailsFromExcel;
    }

    @Test(dataProvider = "multiplication")
    public void multiplicationOperation(String number_1, String number_2, String expectedResult) {
        Allure.description("Multiplication operation with: [" + number_1 + " " + number_2 + "]");
        pages.calculatorPage.performCalculatorOperation("multiply", number_1, number_2);
        String actualResult = pages.calculatorPage.RESULT_FIELD.getText();
        Assert.assertEquals(actualResult, BrowserUtil.roundIfNoDecimal(expectedResult));
    }


    @DataProvider(name = "division")
    public Object[][] divisionData() {
        ExcelUtil excelUtil = new ExcelUtil("src/test/java/automation/resources/Data.xlsx", "Division");
        Object[][] detailsFromExcel = excelUtil.getDataArray();
        return detailsFromExcel;
    }

    @Test(dataProvider = "division")
    public void divisionOperation(String number_1, String number_2, String expectedResult) {
        Allure.description("Multiplication operation with: [" + number_1 + " " + number_2 + "]");
        pages.calculatorPage.performCalculatorOperation("divide", number_1, number_2);
        String actualResult = pages.calculatorPage.RESULT_FIELD.getText();
        Assert.assertEquals(actualResult, BrowserUtil.roundIfNoDecimal(expectedResult));
    }


}

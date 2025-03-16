package Utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {

    private Validations() {
        super();
    }

    //@Step("Validating {condition} with message {msg}")
    public static void validateTrue(boolean condition , String msg){
        Assert.assertTrue(condition , msg);
    }

    //@Step("Validating {condition} with message {msg}")
    public static void validateFalse(boolean condition , String msg){
        Assert.assertFalse(condition , msg);
    }

    //@Step("Validating {actual} equals {expected} with message {msg}")
    public static void validateEquals(String actual , String expected , String msg){
        Assert.assertEquals(actual , expected , msg);
    }

    //@Step("Validating {actual} not equals {expected} with message {msg}")
    public static void validateNotEquals(String actual , String expected , String msg){
        Assert.assertNotEquals(actual , expected , msg);
    }

    //@Step("Validating page url is {expected}")
    public static void validatePageUrl(WebDriver driver , String expected){
        Assert.assertEquals(BrowserActions.getCurrentUrl(driver),expected);
    }

    //@Step("Validating page title is {expected}")
    public static void validatePageTitle(WebDriver driver,String expected){
        Assert.assertEquals(BrowserActions.getTitle(driver),expected);
    }
}

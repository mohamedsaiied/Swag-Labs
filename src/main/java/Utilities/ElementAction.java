package Utilities;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementAction {

    private ElementAction() {
        super();
    }

    public static WebElement findElement(WebDriver driver , By locator){
        return driver.findElement(locator);
    }

    //@Step("Entering data {data} in the field {locator}")
    public static void sendData(WebDriver driver , By locator , String data){
        Waits.waitForElementVisible(DriverFactory.getDriver(),locator);
        Scrolling.scrollDown(DriverFactory.getDriver(),locator);
        findElement(driver,locator).sendKeys(data);
        LogsUtils.info("Data entered " , data , "in the field" , locator.toString());
    }

    //@Step("Clicking on the element {locator}")
    public static void clickOnElement(WebDriver driver , By locator){
        Waits.waitForElementClickable(driver,locator);
        Scrolling.scrollDown(driver,locator);
        findElement(driver,locator).click();
        LogsUtils.info("clicked on the element" , locator.toString());
    }

    //@Step("Getting text from the element {locator}")
    public static String getText(WebDriver driver , By locator){
        Waits.waitForElementVisible(driver,locator);
        Scrolling.scrollDown(driver,locator);
        LogsUtils.info("getting text from the element" , locator.toString() , "text is" , findElement(driver,locator).getText());
        return findElement(driver,locator).getText();
    }

    public static String getTextValue(WebDriver driver , By locator){
        Waits.waitForElementVisible(driver,locator);
        Scrolling.scrollDown(driver,locator);
        LogsUtils.info("getting text from input value " , locator.toString() , "text is :" , findElement(driver,locator).getText());
        return findElement(driver,locator).getDomAttribute("value");
    }


}

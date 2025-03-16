package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private Waits() {
        super();
    }

    //TODO : WAIT UNTILL ELEMENT BE VISIBLE - PRESENT - CLICKABLE

    public static WebElement waitForElementPresent(WebDriver driver , By locator){
        LogsUtils.info("Waiting for element to be present" , locator.toString());
        return new WebDriverWait(driver , Duration.ofSeconds(5))
                .until(driver1 -> driver.findElement(locator));
    }

    public static WebElement waitForElementVisible(WebDriver driver , By locator){
        LogsUtils.info("Waiting for element to be visible" , locator.toString());
        return new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(driver1 -> {
                    WebElement element = waitForElementPresent(driver,locator);
                    return element.isDisplayed() ? element : null ;
                });
    }

    public static WebElement waitForElementClickable(WebDriver driver,By locator){
        LogsUtils.info("Waiting for element to be clickable" , locator.toString());
        return  new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(driver1 -> {
                    WebElement element = waitForElementVisible(driver,locator);
                    return element.isEnabled() ? element : null;
                });
    }



}

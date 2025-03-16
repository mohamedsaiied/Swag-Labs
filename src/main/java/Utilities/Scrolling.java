package Utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Scrolling {

    private Scrolling() {
        super();
    }

    //@Step("Scrolling down to the element {locator}")
    public static void scrollDown(WebDriver driver , By locator){
        LogsUtils.info("Scrolling down to the element" , locator.toString());
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);" ,
                        ElementAction.findElement(driver,locator));

        //webelement
    }
}

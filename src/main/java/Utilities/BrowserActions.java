package Utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        super();
    }

    //@Step("Navigating to the url {url}")
    public static void getUrl(WebDriver driver , String url){
        driver.get(url);
        LogsUtils.info("Navigated to the url" , url);
    }

    //@Step("Getting current url")
    public static String getCurrentUrl(WebDriver driver){
        LogsUtils.info("current url is " , driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    //@Step("Getting title")
    public static String getTitle(WebDriver driver){
        LogsUtils.info("title is " , driver.getTitle());
        return  driver.getTitle();
    }

    //@Step("Getting page source")
    public static void refreshPage(WebDriver driver){
        LogsUtils.info("refreshing the page");
        driver.navigate().refresh();
    }

    //@Step("Closing the browser")
    public static void closeBrowser(WebDriver driver){
        LogsUtils.info("closing the browser");
        driver.quit();
    }
}

package Driver;

import Utilities.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public DriverFactory() {
        super();
    }

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    //@Step("Create driver on : {broswerName}")
    public static WebDriver createDriver(String broswerName){
        WebDriver driver = BrowserFactory.getBrowser(broswerName);
        LogsUtils.info("Driver created on:" ,broswerName );
        setDriver(driver);
        return getDriver();
    }

    public static void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver(){
        if(driverThreadLocal.get() == null){
            LogsUtils.error("Driver is null");
        }
        return driverThreadLocal.get();
    }
}

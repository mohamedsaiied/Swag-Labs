package Utilities;

import Driver.DriverFactory;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;

import java.io.File;

public class ScreenShotUtils {
    public static String SCREENSHOT_PATH = "test-outputs/screenshots/";
    private ScreenShotUtils(){
        super();
    }

    public static void takeScreenShot(String sreenshotName){
        //code to take screenshot
        try {
            File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File(SCREENSHOT_PATH + sreenshotName + ".png");
            FileUtils.copyFile(src,dest);
            AllureUtils.attachScreenshotToAllureReport(sreenshotName);
        } catch (Exception e) {
            LogsUtils.error("unable to take screenshot");
        }
    }

    public static void takeFullScreenShot(WebDriver driver , By locator){
        try{
            Shutterbug.shootPage(driver , Capture.FULL_SCROLL)
                    .highlight(ElementAction.findElement(driver,locator))
                    .save(SCREENSHOT_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

}

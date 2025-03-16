package Utilities;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    private AllureUtils(){
        super();
    }

    //todo : attach log to allure report
    //todo : call it after finish

    public static void attachLogToAllureReport() {
        try {
            File fileLog = FileUtils.getLatestFile(LogsUtils.LOGS_PATH);
            if(fileLog ==null || !fileLog.exists  ()){
                LogsUtils.warn("Log file does not exist" + LogsUtils.LOGS_PATH);
                return;
            }
            Allure.addAttachment("Logs.log" , Files.readString(Path.of(fileLog.getPath())));
            LogsUtils.info("Log file attached to allure report");
        } catch (Exception e) {
            LogsUtils.error("Error attaching log file to allure report" + e.getMessage());
        }
    }

    public static void attachScreenshotToAllureReport(String screenshotName) {
        try {
            File fileScreenshot = FileUtils.getLatestFile(ScreenShotUtils.SCREENSHOT_PATH);
            if(fileScreenshot == null || !fileScreenshot.exists()){
                LogsUtils.warn("Screenshot file does not exist" + screenshotName);
                return;
            }
            Allure.addAttachment(screenshotName , new FileInputStream(fileScreenshot));
            LogsUtils.info("Screenshot file attached to allure report");
        } catch (Exception e) {
            LogsUtils.error("Error attaching screenshot file to allure report" + e.getMessage());
        }
    }
}


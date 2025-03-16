package Driver;

import Utilities.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;
import java.util.Objects;

public class BrowserFactory {

    public static WebDriver getBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = getChromeOptions();
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            default:
                EdgeOptions edgeOptions = getEdgeOptions();
                return new EdgeDriver(edgeOptions);
        }
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-extentions");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--remote-allow-origins=*");

        //if the exectiontype equal anything but not equal local run headless
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            chromeOptions.addArguments("--headless");
        }

        Map<String, Object> prefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enable", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return chromeOptions;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-extentions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--remote-allow-origins=*");

        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            edgeOptions.addArguments("--headless");
        }

        Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enable", false);
        edgeOptions.setExperimentalOption("prefs", edgePrefs);
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return edgeOptions;
    }


    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-extentions");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--remote-allow-origins=*");

        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local")){
            firefoxOptions.addArguments("--headless");
        }

        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }
}
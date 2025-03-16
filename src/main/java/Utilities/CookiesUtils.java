package Utilities;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CookiesUtils {

    public static Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver , Set<Cookie> cookies){
        for(Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }
}

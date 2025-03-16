import Driver.DriverFactory;
import Listener.TestNG_Listener;
import Pages.LandingPage;
import Pages.LoginPage;
import Utilities.BrowserActions;
import Utilities.CookiesUtils;
import Utilities.JsonUtils;
import Utilities.PropertiesUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Set;


@Listeners(TestNG_Listener.class)
public class LandingTest {

    //todo: variables
    private WebDriver driver;
    JsonUtils testData;
    private Set<Cookie> cookies;

    //todo: tests
    @Test
    public void addSpecificProductToCart(){
        new LandingPage(driver)
                .addSpecificProduct(testData.getJsonData("Product-Details.item2.name"))
                .assertProductAddedToCart(testData.getJsonData("Product-Details.item2.name"))
                .clickOnCartIcon();
    }

    @Test
    public void addAllProductsToCart(){
        new LandingPage(driver)
                .addAllProductToCart()
                .assertNumOfAllProductsInCart();
    }

    @Test
    public void addRandomProduct(){
      new LandingPage(driver)
                .addRandomProduct(3,6)
                .assertNumOfSelectedProductsInCart();
    }


    //todo: configuration
    @BeforeClass
    public void beforeClass(){
        testData = new JsonUtils("loginData");
        driver = DriverFactory.createDriver(PropertiesUtils.getPropertyValue("browser"));
        new LoginPage(driver).navigateToLoginPage(driver);
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("Login-credentials.username"))
                .enterPassword(testData.getJsonData("Login-credentials.password"))
                .clickOnBtn();
        cookies = CookiesUtils.getAllCookies(driver);
        tearDown();
    }

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.createDriver(PropertiesUtils.getPropertyValue("browser"));
        new LoginPage(driver).navigateToLoginPage(driver);
        CookiesUtils.restoreSession(driver,cookies);
        new LandingPage(driver).navigateToHomePage(driver);
    }
    @AfterMethod
    public void tearDown(){
        BrowserActions.closeBrowser(driver);
    }
}

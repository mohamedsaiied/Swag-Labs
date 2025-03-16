import Driver.DriverFactory;
import Listener.TestNG_Listener;
import Pages.LoginPage;
import Utilities.BrowserActions;
import Utilities.CustomSoftAssert;
import Utilities.JsonUtils;
import Utilities.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNG_Listener.class)
public class OverviewTest {

    //variables
    private WebDriver driver;
    JsonUtils testData;

    //tests
    @Test
    public void checkoutStepTwoTC(){
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("Login-credentials.username"))
                .enterPassword(testData.getJsonData("Login-credentials.password"))
                .clickOnBtn()
                .addSpecificProduct(testData.getJsonData("Product-Details.item1.name"))
                .clickOnCartIcon()
                .clickOnCheckoutBtn()
                .filInformationForm
                        (testData.getJsonData("Form-Data.f-name") ,
                                testData.getJsonData("Form-Data.l-name"),
                                testData.getJsonData("Form-Data.zip"))
                .clickOnContinueBtn()
                .assertOverviewUrl(PropertiesUtils.getPropertyValue("OVERVIEW_URL"))
                .assertComparingPrices();
    }

    //configurations
    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.createDriver(PropertiesUtils.getPropertyValue("browser"));
        new LoginPage(driver).navigateToLoginPage(driver);
        testData = new JsonUtils("loginData");
    }
    @AfterMethod
    public void tearDown(){
        BrowserActions.closeBrowser(driver);
        CustomSoftAssert.customSoftAssertAll();
    }
}

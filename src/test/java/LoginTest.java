import Driver.DriverFactory;
import Listener.TestNG_Listener;
import Pages.LoginPage;
import Utilities.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(TestNG_Listener.class)
public class LoginTest {

    //TODO: VARIABLES
    private WebDriver driver;
    JsonUtils testData;

    //TODO: TESTS
    @Test
    public void successfullLogin(){
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("Login-credentials.username"))
                .enterPassword(testData.getJsonData("Login-credentials.password"))
                .clickOnBtn();
        new LoginPage(driver).softAssertTC();
    }

    @Test
    public void unsuccessfullLogin(){
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("Login-credentials.username"))
                .enterPassword("")
                .clickOnBtn();
        new LoginPage(driver).validateInvalidCredentials();
    }

    @Test
    public void blankField(){
        new LoginPage(driver)
                .enterUsername("")
                .enterPassword("")
                .clickOnBtn();
        new LoginPage(driver).validateBlankField();
    }

    //TODO: CONFIGURATIONS
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
/*        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : PropertiesUtils.getPropertyValue("browser");
        LogsUtils.info(System.getProperty("browser"));*/
        driver = DriverFactory.createDriver(PropertiesUtils.getPropertyValue("browser"));
        new LoginPage(driver).navigateToLoginPage(driver);
        testData = new JsonUtils("loginData");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        BrowserActions.closeBrowser(driver);
        CustomSoftAssert.customSoftAssertAll();
    }
}
 
package Pages;

import Utilities.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //TODO: DRIVER
    private final WebDriver driver;

    //TODO: CONSTRUCTOR
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //TODO: LOCATORS
    private final By USERNAME = By.id("user-name");
    private final By PASSWORD = By.id("password");
    private final By LOGINBTN = By.id("login-button");
    private final By ERRORMSG = By.cssSelector("[data-test='error']");

    //TODO: NAVIGATE TO LOGIN PAGE
    @Step("Navigate to login page")
    public void navigateToLoginPage(WebDriver driver){
        BrowserActions.getUrl(driver, PropertiesUtils.getPropertyValue("LOGIN_URL"));
    }

    //TODO: ACTIONS
    @Step("Enter username {username}")
    public LoginPage enterUsername(String username){
        ElementAction.sendData(driver,USERNAME,username);
        return this;
    }

    @Step("Enter password {password}")
    public LoginPage enterPassword(String password){
        ElementAction.sendData(driver,PASSWORD,password);
        return this;
    }

    @Step("Click on login button")
    public LandingPage clickOnBtn(){
        ElementAction.clickOnElement(driver,LOGINBTN);
        return new LandingPage(driver);
    }

    @Step("Get error message")
    public String getErrorMsg(){
        return ElementAction.getText(driver,ERRORMSG);
    }

    //TODO: VALIDATIONS
    @Step("Validate login successful")
    public LoginPage validateSuccessfullLogin(){
        CustomSoftAssert.softAssert.assertEquals(BrowserActions.getCurrentUrl(driver),PropertiesUtils.getPropertyValue("LANDING_URL"),"");
        return this;
    }
    @Step("Validate title")
    public LoginPage validateTitle(){
        CustomSoftAssert.softAssert.assertEquals(BrowserActions.getTitle(driver),PropertiesUtils.getPropertyValue("SITE_TITILE"),"");
        return this;
    }
    @Step("Validate login successful and title")
    public LoginPage softAssertTC(){
        validateSuccessfullLogin().validateTitle();
        return this;
    }
    @Step("Validate blank field")
    public LoginPage validateBlankField(){
        Validations.validateEquals(getErrorMsg(),PropertiesUtils.getPropertyValue("MISSING_USERNAME"),"");
        return this;
    }
    @Step("Validate invalid credentials")
    public LoginPage validateInvalidCredentials(){
       Validations.validateEquals(getErrorMsg(),PropertiesUtils.getPropertyValue("MISSING_PASSWORD"),"");
        return this;
    }
    @Step("Validate invalid password")
    public LoginPage validateInvalidPassword(){
        Validations.validateEquals(getErrorMsg(),PropertiesUtils.getPropertyValue("INVALID_CREDENTIALS"),"");
        return this;
    }











}

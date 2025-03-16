package Pages;

import Driver.DriverFactory;
import Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    //variables
    private WebDriver driver;

    //constractur
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");


    //navigate
    public CheckoutPage navigateToCheckoutPage(WebDriver driver){
        BrowserActions.getUrl(driver, PropertiesUtils.getPropertyValue("CHECKOUT_URL"));
        return this;
    }

    //actions
    public CheckoutPage filInformationForm(String firstName , String lastName , String postalCode){
        ElementAction.sendData(driver,this.firstName,firstName);
        ElementAction.sendData(driver,this.lastName,lastName);
        ElementAction.sendData(driver,this.postalCode,postalCode);
        return this;
    }

    public OverviewPage clickOnContinueBtn(){
        ElementAction.clickOnElement(driver,continueBtn);
        return new OverviewPage(driver);
    }

    //validations
    public CheckoutPage assertInformationForm(String firstName , String lastName , String postalCode){
        CustomSoftAssert.softAssert.assertEquals(ElementAction.getTextValue(driver,this.firstName) , firstName );
        CustomSoftAssert.softAssert.assertEquals(ElementAction.getTextValue(driver,this.lastName) , lastName );
        CustomSoftAssert.softAssert.assertEquals(ElementAction.getTextValue(driver,this.postalCode) , postalCode );
        return this;
    }

}

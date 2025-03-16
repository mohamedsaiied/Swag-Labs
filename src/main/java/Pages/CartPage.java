package Pages;

import Utilities.*;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    //todo : variables
    private WebDriver driver;
    private String prodName;
    private String prodPrice;

    //todo : constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //todo : locators
    private final By checkoutBtn = By.id("checkout");

    //todo : navigation
    public CartPage navigateToCartPage(WebDriver driver){
        BrowserActions.getUrl(driver,PropertiesUtils.getPropertyValue("CART_URL"));
        return this;
    }

    //todo : actions

    public CartPage getProductDetails(String index){
        By productName =By.xpath("(//div[@class='inventory_item_name'])["+index+"]");
        By productPrice = By.xpath("(//div[@class='inventory_item_price'])["+index+"]");
        this.prodName = ElementAction.getText(driver,productName);
        this.prodPrice = ElementAction.getText(driver,productPrice);
        return this;
    }
/*    public CartPage getProductName(String index){
         By productName =By.xpath("(//div[@class='inventory_item_name'])["+index+"]");
         this.prodName = ElementAction.getText(driver,productName);
         return this;
    }

    public CartPage getProductPrice(String index){
        By productPrice = By.xpath("(//div[@class='inventory_item_price'])["+index+"]");
         this.prodPrice = ElementAction.getText(driver,productPrice);
         return this;
    }*/

    public CheckoutPage clickOnCheckoutBtn(){
        ElementAction.clickOnElement(driver, checkoutBtn);
        return new CheckoutPage(driver);
    }

    //todo : validations
    public CartPage assertProductDetails(String expectedName ,String expectedPrice){
        CustomSoftAssert.softAssert.assertEquals(prodName,expectedName ,"Product name mismatch");
        CustomSoftAssert.softAssert.assertEquals(prodPrice,expectedPrice ,"Product price mismatch");
        return this;
    }

}

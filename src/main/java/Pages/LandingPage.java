package Pages;

import Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.lang.ref.PhantomReference;
import java.util.List;
import java.util.Set;

public class LandingPage {

    //todo : variables
    private WebDriver driver;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;

    //todo : constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    //todo : locators
    private final By numOfProductInCartIcon = By.className("shopping_cart_link");
    private final By cartIcon =By.className("shopping_cart_link");
    private final By addAllProductToCartBtn = By.xpath("//button[@class]");
    private final By numOfSelectedProduct = By.xpath("//button[.='Remove']");

    //todo : navigation
    public LandingPage navigateToHomePage(WebDriver driver){
        BrowserActions.getUrl(driver, PropertiesUtils.getPropertyValue("LANDING_URL"));
        return this;
    }

    //todo : actions

    //TODO : ADD ALL PRODUCT
    public LandingPage addAllProductToCart(){
        allProducts = driver.findElements(addAllProductToCartBtn);
        LogsUtils.info("number of all products : " + allProducts.size());
        for (int i=1;i<=allProducts.size();i++){
            By addAllProductToCart = By.xpath("(//button[@class])["+i+"]");
            ElementAction.clickOnElement(driver,addAllProductToCart);
        }
        return this;
    }

    //TODO : ADD SPECIFIC PRODUCT
    public LandingPage addSpecificProduct(String productName) {
        //relative locator and dynamic i should but dynamic locator here
        LogsUtils.info("Adding"+ productName+ "to cart");
        By addToCartBtn = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+productName+"']"));
        ElementAction.clickOnElement(driver, addToCartBtn);
        return this;
    }

    //TODO: ADD RANDOM PRODUCT
    public LandingPage addRandomProduct(int needed , int total){
        Set<Integer> randomNumbers = GenerateNums.generateUniqueNumbers(needed,total);
        for(int random : randomNumbers) {
            By addToCartBtnForAllProduct = By.xpath("(//button[@class])["+random+"]");
            ElementAction.clickOnElement(driver,addToCartBtnForAllProduct);
        }
        return this;
    }

    //TODO: GET NUMBERS OF CART ICON PRODUCT
    public String getNumOfProductINCart(){
        try {
            LogsUtils.info("number of products in cart : " + ElementAction.getText(driver,numOfProductInCartIcon));
            return ElementAction.getText(driver,numOfProductInCartIcon);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    //TODO: GET NUMBERS OF SELECTED PRODUCTS
    public String getNumOfSelectedProduct(){
        try {
            selectedProducts = driver.findElements(numOfSelectedProduct);
            LogsUtils.info("selected products : " + selectedProducts.size());
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    //TODO : COMPARE BETWEEN NUMBER OF SELECTED PRODUCTS AND NUMBER OF CART ICON
    public boolean comparingNumOfSelectedProductsAndCartIcon(){
        return getNumOfSelectedProduct().equals(getNumOfProductINCart());
    }




    //TODO : CLICK ON CART ICON BUTTON
    public CartPage clickOnCartIcon(){
        ElementAction.clickOnElement(driver,cartIcon);
        return new CartPage(driver);
    }

    //todo : validations
    public LandingPage assertProductAddedToCart(String productName){
        By addToCartBtn = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='"+productName+"']"));
        String actualValue = ElementAction.getText(driver,addToCartBtn);
        Validations.validateEquals(actualValue,"Remove","Product not added to cart");
        LogsUtils.info(productName + "added to cart successfully");
        return this;
    }

    public LandingPage assertNumOfAllProductsInCart(){
        Validations.validateEquals(getNumOfProductINCart() , String.valueOf(allProducts.size()) , "");
        return this;
    }

    public LandingPage assertNumOfSelectedProductsInCart(){
        Validations.validateTrue(comparingNumOfSelectedProductsAndCartIcon() , "");
        return this;
    }


}

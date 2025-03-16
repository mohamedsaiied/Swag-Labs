package Pages;

import Utilities.ElementAction;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishPage {

    //variables
    private final WebDriver driver;


    //constructor
    public FinishPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By finishMsg = By.className("complete-header");
    //navigatie

    //actions
    public String getFinsihText(){
        return ElementAction.getText(driver,finishMsg);
    }

    //validations
    public FinishPage assertFinsihText(String expectedText){
        Validations.validateEquals(getFinsihText() ,expectedText , "" );
        return this;
    }


}

package Pages;

import Driver.DriverFactory;
import Utilities.BrowserActions;
import Utilities.ElementAction;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

    /// variables
    private final WebDriver driver;

    /// constructor
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    /// locators
    private final By itemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishBtn = By.xpath("//button[contains(@class,'btn_action')]");
    //button[contains(@class, 'btn_action')]
    /// navigate

    /// actions

    public Float getItemTotal(){
        return Float.parseFloat(ElementAction.getText(driver,itemTotal).replace("Item total: $",""));
    }
    public Float getTax(){
        return Float.parseFloat(ElementAction.getText(driver,tax).replace("Tax: $" , ""));
    }
    public Float getTotal(){
        return Float.parseFloat(ElementAction.getText(driver,total).replace("Total: $",""));
    }

    public String calcPrices(){
        return String.valueOf(getItemTotal()+getTax());
    }

    public boolean checkTotal(){
        return calcPrices().equals(String.valueOf(getTotal()));
    }

    public FinishPage clickOnFinishBtn(){
        ElementAction.clickOnElement(driver,finishBtn);
        return new FinishPage(driver);
    }

    /// validations

    public OverviewPage assertComparingPrices(){
        Validations.validateTrue(checkTotal(),"");
        return this;
    }

    public OverviewPage assertOverviewUrl(String expectedUrl) {
        Validations.validateEquals(BrowserActions.getCurrentUrl(DriverFactory.getDriver()) , expectedUrl , "");
        return this;
    }
}

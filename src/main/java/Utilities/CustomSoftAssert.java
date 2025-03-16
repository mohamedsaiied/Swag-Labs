package Utilities;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssert extends SoftAssert {

    public static CustomSoftAssert softAssert = new CustomSoftAssert();

    public static void customSoftAssertAll(){
        try{
            softAssert.assertAll("custom soft assertion");
        } catch (Exception e){
            System.out.println("custom soft assertion failed");
        }
    }
}

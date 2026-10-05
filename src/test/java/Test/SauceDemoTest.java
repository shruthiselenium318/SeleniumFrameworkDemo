package Test;

import DataProviders.*;

import Pages.*;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.fileUtil;

import java.util.Map;
//@Listeners({AllureTestNg.class})
//public class SauceDemoTest extends BaseTest{
    //protected WebDriver driver;
    /*fileUtil fileutil=new fileUtil("environment.properties");


    @Test(dataProvider = "SauceDemo",dataProviderClass = SauceDemo_DataProvider.class)
    public void AddtoCartTest(Map<String,String> data)
    {
        gotoURL(fileutil.GetProperty("app_url").toString());
        Allure.step("User navigated to saucedemo page");
        LoginPage lp=new LoginPage(driver);
        lp.LogintoSauceDemo(data.get("USERNAME"),data.get("PASSWORD"));
        HomePage hp=new HomePage(driver);
        //Assert.assertNotEquals(hp.verifyProductTitle(),1);
        hp.AddtoCart(data.get("PRODUCT_NAME"));
        hp.ClickCart();
        YourCartPage ycp=new YourCartPage(driver);
        ycp.clickButton();
        CheckoutYourInfoPage cyip=new CheckoutYourInfoPage(driver);
        cyip.EnterCustomerInfo(data.get("FIRSTNAME"),data.get("LASTNAME"),data.get("PINCODE"));
        cyip.clickContinue();
        CheckoutOverViewPage cop=new CheckoutOverViewPage(driver);
        cop.clickFinish();

    }

    @Test(dataProvider = "LoginSauceDemo",dataProviderClass = LoginSauceDemo_DataProvider.class)
    public void LoginTest(Map<String,String> data)
    {
        gotoURL(fileutil.GetProperty("app_url").toString());
        LoginPage lp=new LoginPage(driver);
        lp.LogintoSauceDemo(data.get("USERNAME"),data.get("PASSWORD"));
        HomePage hp=new HomePage(driver);
        //Assert.assertNotEquals(hp.verifyProductTitle(),1);
    }*/
//}

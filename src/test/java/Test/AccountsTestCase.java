package Test;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.Accounts_DataProvider;
import ModulesBuilder.CreateAccountsBuilder;
import Pages.AccountsPage;
import Pages.AccountsViewPage;
import Pages.EditAccountPage;
import Pages.HomePage;
import Pages.IndividualAccountPage;
import Pages.LoginPage;
import Pages.AccountsViewPage.AccountOption;
import Pages.HomePage.CreateOptions;
import Pages.HomePage.Options;
import utilities.Assert_Report_LogUtil;
import utilities.fileUtil;



public class AccountsTestCase extends BaseTest{
    @Test(priority=1,dataProvider = "Accounts",dataProviderClass = Accounts_DataProvider.class)
    public void CreateAccount(Map<String,String> data)
    {
       String url=fileutil.GetProperty("app_url");
       
       gotoURL(url);
       Assert_Report_LogUtil.logAllurePassStep("User logged into:"+url);
       LoginPage lp=new LoginPage();
       HomePage homepage=lp.Login();
       homepage.ClickOptions(Options.Accounts);
       AccountsPage accountspage=homepage.ClickCreateOptions(CreateOptions.CreateAccount, AccountsPage.class);
       Assert_Report_LogUtil.AssertBoolean(getDriver(), accountspage!=null, "Navigated to Accounts Dashbaord page", "Unable to navigate to Accounts Dashbaord page");
       
       CreateAccountsBuilder cab=new CreateAccountsBuilder();
       cab.setName(data.get("name"));
       cab.setEmail(data.get("email1"));
       cab.setBillingCountry(data.get("country"));
       cab.setType(data.get("type"));
       cab.setIndustry(data.get("industry"));
       cab.setAssignedUser(data.get("assigned_user"));
       HashMap<String,String> values=accountspage.CreateAccounts(cab);
       
       //Validate if the values are entered successfully
       Assert_Report_LogUtil.AssertBoolean(getDriver(),data.get("name").equals(values.get("name")), "Name is entered", "Unable to enter name");
       Assert_Report_LogUtil.AssertBoolean(getDriver(),data.get("email1").equals(values.get("email")), "Email is entered", "Unable to enter email");
       Assert_Report_LogUtil.AssertBoolean(getDriver(),data.get("country").equals(values.get("billingCountry")), "Country is entered", "Unable to enter Country");
       Assert_Report_LogUtil.AssertBoolean(getDriver(),data.get("type").equals(values.get("type")), "type is entered", "Unable to enter type");
       Assert_Report_LogUtil.AssertBoolean(getDriver(),data.get("industry").equals(values.get("industry")), "industry is entered", "Unable to enter industry");
       Assert_Report_LogUtil.AssertBoolean(getDriver(),data.get("assigned_user").equals(values.get("AssignedUser")), "Assigned User is entered", "Unable to enter Assigned User");
       
       IndividualAccountPage iap=accountspage.ClickSaveButton();
       Assert_Report_LogUtil.AssertBoolean(getDriver(),iap!=null, "Clicked Save button", "Unable to click Save button");
       AccountsViewPage avp=iap.NavigateToAccountsDashbaord(data.get("name"));

        //Validate if the all the created details are present in Account view page
        
        HashMap<String,String> accountDetails=new HashMap<>();
        avp.searchAccount(data.get("name"));
        accountDetails=avp.extractAccountDetails();
        
        Assert_Report_LogUtil.AssertBoolean(getDriver(), values.entrySet().containsAll(accountDetails.entrySet()),"Account is created with the entered details", "Unable to create account with the entered details");
    }

    @Test(priority=2,dataProvider = "Accounts",dataProviderClass = Accounts_DataProvider.class)
    public void UpdateAccount(Map<String,String> data)
    {
       String url=fileutil.GetProperty("app_url");
       gotoURL(url);
       Assert_Report_LogUtil.logAllurePassStep("User logged into:"+url);
       LoginPage lp=new LoginPage();
       HomePage homepage=lp.Login();
       
       AccountsViewPage avp=homepage.ClickOptions(Options.Accounts);
       Assert_Report_LogUtil.AssertBoolean(getDriver(), avp!=null, "Navigated to Accounts Dashbaord page", "Unable to navigate to Accounts Dashbaord page");
       avp.searchAccount(data.get("name"));
       EditAccountPage eap=avp.selectOptionforAccount(AccountOption.Edit);
       Assert_Report_LogUtil.AssertBoolean(getDriver(), eap!=null, "Navigated to edit Accoutns page", "Unable to navigate to Edit Accounts page");
       eap.clickFullForm();
       CreateAccountsBuilder cab=new CreateAccountsBuilder();
       cab.setIndustry(data.get("industry"));
       cab.setType(data.get("type"));
       eap.updateAccountFields(cab);
       avp=eap.SaveUpdate();
       Assert_Report_LogUtil.logAllurePassStep("Updated Account Details");
         //Verify if the values are updated
       Map<String,String> accountDetails=avp.extractAccountDetails();
       Assert_Report_LogUtil.AssertBoolean(getDriver(), accountDetails.get("industry").equals(data.get("industry")), "Updated industry is reflected in Accounts Dashbaord page", "Updated industry is not reflected in Accounts Dashbaord page");
       Assert_Report_LogUtil.AssertBoolean(getDriver(), accountDetails.get("type").equals(data.get("type")), "Updated type is reflected in Accounts Dashbaord page", "Updated industry is not reflected in Accounts Dashbaord page");
    }   
    
    @Test(priority=3,dataProvider = "Accounts",dataProviderClass = Accounts_DataProvider.class)
    public void DeleteAccount(Map<String,String> data)
    {
       String url=fileutil.GetProperty("app_url");
       gotoURL(url);
       Assert_Report_LogUtil.logAllurePassStep("User logged into:"+url);
       LoginPage lp=new LoginPage();
       HomePage homepage=lp.Login();
       AccountsViewPage avp=homepage.ClickOptions(Options.Accounts);
       
       Assert_Report_LogUtil.AssertBoolean(getDriver(), avp!=null, "Navigated to Accounts Dashbaord page", "Unable to navigate to Accounts Dashbaord page");
       avp.searchAccount(data.get("name"));
       avp.selectOptionforAccount(AccountOption.Remove);
       avp.deleteAccount();
       Assert_Report_LogUtil.logAllurePassStep("Option selected to delete account");
       Assert_Report_LogUtil.AssertBoolean(getDriver(),avp.getNoDataMessage().equals("No Data"), "No data message is displayed, since the account is deleted", "No data message is not displayed");
    }
    

}

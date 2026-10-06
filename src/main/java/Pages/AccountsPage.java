package Pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import BaseClass.BaseClass;
import ModulesBuilder.CreateAccountsBuilder;

public class AccountsPage extends BaseClass{
    //defining xpath for AccountsPage
    private By name=By.xpath("//input[@data-name='name']");
    private By website=By.xpath("//input[@data-name='website']");
    private By email=By.xpath("//input[@class='form-control email-address']");
    private By billingcountry=By.xpath("//input[@data-name='billingAddressCountry']");
    //private By type=By.xpath("//select[@data-name='type']");
    private By typeDropDownBox=By.xpath("//div[@data-name='type']/div[@class='selectize-control form-control main-element single plugin-espo_select']");
    private By typeDropDownValue=By.xpath("//div[@data-name='type']/div[@class='selectize-control form-control main-element single plugin-espo_select']/div/div[contains(@class,'item')]");
    private By industryDropDownBox=By.xpath("//div[@data-name='industry']/div[@class='selectize-control form-control main-element single plugin-espo_select']");
    private By industryDropDownValue=By.xpath("//div[@data-name='industry']/div[@class='selectize-control form-control main-element single plugin-espo_select']//div[@class='item']");
    ////div[@data-name='type']//div[@class='selectize-dropdown-content']/div[@data-value='Investor']
    private By industry=By.xpath("//select[@data-name='industry']");
    private By AssignedUserSelectIcon=By.xpath("//div[@data-name='assignedUser']//button[@title='Select']");
    private By SelectAlluserbutton=By.xpath("//button[@title='Filter']");
    private By SelectAllValue=By.xpath("//a/div[text()='All']");
    private By saveButton=By.xpath("//button[text()='Save']");
    private By assingedUserTextbox=By.xpath("//input[@data-name='assignedUserName']");

    private By AssignedUserNameSelect(String username)
    {
        return By.xpath("//a[text()='"+username+"']");
    }

    private By SelectTypeXapth(String type)
    {
        return By.xpath("//div[@data-name='type']//div[@class='selectize-dropdown-content']/div[@data-value='"+type+"']");
    }
    
    private By SelectIndustryXapth(String type)
    {
        return By.xpath("//div[@data-name='industry']//div[@class='selectize-dropdown-content']/div[@data-value='"+type+"']");
    }

    public HashMap<String,String> CreateAccounts(CreateAccountsBuilder cab)
    {
        HashMap<String,String> captureValues=new HashMap<>();
        
        //wait for the visibility of the element name
        waitUntilElementVisible(name);
        //Entering all values in Website
        if(cab.getName()!=null)
        {
            enterText(name, cab.getName());
            captureValues.put("name",getValue(name));
        }

        if(cab.getWebsite()!=null)
        {
            waitUntilElementVisible(website);
            enterText(website, cab.getWebsite());
            captureValues.put("website",getValue(website));
        }

        if(cab.getEmail()!=null)
        {
            waitUntilElementVisible(email);
            enterText(email, cab.getEmail());
            captureValues.put("email",getValue(email));
        }

        if(cab.getAssignedUser()!=null)
        {
            waitUntilElementVisible(AssignedUserSelectIcon);
            click(AssignedUserSelectIcon);
            
            waitUntilElementVisible(SelectAlluserbutton);
            click(SelectAlluserbutton);
            click(SelectAllValue);
            waitUntilElementVisible(AssignedUserNameSelect(cab.getAssignedUser()));
            click(AssignedUserNameSelect(cab.getAssignedUser()));
     
            captureValues.put("AssignedUser",getValue(assingedUserTextbox));
        }

        if(cab.getBillingCountry()!=null)
        {
            ScrollTillElementVisible(billingcountry);
            waitUntilElementVisible(billingcountry);
            enterText(billingcountry, cab.getBillingCountry());
            pressTab(billingcountry);
            System.out.println(getValue(billingcountry));
            captureValues.put("billingCountry",getValue(billingcountry));
        }

        if(cab.getType()!=null)
        {
            ScrollTillElementVisible(typeDropDownBox);
            waitUntilElementVisible(typeDropDownBox);
            click(typeDropDownBox);
            click(SelectTypeXapth(cab.getType()));
            captureValues.put("type",getTextValue(typeDropDownValue));  
        }

        if(cab.getIndustry()!=null)
        {
            ScrollTillElementVisible(industryDropDownBox);
            click(industryDropDownBox);
            click(SelectIndustryXapth(cab.getIndustry()));
            captureValues.put("industry",getTextValue(industryDropDownValue));  
            
        }

        
        return captureValues;
    }

    public IndividualAccountPage ClickSaveButton()
    {
        click(saveButton);
        return new IndividualAccountPage();
    }


}

package Pages;

import org.openqa.selenium.By;

import BaseClass.BaseClass;
import ModulesBuilder.CreateAccountsBuilder;

public class EditAccountPage extends BaseClass{
    
    private By name=By.xpath("//input[@data-name='name']");
    private By website=By.xpath("//input[@data-name='website']");
    private By email=By.xpath("//input[@class='form-control email-address']");
    private By billingcountry=By.xpath("//input[@data-name='billingAddressCountry']");
    private By typeDropDownBox=By.xpath("//div[@data-name='type']/div[@class='selectize-control form-control main-element single plugin-espo_select']");
    private By industryDropDownBox=By.xpath("//div[@data-name='industry']/div[@class='selectize-control form-control main-element single plugin-espo_select']");
    private By AssignedUserSelectIcon=By.xpath("//div[@data-name='assignedUser']//button[@title='Select']");
    private By SelectAlluserbutton=By.xpath("//button[@title='Filter']");
    private By SelectAllValue=By.xpath("//a/div[text()='All']");
    private By saveButton=By.xpath("//button[text()='Save']");
    private By fullFormLink=By.xpath("//button[text()='Full Form']");

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

    public void clickFullForm()
    {
        waitUntilElementVisible(fullFormLink);
        click(fullFormLink);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void updateAccountFields(CreateAccountsBuilder cab)
    {

        if(cab.getName()!=null)
        {
            enterText(name, cab.getName());
            
        }

        if(cab.getWebsite()!=null)
        {
            waitUntilElementVisible(website);
            enterText(website, cab.getWebsite());
            
        }

        if(cab.getEmail()!=null)
        {
            waitUntilElementVisible(email);
            enterText(email, cab.getEmail());
            
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
     
            
        }

        if(cab.getBillingCountry()!=null)
        {
            ScrollTillElementVisible(billingcountry);
            waitUntilElementVisible(billingcountry);
            enterText(billingcountry, cab.getBillingCountry());
            pressTab(billingcountry);
            System.out.println(getValue(billingcountry));
            
        }

        if(cab.getType()!=null)
        {
            ScrollTillElementVisible(typeDropDownBox);
            click(typeDropDownBox);
            click(SelectTypeXapth(cab.getType()));
            
        }

        if(cab.getIndustry()!=null)
        {
            ScrollTillElementVisible(industryDropDownBox);
            click(industryDropDownBox);
            click(SelectIndustryXapth(cab.getIndustry()));
            
            
        }
    }

    public AccountsViewPage SaveUpdate()
    {
        click(saveButton);
        return new AccountsViewPage();
    }
}

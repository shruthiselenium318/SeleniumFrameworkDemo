package Pages;

import java.util.HashMap;

import org.openqa.selenium.By;

import BaseClass.BaseClass;

public class AccountsViewPage extends BaseClass{

    public enum AccountOption
    {
        View,
        Edit,
        Remove    
    }

    private By searchTextbox=By.xpath("//input[@data-name='textFilter']");
    private By searchButton=By.xpath("//button[@title='Search']");
    private By name=By.xpath("(//tr/td[@data-name='name']/a)[1]");
    private By industry=By.xpath("(//tr/td[@data-name='industry']/span)[1]");
    private By type=By.xpath("(//tr/td[@data-name='type']/span)[1]");
    private By country=By.xpath("(//tr/td[@data-name='billingAddressCountry']/span)[1]");
    private By accountsDropdown=By.xpath("//tbody//button[@data-toggle='dropdown']");
    private By confirmDeleteButton=By.xpath("//button[@data-name='confirm']");
    private By noDataMessage=By.xpath("//div[text()='No Data']");


    private By getSelectOptionForAccount(AccountOption accountOption)
    {
        return By.xpath("//a[@data-action='quick"+accountOption+"']");
    }

    public String getNoDataMessage()
    {
        waitUntilElementVisible(noDataMessage);
        return getTextValue(noDataMessage);
    }

    public <T> T selectOptionforAccount(AccountOption accountoption)
    {
        waitUntilElementVisible(accountsDropdown);
        click(accountsDropdown);
        try{
            Thread.sleep(3000);            
        }catch(Exception e)
        {

        }
        
        if(accountoption==AccountOption.Edit)
        {
            waitUntilElementVisible(getSelectOptionForAccount(accountoption));
            click(getSelectOptionForAccount(accountoption));
            return (T) new EditAccountPage();
        }
        else if(accountoption==AccountOption.Remove)
        {
            waitUntilElementVisible(getSelectOptionForAccount(accountoption));
            click(getSelectOptionForAccount(accountoption));
            return (T) new AccountsViewPage();
        }
        return null;
        
    }

    public void searchAccount(String searchCriteria)
    {
        waitUntilElementVisible(searchTextbox);
        //search for the account by name
        enterText(searchTextbox, searchCriteria);
        click(searchButton);
    }

    public void deleteAccount()
    {
        waitUntilElementVisible(confirmDeleteButton);
        click(confirmDeleteButton);
    }
    
    public HashMap<String,String> extractAccountDetails()
    {
        HashMap<String,String> accountDetails=new HashMap<>();
        waitUntilElementVisible(name);
        //extract the values from the table
        accountDetails.put("name",getTextValue(name));
        accountDetails.put("industry",getTextValue(industry));
        accountDetails.put("type",getTextValue(type));
        accountDetails.put("billingCountry",getTextValue(country));
        return accountDetails;
    }

    

}
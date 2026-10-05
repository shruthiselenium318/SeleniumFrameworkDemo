package Pages;

import BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.reporters.jq.BasePanel;
import utilities.Assert_Report_LogUtil;

public class HomePage extends BaseClass {

    public enum Options
    {
        Accounts,
        Contacts,
        Leads
    }

    public enum CreateOptions
    {
        CreateAccount("Create Account"),
        CreateContacts("Create Contact"),
        CreateLeads("Create Lead");

        private final String createoption;
        CreateOptions(String  createoption)
        {
            this.createoption=createoption;
        }

        public String getcreateoption()
        {
            return this.createoption;
        }


    }
    //WebDriver driver;
    By CreateAccountlink=By.xpath("//a/span[text()='Create Account']");

    public <T> T ClickOptions(Options options) 
    {   
        waitUntilElementClickable(By.xpath("//a[span[text()='"+options+"']]"));
        click(By.xpath("//a[span[text()='"+options+"']]"));
        if(options==Options.Accounts)
        {
            return (T) new AccountsViewPage(); 
        }
        else if(options==Options.Contacts)
        {
            return (T) new ContactsViewPage(); 
        }
        else if(options==Options.Leads)
        {
            return (T) new LeadsViewPage();
        }
        return null;
                
        
    }

    public <T> T ClickCreateOptions(CreateOptions createoptions, Class<T> pageclass)
    {
        waitUntilElementClickable(By.xpath("//a[span[text()='"+createoptions.getcreateoption()+"']]"));
        click(By.xpath("//a[span[text()='"+createoptions.getcreateoption()+"']]"));
        try{
            return pageclass.getDeclaredConstructor().newInstance(null);
        }catch(Exception e)
        {
            throw new RuntimeException("Unable to create page object: "+e.getMessage());
        }
    }


    
    


}

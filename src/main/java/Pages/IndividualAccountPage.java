package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import BaseClass.BaseClass;

public class IndividualAccountPage extends BaseClass{
    private By accountsLink=By.xpath("//a[text()='Accounts']");

    public By getBreadCrumbName(String name)
    {
        return By.xpath("//div[@class='breadcrumb-item']/span[text()='"+name+"']"); 
    }

    public AccountsViewPage NavigateToAccountsDashbaord(String name)
    {
        waitUntilElementVisible(getBreadCrumbName(name));
        click(accountsLink);
        return new AccountsViewPage();
    }
    
}

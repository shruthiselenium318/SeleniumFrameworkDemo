package BaseClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverFactory.DriverFactory;


public class BaseClass {

    protected WebDriver getDriver() {
        return DriverFactory.getInstance().getDriver();
    }


    public void gotoURL(String url)
    {
        getDriver().get(url);
    }

    public void enterText( By locator,String txt)
    {
        getDriver().findElement(locator).sendKeys(txt);
    }
    public void click( By locator)
    {
        getDriver().findElement(locator).click();
    }
    public void clickDropdownOption(By locator,String value)
    {
        WebElement dropdown=getDriver().findElement(locator);
        Select select=new Select(dropdown);
        select.selectByValue(value);
    }



    public void ScrollTillElementVisible(By locator)
    {
        WebElement elem=getDriver().findElement(locator);
        JavascriptExecutor js=(JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({block:'center'});",elem);

    }
    public void pressTab(By locator)
    {
        getDriver().findElement(locator).sendKeys(Keys.TAB);
    }

    public String getValue( By locator)
    {
        return getDriver().findElement(locator).getDomProperty("value");
    }

    public String getTextValue( By locator)
    {
        return getDriver().findElement(locator).getAttribute("textContent");
    }

    public String getSelectedValueFromDrodpwon( By locator)
    {
        WebElement dropdown=getDriver().findElement(locator);
        Select select=new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void waitUntilElementVisible( By locator)
    {   
        WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForStaleElement(By locator)
    {
        WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(getDriver().findElement(locator)));
    }

    public void waitUntilElementClickable( By locator)
    {
        
        WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}

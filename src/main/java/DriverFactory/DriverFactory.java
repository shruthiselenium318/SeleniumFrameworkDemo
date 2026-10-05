package DriverFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

//import groovyjarjarantlr4.v4.parse.ANTLRParser.elementOptions_return;
import utilities.Assert_Report_LogUtil;

public class DriverFactory {
    private static DriverFactory driverfactory=new DriverFactory();
    private static ThreadLocal<WebDriver> singledriver=new ThreadLocal<>();

    private DriverFactory()
    {
        
    }

    public static DriverFactory getInstance()
    {
        return driverfactory;
    }

    public void setDriver(String BrowserName)
    {
        WebDriver driver=null;
        if(BrowserName.toUpperCase().equals("CHROME"))
        {
            ChromeOptions co=new ChromeOptions();
            co.addArguments("--incognito");
            driver=new ChromeDriver(co);     
        }
        else if(BrowserName.toUpperCase().equals("FIREFOX"))
        {
            driver=new FirefoxDriver();
        }
        else if(BrowserName.toUpperCase().equals("EDGE"))
        {
            driver=new EdgeDriver();
            
        }
        else if(BrowserName.toUpperCase().equals("REMOTE"))
        {
            MutableCapabilities cap=new MutableCapabilities();
            //Generic Capabilities
            cap.setCapability("browserName","Chrome");
            cap.setCapability("browserVersion","128.0");
            cap.setCapability("platformName","Windows 10");

            //Lambdatest Capabilities
            MutableCapabilities lt=new MutableCapabilities();
            lt.setCapability("Project","SauceDemoProject");

            cap.setCapability("LT:Options",lt);
            String GRID_URL="https://shruthibabu:LT:RdktNa8KD7VAJo6hD05VbEavZuxNTk7A22ITIndcBkVrB1Q@hub.lambdatest.com/wd/hub";
            try {
                driver=new RemoteWebDriver(new URL(GRID_URL),cap);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else 
        {
            throw new IllegalArgumentException("Incorrect browsername: "+BrowserName);
        }
        singledriver.set(driver);
    }

    public WebDriver getDriver()
    {
        return singledriver.get();
    }

    public void quitDriver()
    {
        WebDriver driver=singledriver.get();
        if(driver!=null)
        {
            driver.quit();
            singledriver.remove();
        }
    }
}

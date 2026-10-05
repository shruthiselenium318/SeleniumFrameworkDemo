package Test;

import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

import org.testng.annotations.Listeners;

import BaseClass.BaseClass;
import DriverFactory.DriverFactory;
import utilities.Assert_Report_LogUtil;
import utilities.DBUtil;
import utilities.fileUtil;

@Listeners({AllureTestNg.class})
public class BaseTest extends BaseClass{
    
    private String BrowserName=null;
    private String username=null;
    private String language=null;

    protected fileUtil fileutil=null;
    //DBUtil dbutil=null;

  


    @BeforeMethod
    public void init()  {
        //get the data for login page
        try
        {
            fileutil=new fileUtil("environment.properties");
            BrowserName=fileutil.GetProperty("browsername");
            username=fileutil.GetProperty("username");
            language=fileutil.GetProperty("language");
        }
        catch(Exception e)
        {
            System.out.println("reading the data from environment variables.");
            BrowserName = System.getenv("CONFIG_BROWSERNAME");
            username = System.getenv("CONFIG_USERNAME");
            language = System.getenv("CONFIG_LANGUAGE");
                }

        if(BrowserName==null||BrowserName.isEmpty())
        {
            throw new RuntimeException("Browsername is not configured");
        }else if(username==null||username.isEmpty())
        {
            throw new RuntimeException("Username is not configured");
        }
        else if(language==null||language.isEmpty())
        {
            throw new RuntimeException("Language is not configured");
        }
        
        //Establish DB Connection
        //dbutil=new DBUtil();
        //Create browser instance
        
        DriverFactory.getInstance().setDriver(BrowserName);
        
        getDriver().manage().window().maximize();

    }

    @AfterMethod(alwaysRun = true)
    public void teardown()
    {   
        DriverFactory.getInstance().quitDriver();
        //if(dbutil!=null)
        //{
         //   dbutil.closeConnection();
        //}
        
    }

    /*public void gotoURL(String url)
    {
        driver.get(url);
    }*/


}

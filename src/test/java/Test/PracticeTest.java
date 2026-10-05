package Test;

import Pages.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.client.HttpClient;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.fileUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
//@Listeners({AllureTestNg.class})
/*public class PracticeTest extends BaseTest{
    //protected WebDriver driver;
    fileUtil fileutil=new fileUtil("environment.properties");

    @Test(enabled = false)
    public void LoginTest()
    {
        gotoURL("https://the-internet.herokuapp.com/challenging_dom");
        String name="Iuvaret1";
        driver.findElement(By.xpath("//tr/td[text()='"+name+"']/following-sibling::td/a[text()='edit']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String currenturl=driver.getCurrentUrl();
        Assert.assertEquals(currenturl,"https://the-internet.herokuapp.com/challenging_dom#edit","Success");
        driver.quit();
    }

    @Test(enabled = false)
    public void GetAllValuesTest()
    {
        gotoURL("https://the-internet.herokuapp.com/challenging_dom");
        Hashtable<String,List<String>> table=new Hashtable<String,List<String>>();
        List<WebElement> numofRows=driver.findElements(By.xpath("//table/tbody/tr"));
        List<WebElement> numofCols=driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
        System.out.println("The number of rows is"+numofRows.size());
        System.out.println("The number of rows is"+numofCols.size());
        for(int i=0;i< numofCols.size()-1;i++)
        {
            String header=driver.findElement(By.xpath("//table/thead/tr/th["+(i+1)+"]")).getText();
            List<String> getcolval=new ArrayList<>();
            for(int j=0;j<numofRows.size();j++)
            {
                String val=driver.findElement(By.xpath("//table/tbody/tr["+(j+1)+"]/td["+(i+1)+"]")).getText();
                getcolval.add(val);
            }
            table.put(header,getcolval);
        }

        System.out.println(table);
        driver.quit();
    }

    @Test(enabled = false)
    public void brokenImage()
    {
        gotoURL("https://the-internet.herokuapp.com/broken_images");
        List<WebElement> imgelem=driver.findElements(By.tagName("img"));
        for(WebElement e:imgelem)
        {
            e.getAttribute("src");

                    Response response=RestAssured.get(e.getAttribute("src"));
                    //HttpURLConnection connection=(HttpURLConnection) new URL(e.getAttribute("src")).openConnection();
                    //connection.setRequestMethod("GET");
                    //connection.connect();
                    int responsecode=response.getStatusCode();
                    if(responsecode==200)
                    {
                        System.out.println(e.getAttribute("src"));
                        System.out.println("Not a broken image");
                    }
                    else {
                        System.out.println(e.getAttribute("src"));
                        System.out.println("Broken Image");
                    }


        }
    }


    @Test(enabled = false)
    public void checkbox()
    {
        gotoURL("https://the-internet.herokuapp.com/checkboxes");
        driver.findElement(By.xpath("//input[1]")).click();
        System.out.println(driver.findElement(By.xpath("//input[2]")).isSelected());
    }

    @Test(enabled=false)
    public void contextmenu()
    {
        gotoURL("https://the-internet.herokuapp.com/context_menu");
        Actions action=new Actions(driver);
        action.contextClick(driver.findElement(By.xpath("//div[@id='hot-spot']"))).perform();
        Alert alert=driver.switchTo().alert();
        alert.accept();
        System.out.println(alert.getText());

        //driver.findElement(By.xpath("//div[@id='hot-spot']"))
    }
//https://the-internet.herokuapp.com/drag_and_drop

    @Test(enabled = false)
    public void draganddrop() throws InterruptedException {
        gotoURL("https://the-internet.herokuapp.com/drag_and_drop");
        Actions action=new Actions(driver);
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//div[@id='column-a']")).click();
        //action.click(driver.findElement(By.xpath("//div[@id='column-a']"))).clickAndHold(driver.findElement(By.xpath("//div[@id='column-b']"))).build().perform();
        WebElement source=driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement target=driver.findElement(By.xpath("//div[@id='column-b']"));

        action.dragAndDrop(source,target).perform();
                //clickAndHold(driver.findElement(By.xpath("//div[@id='column-b']"))).build().perform();
        Thread.sleep(5000);
    }

    @Test(enabled = false)
    public void SmallModal()
    {
        gotoURL("https://demoqa.com/modal-dialogs");
        WebElement elem=driver.findElement(By.xpath("//button[@id='showSmallModal']"));
        JavascriptExecutor js=(JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block:'center'})",elem);
        driver.findElement(By.xpath("//button[text()='Small modal']")).click();
        //driver.switchTo().alert().dismiss();
        driver.findElement(By.xpath("//button[text()='Close']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void nestedframe()
    {
        gotoURL("https://the-internet.herokuapp.com/nested_frames");
        List<WebElement> frm=driver.findElements(By.tagName("frame"));
        System.out.println(frm.size());
        
        for(WebElement elem:frm)
        {
            
           driver.switchTo().frame(elem);
            List<WebElement> frm1=driver.findElements(By.tagName("frame"));
            System.out.println(frm1.size());
            break;
        }
    }*/






//}

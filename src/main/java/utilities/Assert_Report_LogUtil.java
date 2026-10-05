package utilities;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.qameta.allure.model.Status;

import java.io.ByteArrayInputStream;

public class Assert_Report_LogUtil {

        public static final Logger log= LogManager.getLogger(Assert_Report_LogUtil.class);


        //To Verify Expected and Actual result
        public static void AssertTrue(WebDriver driver, String Expected, String Actual, String PassMessage, String FailMessage)
        {
            try {
                Assert.assertEquals(Actual,Expected,PassMessage);
                log.info(PassMessage);
                Allure.step(PassMessage);
            } catch (AssertionError e) {
                Allure.step(FailMessage,Status.FAILED);
                attachScreenshot(driver);
                log.info(FailMessage);
                log.info(e.getMessage());
                throw e;
            }
        }

        //Log Pass Step
        public static void logAllurePassStep(String PassMessage)
        {
                log.info(PassMessage);
                Allure.step(PassMessage);
        }

        //Log Fail Step
        public static void logAllureFailStep(WebDriver driver,String failMessage,Exception e) {

            Allure.step(failMessage,Status.FAILED);
            attachScreenshot(driver);
            log.error(failMessage, e);
            throw new RuntimeException(failMessage, e);
        }

        //To Verify Expected and Actual result
        public static void AssertBoolean(WebDriver driver,boolean trueorfalse,String PassMessage,String FailMessage)
        {
            try {
                Assert.assertTrue(trueorfalse);
                log.info(PassMessage);
                Allure.step(PassMessage);
            } catch (AssertionError e) {
                Allure.step(FailMessage,Status.FAILED);
                attachScreenshot(driver);
                log.info(FailMessage);
                log.info(e.getMessage());
                throw e;
            }
        }

        //To only log the step using log4j
        public static void log4jLog(String Message)
        {
            log.info(Message);
        }

        public static void attachScreenshot(WebDriver driver){

            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
            }catch(Exception e)
            {
                throw new RuntimeException(e);
            }

        }




}

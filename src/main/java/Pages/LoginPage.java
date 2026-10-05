package Pages;

import org.openqa.selenium.By;

import BaseClass.BaseClass;

public class LoginPage extends BaseClass {

    By LoginButton=By.xpath("//button[text()='Login']");
    public HomePage Login()
    {

        waitUntilElementClickable(LoginButton);
        click(LoginButton);
        return new HomePage();
    }
}

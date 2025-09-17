package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){

        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement Link_MyAccount;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement Link_Register;
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement Link_Login;

    public void clickMyAccount(){

        Link_MyAccount.click();
    }

    public void clickRegister(){

        Link_Register.click();
    }

    public void clikLogin(){

        Link_Login.click();
    }
}

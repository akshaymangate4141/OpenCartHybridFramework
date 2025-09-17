package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

   public LoginPage(WebDriver driver){

       super(driver);
   }

   @FindBy(xpath = "//input[@id='input-email']")
    WebElement txt_Username;
   @FindBy(xpath = "//input[@id='input-password']")
    WebElement txt_Password;
   @FindBy(xpath = "//input[@value='Login']")
   WebElement btn_Login;

   public void setUserName(String userName){
       txt_Username.sendKeys(userName);
   }

   public void setPassword(String password){
       txt_Password.sendKeys(password);
   }

   public void clickLoginButton(){
       btn_Login.click();
   }
}

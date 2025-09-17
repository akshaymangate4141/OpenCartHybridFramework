package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationClass extends BasePage{

    public AccountRegistrationClass(WebDriver driver){

        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txt_firstname;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txt_lastname;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txt_email;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txt_telephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txt_password;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txt_confirmPassword;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement link_click;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement privacy_policy;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement message_confirmation;



    public void setFirstName(String firstName) {
        txt_firstname.sendKeys(firstName);
    }

    public void setLastname(String lastname) {
        txt_lastname.sendKeys(lastname);
    }

    public void setEmail(String email) {
        txt_email.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        txt_telephone.sendKeys(telephone);
    }

    public void setPassword(String password){
    txt_password.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        txt_confirmPassword.sendKeys(password);
    }

    public void cLickContinueButton() {
       link_click.click();
    }

    public void clickPrivacyPolicy(){
        privacy_policy.click();
    }

    public String getConfirmationMessage(){
        try {
            return message_confirmation.getText();
        } catch (Exception e) {
           return e.getMessage();
        }
    }


}

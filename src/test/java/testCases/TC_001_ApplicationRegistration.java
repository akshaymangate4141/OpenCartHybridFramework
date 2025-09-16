package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationClass;
import pageObjects.HomePage;

import java.time.Duration;

public class TC_001_ApplicationRegistration extends BaseClass{


    @Test
    public void testApplicationRegistration(){
        HomePage hp=new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegistrationClass arc=new AccountRegistrationClass(driver);
        arc.setFirstName(generateRandomString().toUpperCase());
        arc.setLastname(generateRandomString().toUpperCase());
        arc.setEmail(generateRandomString()+"@example.com");
        arc.setTelephone(generateRandomNumber());

        String password=generateRandomAlphanumeric();
        arc.setPassword(password);
        arc.setConfirmPassword(password);
        arc.clickPrivacyPolicy();
        arc.cLickContinueButton();

        String message=arc.getConfirmationMessage();

        Assert.assertEquals(message,"Your Account Has Been Created!");
    }

}

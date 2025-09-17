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
        try {

            logger.info("*********Strating Application Registration Testing****** ");

            HomePage hp = new HomePage(driver);
            logger.info("Clicking My Account");
            hp.clickMyAccount();
            logger.info("Clicking Register");
            hp.clickRegister();

            AccountRegistrationClass arc = new AccountRegistrationClass(driver);

            logger.info("Providing Customer Information");
            arc.setFirstName(generateRandomString().toUpperCase());
            arc.setLastname(generateRandomString().toUpperCase());
            arc.setEmail(generateRandomString() + "@example.com");
            arc.setTelephone(generateRandomNumber());

            String password = generateRandomAlphanumeric();
            arc.setPassword(password);
            arc.setConfirmPassword(password);
            arc.clickPrivacyPolicy();
            arc.cLickContinueButton();

            String message = arc.getConfirmationMessage();
            if (message.equals("Your Account Has Been Created!")){

                Assert.assertTrue(true);
                logger.info("Account Is Created");

            }
            else {

                logger.error("Test Failed");
                logger.debug("Debug Logs..");
                Assert.assertTrue(false);
            }

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }

        logger.info("********Application Registration Testing Is Completed*********");
    }

}

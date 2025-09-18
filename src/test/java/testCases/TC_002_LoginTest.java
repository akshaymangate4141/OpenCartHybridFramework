package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

import java.io.Reader;

public class TC_002_LoginTest extends BaseClass{

    @Test(groups = {"Regresstion","Master"})
    public void verifyLogin(){

        logger.info("*****Starting Login Test");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clikLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setUserName(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLoginButton();

            MyAccountPage mp = new MyAccountPage(driver);

            if (mp.isMyAccountPageIsExists() == true) {

                logger.info("Test Case Is Passed Login Is Successfull");
            }
            else {
                logger.error("Test Case Failed") ;
                throw new Exception("Test Case Failed");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("Debug");
            Assert.fail();
        }

        logger.info("******Login Test Is Completed");


    }

}

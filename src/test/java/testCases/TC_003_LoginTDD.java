package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_003_LoginTDD extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = {"Data Driven"})
    public void verifyLoginTdd(String email, String password, String exp) {

        logger.info("************ TC_003 Test Case Is Started********");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clikLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setUserName(email);
            lp.setPassword(password);
            lp.clickLoginButton();

            MyAccountPage mp = new MyAccountPage(driver);

            mp.isMyAccountPageIsExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (mp.isMyAccountPageIsExists() == true) {
                    Assert.assertTrue(true);
                    mp.clickLogout();
                    logger.info("Test Case Is Passed");
                } else {
                    Assert.assertTrue(false);
                    logger.error("Test Case Is Failed");
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (mp.isMyAccountPageIsExists() == true) {
                    logger.error("Test Case Is Failed");
                    mp.clickLogout();
                    Assert.assertTrue(false);

                } else {
                    Assert.assertTrue(true);
                    logger.info("Test Case Is Pass");

                }
            }


        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }

        logger.info("********** TC_003 Test Case Is Finished**********");
    }
}

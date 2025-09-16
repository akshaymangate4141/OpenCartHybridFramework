package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    public String generateRandomString(){
        String randomString= RandomStringUtils.randomAlphabetic(5);
        return randomString;
    }

    public String generateRandomNumber(){
        String randomNumber=RandomStringUtils.randomNumeric(10);
        return randomNumber;
    }

    public String generateRandomAlphanumeric(){
        String randomString= RandomStringUtils.randomAlphabetic(5);
        String randomNumber=RandomStringUtils.randomNumeric(10);
        return (randomString+"@"+randomNumber);
    }
}

package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        p = new Properties();
        p.load(fis);

        logger = LogManager.getLogger(this.getClass());
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invaid Browser Name");
                return;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public String generateRandomString() {
        String randomString = RandomStringUtils.randomAlphabetic(5);
        return randomString;
    }

    public String generateRandomNumber() {
        String randomNumber = RandomStringUtils.randomNumeric(10);
        return randomNumber;
    }

    public String generateRandomAlphanumeric() {
        String randomString = RandomStringUtils.randomAlphabetic(5);
        String randomNumber = RandomStringUtils.randomNumeric(10);
        return (randomString + "@" + randomNumber);
    }
}

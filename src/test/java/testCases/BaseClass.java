package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master", "Data Driven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {


        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
        p = new Properties();
        p.load(fis);

        logger = LogManager.getLogger(this.getClass());

        if (p.getProperty("runEnviorment").equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();


            switch (os.toLowerCase()) {

                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                default:
                    System.out.println("Invalid Operation System");
                    return;
            }

            switch (br.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("Invaid Browser Name");
                    return;

            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
        }
        if (p.getProperty("runEnviorment").equalsIgnoreCase("local")) {
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
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL"));
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master", "Data Driven"})
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

    public String captureTheScreenshot(String testName) {
        String screenshotName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot tc = (TakesScreenshot) driver;
        File sourceFile = tc.getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + screenshotName + ".png";
        File destinationFile = new File(targetPath);
        sourceFile.renameTo(destinationFile);

        return targetPath;
    }
}

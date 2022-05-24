import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SeleniumBaseTest {
    protected WebDriver driver;
    protected Config config;

    @BeforeMethod
    public void baseBeforeMethod() {
        WebDriverManager.chromedriver().setup();
        config = new Config();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(config.getApplicationUrl());
    }

    @AfterMethod
    //public void baseAfterMethod() {driver.quit();}

    public void baseAfterMethod(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            getScreenShot(testResult.getMethod().getMethodName());
        }
        driver.quit();
    }

    private void getScreenShot(String name) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "\\snapshots\\" + name + "_" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".jpg";
            String filePath = System.getProperties().get("user.dir") + fileName;
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException ex) {
            System.out.println("Exception while taking screenshot: " + ex.getMessage());
        }
    }
}


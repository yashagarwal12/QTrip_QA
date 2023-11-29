package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import com.relevantcodes.extentreports.ExtentReports;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import qtriptest.ReportSingleton;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {

    public static RemoteWebDriver driver;
    public static ExtentReports report;
    public static ExtentTest test;
    
    public static  HomePage homepage;

    public static RegisterPage register;

    public static LoginPage loginpage;

    public static AdventurePage adventurepage;

    public static AdventureDetailsPage adventuredetailspage;

    public static HistoryPage historyPage;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException  {
        // Launch Browser using Zalenium
       driver=DriverSingleton.getDriver();

        System.out.println("createDriver() : " + driver);

        homepage=new HomePage(driver);
        register=new RegisterPage(driver);
        loginpage=new LoginPage(driver);
        adventurepage=new AdventurePage(driver);
        adventuredetailspage=new AdventureDetailsPage(driver);
        historyPage=new HistoryPage(driver);

        report=ReportSingleton.getReport();
        report.loadConfig(new File("src/test/java/qtriptest/extent_customization_configs.xml"));
        test= report.startTest("QTrip");

    } 

    @AfterSuite(alwaysRun = true)
    public static void quitDriver(){
    driver.quit();
    report.endTest(test);
    report.flush();
    }

    public static String takeScreenshot(RemoteWebDriver driver, String screenshotType, String description) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType,
                    description);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            String desString=DestFile.getPath();
            FileUtils.copyFile(SrcFile, DestFile);
            return desString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

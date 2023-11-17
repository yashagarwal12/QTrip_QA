package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static RemoteWebDriver driver;
    
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
    } 

    @AfterSuite(alwaysRun = true)
    public static void quitDriver(){
    driver.quit();
    }
}

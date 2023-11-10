package qtriptest.tests;
import qtriptest.DP;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import com.beust.jcommander.Parameter;
import org.apache.poi.hssf.record.HCenterRecord;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.URL;


public class testCase_01 {

    static RemoteWebDriver driver;
    public static String lastGeneratedUserName;
    String url="https://qtripdynamic-qa-frontend.vercel.app/";

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException  {
        // Launch Browser using Zalenium
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().window().maximize();
        System.out.println("createDriver()");
    }
    //

    @Test(description="Verify user registration - login - logout",enabled = true,dataProvider = "qtripData", dataProviderClass =DP.class)
    //@Parameters({"user","pass"})
    public void TestCase01(String UserName, String Password) throws InterruptedException{
       SoftAssert SoftAssert = new SoftAssert();
         boolean status;
        HomePage homepage=new HomePage(driver);
        RegisterPage register=new RegisterPage(driver);
        LoginPage loginpage=new LoginPage(driver);
        homepage.navigateToHomePage(url);
        homepage.registerBtn();
        status=register.verifyNavigateToRegister();
        Assert.assertTrue(status,"Unable to click on register btn");

        register.performRegister(UserName, Password, Password, true);
        lastGeneratedUserName=register.username;
        System.out.println(lastGeneratedUserName);
        Thread.sleep(2000);

        status=loginpage.verifyNavigateToLogin();
        Assert.assertTrue(status,"Unable to register");

        loginpage.performLogin(lastGeneratedUserName, Password);
        Assert.assertTrue(status,"Unable to login");

        status= loginpage.logoutBtn();
        Assert.assertTrue(status,"Unable to logout");

        /*homepage.registerBtn();
        register.alreadyRegister(lastGeneratedUserName, pass, pass);
        Thread.sleep(5000);*/
    }

    @AfterSuite(alwaysRun = true)
    public static void quirDriver(){
        driver.quit();
    }



}

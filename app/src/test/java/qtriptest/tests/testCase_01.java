package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.LogStatus;
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



public class testCase_01 extends BaseTest {

    public static String lastGeneratedUserName;
    String url="https://qtripdynamic-qa-frontend.vercel.app/";

    @Test(description="Verify user registration - login - logout",groups={"Login Flow"},enabled = true,dataProvider = "TestCase01Data", dataProviderClass =DP.class)
    //@Parameters({"user","pass"})
    public void TestCase01(String UserName, String Password) throws InterruptedException, MalformedURLException{
        SoftAssert SoftAssert = new SoftAssert();
        boolean status;
      
        homepage.navigateToHomePage(url);

        homepage.selectOption("Register");

        status=register.verifyRegisteration();
        Assert.assertTrue(status,"Unable to click on register btn");
        if(status){
            test.log(LogStatus.PASS, "PASS-click on register btn");
           
        }
        else{
          //  test.log(LogStatus.FAIL, "FAIL-click on register btn");
            test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot(driver, "click on register btn", "TC01"))+"FAIL-click on register btn");
        }

        register.performRegister(UserName, Password, Password, true);

        lastGeneratedUserName=register.username;
        Thread.sleep(2000);

        status=loginpage.verifyNavigateToLogin();
        Assert.assertTrue(status,"Unable to register");
        if(status){
            test.log(LogStatus.PASS, "PASS-register");
           
        }
        else{
            test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot(driver, "Register", "TC01"))+"FAIL-register");

        }

        loginpage.performLogin(lastGeneratedUserName, Password);
        Assert.assertTrue(status,"Unable to login");
        if(status){
            test.log(LogStatus.PASS, "PASS-login");
           
        }
        else{
           // test.log(LogStatus.FAIL, "FAIL-login");
            test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot(driver, "login", "TC01"))+"FAIL-login");
        }

        status= loginpage.logoutBtn();
        Assert.assertTrue(status,"Unable to logout");
        if(status){
            test.log(LogStatus.PASS, "PASS-logout");
           
        }
        else{
         //   test.log(LogStatus.FAIL, "FAIL-logout");
            test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot(driver, "logout", "TC01"))+"FAIL-logout");
        }
        test.log(LogStatus.PASS,test.addScreenCapture(takeScreenshot(driver, "TC01-Completed", "TC01")));

    }

}

package qtriptest.tests;

import qtriptest.tests.BaseTest;
import java.text.ParseException;
import java.util.List;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import qtriptest.DP;
import qtriptest.pages.HistoryPage;

public class testCase_03 extends BaseTest {

    String lastGeneratedUserName;
    boolean status;
    String url="https://qtripdynamic-qa-frontend.vercel.app/";

    @Test(description="Verify Booking and Cancellation Flow",priority = 3,groups ={"Booking and Cancellation Flow"},enabled = true,dataProvider = "TestCase03Data", dataProviderClass =DP.class)
    public void TestCase03(String UserName, String Password, String cityName, String adventure, String guest, String date, String count) throws InterruptedException {
        boolean status;

        homepage.navigateToHomePage(url);

        homepage.selectOption("Register");

        status=register.verifyRegisteration();
        Assert.assertTrue(status,"Unable to click on register btn");
        if(status){
          test.log(LogStatus.PASS, "PASS-click on register btn");
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-click on register btn");
      }

        register.performRegister(UserName, Password, Password, true);
        

        lastGeneratedUserName=register.username;

        status=loginpage.verifyNavigateToLogin();
        Assert.assertTrue(status,"Unable to register");

        if(status){
          test.log(LogStatus.PASS, "PASS-register");
         
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-register");

      }

        loginpage.performLogin(lastGeneratedUserName, Password);
        Thread.sleep(2000);
        Assert.assertTrue(status,"Unable to login");
        if(status){
          test.log(LogStatus.PASS, "PASS-login");
         
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-login");
      }


        status=homepage.searchCity(cityName);
        Thread.sleep(2000);
        Assert.assertTrue(status,"Valid City is not present");
        if(status){
          test.log(LogStatus.PASS, "PASS-Valid City is present");
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-Valid City is not present");
      }

       status= adventurepage.search(adventure);
       Thread.sleep(2000);

      status=adventuredetailspage.booking(guest, date, count);
      Assert.assertTrue(status,"Booking is Unsuccessful");
      if(status){
        test.log(LogStatus.PASS, "PASS-Booking is successful");
    }
    else{
        test.log(LogStatus.FAIL, "FAIL-Booking is Unsuccessful");
    }
      Thread.sleep(2000);
       
      List<String> transac_id=historyPage.showReservation();
      for(int i=0;i<transac_id.size();i++){
       System.out.println(transac_id.get(i));
      }
        Thread.sleep(2000);

        status=historyPage.cancel();
        Assert.assertTrue(status,"Booking cancellation is Unsuccessful");
        if(status){
          test.log(LogStatus.PASS, "Booking cancellation is successful");
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-Booking cancellation is Unsuccessful");
      }
        
        loginpage.logoutBtn();
        test.log(LogStatus.PASS,test.addScreenCapture(takeScreenshot(driver, "TC03-Completed", "TC03")));
       
}
}

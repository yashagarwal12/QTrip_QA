package qtriptest.tests;

import qtriptest.tests.BaseTest;
import java.text.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qtriptest.DP;
import qtriptest.pages.HistoryPage;
import java.util.ArrayList;
import java.util.List;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_04 extends BaseTest{

    String lastGeneratedUserName;
    boolean status;
    String url="https://qtripdynamic-qa-frontend.vercel.app/";

    @Test(description="Verify Booking History flow",priority = 4,groups ={"Reliability Flow"},enabled = true,dataProvider = "TestCase04Data", dataProviderClass =DP.class)
    public void TestCase04(String UserName, String Password,String dataset1, String dataset2, String dataset3) throws InterruptedException {
        boolean status;
        SoftAssert softassert=new SoftAssert();
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
        Thread.sleep(2000);

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

        Assert.assertTrue(status,"Unable to login");
        if(status){
          test.log(LogStatus.PASS, "PASS-login");
         
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-login");
      }

        status=booktrip(dataset1);
        softassert.assertTrue(status,"Booking is Unsuccessful");
        if(status){
          test.log(LogStatus.PASS, "PASS-Booking is successful");
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-Booking is Unsuccessful");
      }
        homepage.selectOption("Home");
        Thread.sleep(2000);

        status=booktrip(dataset2);
        softassert.assertTrue(status,"Booking is Unsuccessful");
        if(status){
          test.log(LogStatus.PASS, "PASS-Booking is successful");
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-Booking is Unsuccessful");
      }
        homepage.selectOption("Home");
        Thread.sleep(2000);

        status=booktrip(dataset3);
        Assert.assertTrue(status,"Booking is Unsuccessful");
        if(status){
          test.log(LogStatus.PASS, "PASS-Booking is successful");
      }
      else{
          test.log(LogStatus.FAIL, "FAIL-Booking is Unsuccessful");
      }

        List<String> transac_id=historyPage.showReservation();
       for(int i=0;i<transac_id.size();i++){
        System.out.println(transac_id.get(i));
       }

        Thread.sleep(2000);
  
        loginpage.logoutBtn();
        test.log(LogStatus.PASS,test.addScreenCapture(takeScreenshot(driver, "TC04-Completed", "TC04")));

       
}

public boolean booktrip(String dataset) throws InterruptedException{
  boolean status;
  String[] data=dataset.split(";", 0);
  String cityName=data[0];
  String adventure=data[1];
  String guest=data[2];
  String date=data[3];
  String count=data[4];

  status=homepage.searchCity(cityName);
  Thread.sleep(2000);
  status= adventurepage.search(adventure);
  Thread.sleep(2000);
status=adventuredetailspage.booking(guest, date, count);
Thread.sleep(2000);
return status;

}
}

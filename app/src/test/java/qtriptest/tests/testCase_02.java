package qtriptest.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qtriptest.DP;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_02 extends BaseTest{

    boolean status;
    String url="https://qtripdynamic-qa-frontend.vercel.app/";

    @Test(description="Search & Filters",enabled = true,priority = 2,groups={"Search and Filter flow"},dataProvider = "TestCase02Data", dataProviderClass = DP.class)
    public void TestCase02(String cityName, String category, String duration, String expected, String actual) throws InterruptedException{
        boolean status;
        String count;
       homepage.navigateToHomePage(url);
       status=homepage.searchCity("Nanital");
       Assert.assertFalse(status,"Invalid City is present");
       if(!status){
        test.log(LogStatus.PASS, "PASS-Not Found message is present on passing Invalid City ");
    }
    else{
        test.log(LogStatus.FAIL, "FAIL-Invalid City is present");
    }

       status=homepage.searchCity(cityName);
       Assert.assertTrue(status,"Valid City is not present");
       if(status){
        test.log(LogStatus.PASS, "PASS-Valid City is present");
    }
    else{
        test.log(LogStatus.FAIL, "FAIL-Valid City is not present");
    }

       adventurepage.durationFilter(duration);
       Thread.sleep(2000);
       count = adventurepage.categoryFilter(category.trim());
       Thread.sleep(2000);
       Assert.assertEquals(count, expected,"Number of filtered tiles expected failed");
       if(status){
        test.log(LogStatus.PASS, "PASS-Number of filtered tiles present");
    }
    else{
        test.log(LogStatus.FAIL, "FAIL-Number of filtered tiles present");
    }


       count=adventurepage.clearFilter();
       Assert.assertEquals(count, actual,"Number of actual tiles expected failed");
       if(status){
        test.log(LogStatus.PASS, "PASS-Number of actual tiles present");
    }
    else{
        test.log(LogStatus.FAIL, "FAIL-Number of actual tiles present");
    }
    test.log(LogStatus.PASS,test.addScreenCapture(takeScreenshot(driver, "TC02-Completed", "TC01")));  
}
}

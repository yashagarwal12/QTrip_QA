package qtriptest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportSingleton {

   // static ExtentTest test;
    private static ExtentReports report;

    public ReportSingleton(){}

    public static ExtentReports getReport(){
        if(report==null){
        report=new ExtentReports(System.getProperty("user.dir")+"ExtentReportResults_"+".html",true);
        }
        return report;

    }
   
}
package qtriptest.pages;

import java.util.List;
import com.google.common.util.concurrent.Service.Listener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureDetailsPage {

    Actions action;
    RemoteWebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath="//*[@id='myForm']/input[@name='name']")
    WebElement name;

    @FindBy(xpath="//*[@id='myForm']//input[@name='date']")
    WebElement travel_date;

    @FindBy(xpath="//*[@id='myForm']//input[@name='person']")
    WebElement person;

    @FindBy(className ="reserve-button")
    WebElement reserve_btn;

    @FindBy(id="reserved-banner")
    WebElement reserved_banner;

   
    public AdventureDetailsPage(RemoteWebDriver driver){
         this.driver=driver;
         PageFactory.initElements(driver,this);
         wait= new WebDriverWait(driver, 10);
         action=new Actions(driver);

    }

    public boolean booking(String guest_name, String trip_date,String guest_count){
        try{
        name.sendKeys(guest_name);
        travel_date.sendKeys(trip_date);
        person.clear();
        person.sendKeys(guest_count);
        reserve_btn.click();
        wait.until(ExpectedConditions.textToBePresentInElement(reserved_banner, "Greetings! Reservation for this adventure is successful."));
        return true;
        }catch(Exception E){
            E.printStackTrace();
            return false;
        }
    }

   
}
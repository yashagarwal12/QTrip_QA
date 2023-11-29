
package qtriptest.pages;
import qtriptest.SeleniumWrapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryPage {

  SeleniumWrapper sw=new SeleniumWrapper();
    Actions action;
    RemoteWebDriver driver;
    WebDriverWait wait;
    

    @FindBy(linkText ="Reservations")
    WebElement reservation_btn;

    @FindBy(xpath = "//button[@class='cancel-button']")
    WebElement cancel_btn;

    @FindBys({@FindBy(xpath = "//table/tbody/tr/th")})
    List<WebElement> transacElement;

    @FindBy(id="no-reservation-banner")
    WebElement no_reservation;

    public HistoryPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait= new WebDriverWait(driver, 10);
        action=new Actions(driver);
    }
    public List<String> showReservation() throws InterruptedException{
        boolean status;
        sw.click_btn(reservation_btn, driver);
      // reservation_btn.click();
       Thread.sleep(5000);
        List<String> transacId=new ArrayList<String>();

        for(WebElement ele:transacElement){
            transacId.add(ele.getText());
        }

      // wait.until(ExpectedConditions.urlContains("pages/adventures/reservations/index.html"));
       return transacId;
    }

    public boolean cancel(){
        try{
       action.moveToElement(cancel_btn).click(cancel_btn).build().perform();
       Thread.sleep(3000);
    //    driver.switchTo().alert().accept();
        driver.navigate().refresh();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.textToBePresentInElement(no_reservation,"Oops! You have not made any reservations yet!"));
        return true;
        }catch(Exception E){
            E.printStackTrace();
            return false;
        }

    }



}
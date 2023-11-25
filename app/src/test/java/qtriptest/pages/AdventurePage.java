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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventurePage {
    Actions action;
    RemoteWebDriver driver;
    WebDriverWait wait;
    public int count;
    Select select;

     @FindBy(id="duration-select")
     WebElement duration;

     @FindBy(id="category-select")
     WebElement category;
     
     @FindBy(xpath="//*[@placeholder='Search adventures']")
     WebElement searchbar;

     @FindAll(@FindBy(xpath="//*[@class='col-6 col-lg-3 mb-4']"))
     List<WebElement> tiles;

     @FindBy(xpath="//*[@id='duration-select']//following-sibling::div")
     WebElement duration_clear;

     @FindBy(xpath="//*[@id='category-select']//following-sibling::div")
     WebElement category_clear;

     @FindBy(xpath="//*[@id='data']/div/a/div[2]/div/div/h5[1]")
     WebElement adventure_title;

     @FindBy(xpath="//*[@id='data']/div/a")
     WebElement adventure_link;

    public AdventurePage(RemoteWebDriver driver){
        this.driver=driver;
         PageFactory.initElements(driver,this);
         wait= new WebDriverWait(driver, 10);
         action=new Actions(driver);

    }

    public String durationFilter(String durationtext){
        action.click(duration).perform();
        select=new Select(duration);
        select.selectByVisibleText(durationtext); 
        count=tiles.size(); 
        return String.valueOf(count);
       
    }
    public String categoryFilter(String categorytext) throws InterruptedException{

         action.click(category).perform();
         Thread.sleep(2000);
         select=new Select(category);
         select.selectByVisibleText(categorytext); 
         count=tiles.size(); 
         return String.valueOf(count);
    }

    public boolean search(String adventure){
        try{
        action.click(searchbar).sendKeys(searchbar,adventure).build().perform();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.textToBePresentInElement(adventure_title, adventure.trim()));
        action.click(adventure_link).perform();
        wait.until(ExpectedConditions.urlContains("/pages/adventures/detail/?adventure"));
        return true;
        }catch(Exception E){
         return false;
    }
    }

    public String clearFilter(){
        duration_clear.click();
        category_clear.click();
        count=tiles.size();
        return String.valueOf(count);
    }

}
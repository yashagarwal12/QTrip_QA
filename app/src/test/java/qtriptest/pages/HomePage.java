package qtriptest.pages;


import javax.lang.model.util.ElementScanner6;
import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
    RemoteWebDriver driver;

    @FindBy(xpath  ="//*[@class='nav-link login register']")
    WebElement register_btn;

    @FindBy(xpath ="//*[@class='nav-link login']")
    WebElement login_btn;

    @FindBys({@FindBy(xpath = "//*[@class='nav-link']"), @FindBy(xpath="//*[text()='Home']") })
    WebElement home_btn;

    @FindBys({@FindBy(xpath = "//*[@class='formtitle']"), @FindBy(xpath="//*[text()='Register']") })
    WebElement register_head;

    @FindBy(xpath="//*[@placeholder='Search a City ']")
    WebElement searchbar;

    @FindBy(xpath = "//ul[@id='results']/h5")
    WebElement noResult;

    @FindBy(xpath = "//ul[@id='results']/a/li")
    WebElement result;

    @FindBy(xpath = "//ul[@id='results']/a")
    WebElement resultLink;

    WebDriverWait wait;
    // WebDriverWait wait=new WebDriverWait(driver, 30); 


    public HomePage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, 10);
    }

    public void navigateToHomePage(String url){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }
    }

    public void selectOption(String option){

        if(option.equalsIgnoreCase("Register"))
        register_btn.click();

        else if(option.equalsIgnoreCase("Login"))
        login_btn.click();

        else if(option.equalsIgnoreCase("Home"))
        home_btn.click();

        else
        System.out.println("Invalid Option:"+option);
    }

   
    public boolean searchCity(String city){
        boolean status;
        try{
            Actions action =new Actions(driver);
            action.click(searchbar).sendKeys(searchbar,city).build().perform();
           
        wait.until(ExpectedConditions.textToBePresentInElement(result,city.trim()));
        action.click(resultLink).perform();
        wait.until(ExpectedConditions.urlContains("/pages/adventures/"));
        status= true;
        }catch(Exception E){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='results']/h5")));
            status= false;
        }
        return status;
    }

}

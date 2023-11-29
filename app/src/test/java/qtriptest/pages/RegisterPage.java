package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.Date;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    SeleniumWrapper sw=new SeleniumWrapper();
    private final static String REGISTER_PAGE="/pages/register/";
    RemoteWebDriver driver;
    public String username="";
    @FindBy(name="email")
    WebElement email;

    @FindBy(name="password")
    WebElement password;

    @FindBy(name="confirmpassword")
    WebElement confirmpassword;

    @FindBys({@FindBy(xpath = "//*[@class='formtitle']"), @FindBy(xpath="//*[text()='Register']") })
    WebElement register_head;

    @FindBy(xpath = "//*[@class='btn btn-primary btn-login']")
    WebElement register;

    public RegisterPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    
    public boolean verifyRegisteration(){
        return driver.getCurrentUrl().endsWith(REGISTER_PAGE) && register_head.isDisplayed();
    }

    public void performRegister(String user, String pass, String cnfmpass,boolean option) throws InterruptedException{
    
        if(option){
        Date date=new Date();
        String d=String.valueOf(date.getTime());
        username=String.format("testuser_%s%s",d,user);
        }
        else{
            username=String.format("%s",user);
        }
        sw.sendKeys(email, username, driver);
        sw.sendKeys(password, pass, driver);
        sw.sendKeys(confirmpassword, cnfmpass, driver);
       // email.sendKeys(username);
        //password.sendKeys(pass);
        //confirmpassword.sendKeys(cnfmpass);
        boolean status;
        status= sw.click_btn( register,driver);
        Thread.sleep(2000);
    }


    public void alreadyRegister(String user, String pass, String cnfmpass) throws InterruptedException{
        
        sw.sendKeys(email, username, driver);
        sw.sendKeys(password, pass, driver);
        sw.sendKeys(confirmpassword, cnfmpass, driver);
           
        //email.sendKeys(user);
           // password.sendKeys(pass);
           // confirmpassword.sendKeys(cnfmpass);
            boolean status;
            status= sw.click_btn( register,driver);
            //register.click();
            Thread.sleep(2000);
            WebDriverWait wait=new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
    
        
    }
    }

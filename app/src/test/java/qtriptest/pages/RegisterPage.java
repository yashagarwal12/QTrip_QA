package qtriptest.pages;

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
    
    public boolean verifyNavigateToRegister(){
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
        email.sendKeys(username);
        password.sendKeys(pass);
        confirmpassword.sendKeys(cnfmpass);
        register.click();
        Thread.sleep(2000);
    }


    public void alreadyRegister(String user, String pass, String cnfmpass) throws InterruptedException{
        
            email.sendKeys(user);
            password.sendKeys(pass);
            confirmpassword.sendKeys(cnfmpass);
            register.click();
            Thread.sleep(2000);
            WebDriverWait wait=new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
    
        
    }
    }

package qtriptest.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import qtriptest.SeleniumWrapper;
public class LoginPage {

    private final static String LOGIN_PAGE="/pages/login";
    private final static String HOME_PAGE="https://qtripdynamic-qa-frontend.vercel.app/";
    SeleniumWrapper sw=new SeleniumWrapper();

    @FindBy(name="email")
    WebElement email;

    @FindBy(name="password")
    WebElement password;

    @FindBy(xpath = "//*[text()='Login to QTrip']")
    WebElement login;

    @FindBy(xpath="//*[text()='Logout']")
    WebElement logout_btn;

    @FindBys({@FindBy(xpath = "//*[@class='nav-link']"), @FindBy(xpath="//*[text()='Home']") })
    WebElement home_btn;

    
    @FindBys({@FindBy(xpath = "//*[@class='formtitle']"), @FindBy(xpath="//*[text()='Login']") })
    WebElement login_head;

    @FindBy(xpath  ="//*[@class='nav-link login register']")
    WebElement register_btn;
    @FindBy(xpath ="//*[@class='nav-link login']")
    WebElement login_btn;

    RemoteWebDriver driver;
    public LoginPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyNavigateToLogin(){
       return driver.getCurrentUrl().endsWith(LOGIN_PAGE) && login_head.isDisplayed();
    }

    public void performLogin(String user, String pass) throws InterruptedException{
        boolean status;
        sw.sendKeys(email, user, driver);
        sw.sendKeys(password, pass, driver);
        
       // email.sendKeys(user);
       // password.sendKeys(pass);
        status= sw.click_btn(login,driver);
        Thread.sleep(2000);
    }

    public boolean verfyLogin(){
        return driver.getCurrentUrl().equals(HOME_PAGE) && logout_btn.isDisplayed();
    }

    public boolean logoutBtn(){
        boolean status;
        status= sw.click_btn( logout_btn,driver);
       return login_btn.isDisplayed() && register_btn.isDisplayed();
    }
}

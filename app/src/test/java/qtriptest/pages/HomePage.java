package qtriptest.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

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

    public HomePage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToHomePage(String url){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }
    }

    public void registerBtn(){
        register_btn.click();
    }

    public void LoginBtn(){
        login_btn.click();
    }

    public void HomeBtn(){
        home_btn.click();
    }
}

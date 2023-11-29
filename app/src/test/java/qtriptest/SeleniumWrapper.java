package qtriptest;

import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {
    
    JavascriptExecutor js;
    public boolean click_btn(WebElement element, RemoteWebDriver driver){
        Actions action=new Actions(driver);
        try{
        if(element.isDisplayed()){
           js =(JavascriptExecutor)driver;
           js.executeScript("arguments[0].scrollIntoView", element);
           action.click(element).perform();
        }
        return true;
    }catch(Exception E){
        return false;
    }
    }

    public boolean sendKeys(WebElement element, String keysToSend,RemoteWebDriver driver){
        try{
        element.clear();
        element.sendKeys(keysToSend);
        return true;
        }catch(Exception E){
            return false;
        }
    }

    public boolean getURL(String url, RemoteWebDriver driver){
        try{
            driver.get(url);
            return true;
            }catch(Exception E){
                return false;
            }
    }

    public WebElement findElementWithRetry(By by, RemoteWebDriver driver,int retryCount){

        WebElement element=null;
        try{
            WebDriverWait wait=new WebDriverWait(driver,10);
            element=wait.until(ExpectedConditions.presenceOfElementLocated(by));
           
        }catch(Exception E){
            for(int i=0; i<retryCount; i++){
                element = driver.findElement(by);
                if(element.isDisplayed())
                    return element;
            }
        }
        return element;

    }
}

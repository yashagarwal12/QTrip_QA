package qtriptest;

import java.sql.Driver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSingleton {

private static RemoteWebDriver driver=null;

private DriverSingleton(){}

public static RemoteWebDriver getDriver() throws MalformedURLException{
    if(driver==null){
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().window().maximize();
    }
    return driver;
}
}

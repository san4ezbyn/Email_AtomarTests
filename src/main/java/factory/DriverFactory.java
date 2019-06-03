package factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static WebDriver createDriver(String browserName) {
        //browserName = System.getProperty("browser.type");
        DesiredCapabilities capabilities;
        switch (browserName) {
            case "chrome": {
                System.setProperty("webDriver.chrome.driver", "C:/chromeDriver/chromedriver.exe");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setPlatform(Platform.WINDOWS);
                break;
            }
            default: {
                System.setProperty("webDriver.firefox.driver", "C:/fireFoxDriver/geckodriver.exe");
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.WINDOWS);
            }
        }
        try {
         RemoteWebDriver  remoteWebDriver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver.set(remoteWebDriver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver.get();
    }
}

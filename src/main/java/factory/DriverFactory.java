package factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private volatile static WebDriver webDriver = null;


    public WebDriver createDriver(String browserName) {
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
                capabilities.setPlatform(Platform.WINDOWS);
            }
        }
        try {
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver.set(webDriver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver.get();

    }

    public static void killDriver() {
        driver.get().quit();
        driver.set(null);
    }
}

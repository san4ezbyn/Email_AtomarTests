package decorator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;


public class DriverDecorator extends RemoteWebDriver {

    public static final int IMPLICIT_WAIT = 80;
    private WebDriver driver;

    public DriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver initDriver(String url) {
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}


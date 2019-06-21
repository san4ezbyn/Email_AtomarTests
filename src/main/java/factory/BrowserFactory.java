package factory;

import decorator.DriverDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class BrowserFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver createDriver(BrowserType browserName, String url) {

        if (driver.get() != null) {
            return driver.get();
        }

        DriverDecorator driverDecorator;
        try {
            switch (browserName) {
                case CHROME: {
                    driverDecorator = new DriverDecorator(new ChromeBrowser().create());
                    driver.set(driverDecorator.initDriver(url));
                    break;
                }
                default: {
                    driverDecorator = new DriverDecorator(new FireFoxBrowser().create());
                    driver.set(driverDecorator.initDriver(url));
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver.get();
    }
}

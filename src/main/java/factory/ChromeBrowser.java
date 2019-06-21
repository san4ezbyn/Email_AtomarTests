package factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeBrowser extends AbstractBrowser {


    @Override
    public RemoteWebDriver create() throws MalformedURLException {
        System.setProperty("webDriver.chrome.driver", "C:/chromeDriver/chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.WINDOWS);

        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }
}

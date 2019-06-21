package factory;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public abstract class AbstractBrowser {

  public abstract RemoteWebDriver create() throws MalformedURLException;

}

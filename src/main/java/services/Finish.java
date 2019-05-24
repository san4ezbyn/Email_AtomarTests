package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Finish {

    WebDriver driver;

    public Finish(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}

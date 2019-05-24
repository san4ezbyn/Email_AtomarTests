package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOut {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='mail-User-Name'][contains(text(),'alexbyn')]")
    private static WebElement userAccount;

    public LogOut(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public Finish logOut() {
        userAccount.click();
        WebElement leaveYandexServices = driver.findElement(By.xpath("//a[@data-metric='Sign out of Yandex services'][contains(text(),'Выйти из сервисов Яндекса')]"));
        leaveYandexServices.click();
        return new Finish(this.driver);
    }

    public String getStartPageTitel() {
        return this.driver.getTitle();
    }

}

package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class LogOut {

    private static WebDriver driver;

    @FindBy(className = "mail-User-Name")
    private Button userAccount;

    public LogOut(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
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

package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SendDraftMail {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private static WebElement sentLetters;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private static WebElement draftLetters;

    public SendDraftMail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }

    public LogOut checkDraftLettersFolder(String topic) {

        draftLetters.click();
        checkFolders(topic);
        return new LogOut(this.driver);
    }

    public LogOut checkSentLettersFolder(String topic) {
        sentLetters.click();
        checkFolders(topic);
        return new LogOut(this.driver);
    }

    public boolean checkFolders(String topic) {
        List<WebElement> listOfLetters = driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        for (WebElement draftLetter : listOfLetters) {
            wait.until(ExpectedConditions.textToBePresentInElement(draftLetter, topic));
            if (draftLetter.getText().contains(topic)) {
                return false;
            }
        }
        return true;
    }
}

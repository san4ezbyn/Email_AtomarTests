package services;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DeleteLetter {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(xpath = "//div[@data-id='2']")
    private WebElement deleteLetter;

    @FindBy(xpath = "//span[contains(text(),'Входящие')]")
    private WebElement incomingLetters;

    @FindBy(xpath = "//span[contains(text(),'Почтовый сервис yandex.ru')]")
    private WebElement dragAndDropThisLetter;

    @FindBy(xpath = "//span[@class='mail-Toolbar-Item-Text js-toolbar-item-title js-toolbar-item-title-delete'][contains(text(),'Удалить')]")
    private WebElement droppable;

    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text'][contains(text(),'Написать')]")
    private WebElement writeButton;

    @FindBy(xpath = "//span[contains(text(),'Почтовый сервис yandex.ru')]")
    private WebElement thisOneDelete;

    @FindBy(xpath = "//div[@class='mail-MessageSnippet-Content']")
    private WebElement someLetter;

    public DeleteLetter(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }

    public void countList() {
        List<WebElement> listOfLetters = driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        System.out.println("\nSIZE of LIST - " + listOfLetters.size());

    }

    public LogOut checkDraftLettersFolder(String topic) throws InterruptedException {

        countList();
        /*List<WebElement> listOfLetters = driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        for (WebElement draftLetter : listOfLetters) {*/

/*wait.until(ExpectedConditions.visibilityOfAllElements(listOfLetters));
            wait.until(ExpectedConditions.textToBePresentInElement(draftLetter, topic));
            {
                if (draftLetter.getText().contains(topic)) {*/
        new Actions(driver).contextClick(someLetter).moveToElement(deleteLetter).click().build().perform();

        // wait.until(ExpectedConditions.visibilityOf(incomingLetters)).click();

        /*JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementsByClassName('mail-NestedList-Item-Name').click()");*/

       // clickElementByJS(incomingLetters, driver);
        clickElementByJS(writeButton, driver);
        incomingLetters.click();


        Actions dragAndDrop = new Actions(driver);
        dragAndDrop.dragAndDrop(dragAndDropThisLetter, droppable).build().perform();

        // }

Thread.sleep(2000);
        countList();


        // }
        // }
        return new LogOut(this.driver);
    }

    public void clickElementByJS(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("document.getElementsByClassName('mail-NestedList-Item-Name')", element);

    }
}




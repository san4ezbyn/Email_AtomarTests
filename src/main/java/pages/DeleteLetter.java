package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

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
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public static int countList() {
        List<WebElement> listOfLetters = driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        System.out.println("\nSIZE of LIST - " + listOfLetters.size());
        return listOfLetters.size();
    }

    public NewLetter checkDraftLettersFolder() throws InterruptedException {

        int listBeforeDelete = countList();
        new Actions(driver).contextClick(someLetter).moveToElement(deleteLetter).click().build().perform();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(incomingLetters)).click();

        Actions dragAndDrop = new Actions(driver);
        dragAndDrop.dragAndDrop(clickElementByJS(dragAndDropThisLetter, driver), droppable).build().perform();

        Thread.sleep(2000);
        int listAfterDelete = countList();
        int diff = listBeforeDelete - listAfterDelete;
        checkListAfterDeleteIsTrue();
        Assert.assertEquals(diff, checkListAfterDeleteIsTrue());

        return new NewLetter(this.driver);
    }

    public WebElement clickElementByJS(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByClassName('mail-MessageSnippet-Item mail-MessageSnippet-Item_sender js-message-snippet-sender mail-MessageSnippet-Item_widgetSender')", element);
        return element;
    }

    public static int checkListAfterDeleteIsTrue() {
        return 2;
    }
}




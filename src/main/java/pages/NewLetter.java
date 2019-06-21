package pages;

import htmlBlocks.WriteNewLetter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public class NewLetter {

    private static final String NEW_LETTER_TOPIC = "AT-WD-№";

    private static WebDriver driver;

    private WriteNewLetter writeNewLetter;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private Button draftLetters;

    @FindBy(xpath = "//div[@class='mail-MessageSnippet-Wrapper']")
    private List<WebElement> listOfDraftLetter;

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private Button sendLetter;

    @FindBy(xpath = "//div[@data-nb='popup']//*[contains(text(),'Сохранить и перейти')]")
    private Button acceptPopUp;

    public NewLetter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public SendDraftMail newLetter(String receiver, String topic, String text) {

        writeNewLetter.newLetterWrite(receiver, topic, text);
        draftLetters.click();

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        acceptPopUp.click();
        draftLetters.click();
        return new SendDraftMail(this.driver);
    }

    public boolean findDraft() {
        for (WebElement draft : listOfDraftLetter) {
            if (draft.getText().contains(NEW_LETTER_TOPIC)) {

                return true;
            }
        }
        return false;
    }

    public boolean verifyDraftIsCorrect(String receiver, String topic, String text) {
        for (WebElement draft : listOfDraftLetter) {
            if (draft.getText().contains(topic) || draft.getText().contains(receiver) || draft.getText().contains(text)) {
                draft.click();
                sendLetter.click();

                return true;
            }
        }
        return false;
    }
}


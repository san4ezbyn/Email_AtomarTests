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






/*
package pages;

import htmlBlocks.WriteNewLetter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public class NewLetter {

    private static final String NEW_LETTER_TOPIC = "AT-WD-№";

    private static WebDriver driver;

    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text'][contains(text(),'Написать')]")
    private WebElement writeNewLetter;
*/
/*@FindBy(name = "to")
private TextInput receiverField;*//*

    @FindBy(xpath = "//div[@name='to']")
    private WebElement receiverField;
*/
/*@FindBy(className = "mail-Compose-Field-Input-Controller js-compose-field js-editor-tabfocus-prev")
private TextInput topicField;*//*

    @FindBy(xpath = "//div[@class='mail-Compose-Field-Input']/input[@type='text']")
    private WebElement topicField;
*/
/*@FindBy(className ="cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_show_borders")
private TextInput textField;*//*

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textField;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftLetters;

    @FindBy(xpath = "//div[@class='mail-MessageSnippet-Wrapper']")
    private List<WebElement> listOfDraftLetter;
    */
/*@FindBy(className = "_nb-button-text")
    private Button sendLetter;*//*


    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendLetter;


    @FindBy(xpath = "//div[@data-nb='popup']//*[contains(text(),'Сохранить и перейти')]")
    private WebElement acceptPopUp;

   // private WriteNewLetter writeNewLetter;

    public NewLetter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public SendDraftMail newLetter(String receiver, String topic, String text) {

        writeNewLetter.click();
        receiverField.sendKeys(receiver);
        topicField.sendKeys(topic);
        textField.sendKeys(text);
        sendLetter.click();

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
*/

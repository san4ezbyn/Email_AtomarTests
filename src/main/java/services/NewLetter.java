package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class NewLetter {

    private static final String NEW_LETTER_TOPIC = "AT-WD-№";

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='user2']")
    private WebElement correctUser;

    @FindBy(xpath = "//a[contains(text(),'Почта')]")
    private WebElement pochtaButton;

    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text'][contains(text(),'Написать')]")
    private WebElement writeNewLetter;

    @FindBy(xpath = "//div[@name='to']")
    private WebElement receiverField;

    @FindBy(xpath = "//div[@class='mail-Compose-Field-Input']/input[@type='text']")
    private WebElement topicField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textField;

    @FindBy(xpath = "//span[contains(text(),'как черновик')]")
    private WebElement saveAsDraft;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftLetters;

    @FindBy(xpath = "//div[@class='mail-MessageSnippet-Wrapper']")
    private List<WebElement> listOfDraftLetter;

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendLetter;

    @FindBy(xpath = "//div[@data-nb='popup']//*[contains(text(),'Сохранить и перейти')]")
    private WebElement acceptPopUp;

    public NewLetter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    public SendDraftMail newLetter(String receiver, String topic, String text) {

        correctUser.click();
        pochtaButton.click();
        writeNewLetter.click();
        receiverField.sendKeys(receiver);
        topicField.sendKeys(topic);
        textField.sendKeys(text);
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

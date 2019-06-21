package htmlBlocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("New letter")
@FindBy(xpath = "//div[@class=\"mail-Layout-Aside-Inner js-scroller-left\"]")
public class WriteNewLetter extends HtmlElement {


    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text'][contains(text(),'Написать')]")
    private WebElement writeNewLetter;

    @FindBy(xpath = "//div[@name='to']")
    private TextInput receiverField;

    @FindBy(xpath = "//div[@class='mail-Compose-Field-Input']/input[@type='text']")
    private TextInput topicField;

    @FindBy(xpath = "//div[@role='textbox']")
    private TextInput textField;


    public void newLetterWrite(String receiver, String topic, String text) {
        writeNewLetter.click();
        receiverField.sendKeys(receiver);
        topicField.sendKeys(topic);
        textField.sendKeys(text);

    }
}

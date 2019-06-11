package htmlBlocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;


@Name("Login")
@FindBy(xpath = "//form")
public class Login extends HtmlElement {

    private static final String LOGIN = "alexbyn";
    private static final String PASSWORD = "lollipop18";

    @Name("login")
    @FindBy(id = "passp-field-login")
    private TextInput login;

    @Name("pass")
    @FindBy(id = "passp-field-passwd")
    private TextInput password;


    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButtonEnter;

    public void enterCreds() {

        login.sendKeys(LOGIN);
        loginButtonEnter.click();
        password.sendKeys(PASSWORD);
        loginButtonEnter.click();
    }

}

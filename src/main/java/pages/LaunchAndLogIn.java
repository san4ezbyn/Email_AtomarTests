package pages;

import htmlBlocks.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class LaunchAndLogIn {

    private static WebDriverWait wait;
    private static WebDriver driver;

    private static final String LOGIN = "alexbyn";
    private static final String PASSWORD = "lollipop18";
    private static final String USER_ACCOUNT = "alexbyn";
    private static final int TIME_OUT_SECONDS = 45;
    private static final int SLEEP_MILLIS = 120;

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButtonEnter;

    @FindBy(xpath = "//div[@class='user2']")
    private WebElement correctUser;

    @FindBy(xpath = "//div[@class='mail-User-Name']")
    private WebElement verifyUser;

    @FindBy(xpath = "//a[contains(text(),'Почта')]")
    private WebElement pochtaButton;

    private Login login;


    public LaunchAndLogIn(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIME_OUT_SECONDS, SLEEP_MILLIS);

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public DeleteLetter startAndLogIn() {

        login.enterCreds();
        correctUser.click();
        pochtaButton.click();

        return new DeleteLetter(this.driver);
    }

    public boolean userAccount() {
        if (verifyUser.getText().equalsIgnoreCase(USER_ACCOUNT)) {
            return true;
        }
        return false;
    }
}

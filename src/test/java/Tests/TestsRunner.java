package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;

public class TestsRunner {
    private static WebDriver driver;
    private static final String URL = "https://passport.yandex.by";
    private static final String PAGE_TITLE = "Яндекс";
    private static LaunchAndLogIn launchAndLogIn;
    private static NewLetter newLetter;
    private SendDraftMail sendDraftMail;
    private LogOut logOut;
    private Finish finish;
    private static final String RECEIVER = "fake@gmale.com";
    private static final String TOPIC = String.format("AT-WD-№-%s", new Random().nextInt(100));
    private static final String TEXT = "SOME TEXT FOR LETTER";
    public static final int IMPLICIT_WAIT=60;

    @BeforeClass
    private void init() throws InterruptedException {
        setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        launchAndLogIn = new LaunchAndLogIn(driver);
    }

    @Test
    private void login() throws InterruptedException {
        newLetter = launchAndLogIn.startAndLogIn();
        Assert.assertTrue(launchAndLogIn.userAccount());
    }

    @Test
    private void newLetterSavedInDraft() {
        sendDraftMail = newLetter.newLetter(RECEIVER, TOPIC, TEXT);
        Assert.assertTrue(newLetter.findDraft());
        System.out.println("TOPIC " + TOPIC);
    }

    @Test
    private void verifyAndSendDraft() {
        Assert.assertTrue(newLetter.verifyDraftIsCorrect(RECEIVER, TOPIC, TEXT));
    }

    @Test(dependsOnMethods = "verifyAndSendDraft")
    private void checkDraftFolder() {
        logOut = sendDraftMail.checkDraftLettersFolder(TOPIC);
        Assert.assertFalse(sendDraftMail.checkFolders(TOPIC));
    }

    @Test(dependsOnMethods = "checkDraftFolder")
    private void checkSentLetterFolder() {
        logOut = sendDraftMail.checkSentLettersFolder(TOPIC);
        Assert.assertFalse(sendDraftMail.checkFolders(TOPIC));
    }

    @Test(dependsOnMethods = "checkSentLetterFolder")
    private void logingOut() {
        finish = logOut.logOut();
        Assert.assertEquals(logOut.getStartPageTitel(), PAGE_TITLE);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

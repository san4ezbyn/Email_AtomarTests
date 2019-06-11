package Tests;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import services.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestsRunner {
    private static WebDriver driver;
    private static final String URL = "https://passport.yandex.by";
    private static final String PAGE_TITLE = "Яндекс";
    private LaunchAndLogIn launchAndLogIn;
    private NewLetter newLetter;
    private SendDraftMail sendDraftMail;
    private DeleteLetter deleteLetter;
    private LogOut logOut;
    private Finish finish;
    private static final String RECEIVER = "fake@gmale.com";
    private static final String TOPIC = String.format("AT-WD-№-%s", new Random().nextInt(100));
    private static final String TEXT = "SOME TEXT FOR LETTER";
    public static final int IMPLICIT_WAIT = 80;


    public static WebDriver getDriver(String browser) {

        return DriverFactory.createDriver(browser);
    }

    @BeforeClass
    @Parameters({"browserName"})
    public void startBrowser(String browserName) {
        driver = getDriver(browserName);
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    private void login() {

        launchAndLogIn = new LaunchAndLogIn(driver);
        deleteLetter = launchAndLogIn.startAndLogIn();
        Assert.assertTrue(launchAndLogIn.userAccount());
    }

    @Test(dependsOnMethods = "login")
    private void deleteWithActionsAndJS() throws InterruptedException {
        newLetter = deleteLetter.checkDraftLettersFolder();
        // TODO Assertion
    }

    @Test(dependsOnMethods = "deleteWithActionsAndJS", alwaysRun = true)
    private void newLetterSavedInDraft() {

        sendDraftMail = newLetter.newLetter(RECEIVER, TOPIC, TEXT);
        Assert.assertTrue(newLetter.findDraft());
        System.out.println("TOPIC " + TOPIC);
    }

    @Test(dependsOnMethods = "newLetterSavedInDraft", alwaysRun = true)
    private void verifyAndSendDraft() {
        Assert.assertTrue(newLetter.verifyDraftIsCorrect(RECEIVER, TOPIC, TEXT));
    }

    @Test(dependsOnMethods = "verifyAndSendDraft", alwaysRun = true)
    private void checkDraftFolder() {
        logOut = sendDraftMail.checkDraftLettersFolder(TOPIC);
        Assert.assertFalse(sendDraftMail.checkFolders(TOPIC));
    }

    @Test(dependsOnMethods = "checkDraftFolder", alwaysRun = true)
    private void checkSentLetterFolder() {
        logOut = sendDraftMail.checkSentLettersFolder(TOPIC);
        Assert.assertFalse(sendDraftMail.checkFolders(TOPIC));
    }

    @Test(dependsOnMethods = "checkSentLetterFolder", alwaysRun = true)
    private void logingOut() {
        finish = logOut.logOut();
        Assert.assertEquals(logOut.getStartPageTitel(), PAGE_TITLE);
    }

    @AfterClass
    public void tearDown() {
       // driver.close();
        //driver.quit();

    }
}
